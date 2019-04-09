package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.DigitalOutput;
// import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Solenoid;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.StopDrive;
import frc.robot.Robot;
import frc.robot.util.Util;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Drivetrain extends Subsystem {
    private CANSparkMax leftLeader;
    private CANSparkMax rightLeader;
    private CANSparkMax[] leftFollowers;
    private CANSparkMax[] rightFollowers;
    private double distancePerPulse = distancePerPulse = Util.tickToFeet(Constants.driveEncoderTicks, Constants.WHEEL_DIAMETER_IN_IN);

    private AnalogInput lineSensors[];
    private boolean lineFound = false;
    private Solenoid leds;
    // private DigitalOutput pin0;
    // private PWM lineLight;
    private ADXRS450_Gyro gyro;
    private boolean gyroDisabled = false;


    private double speedMultiplier;

    private StopDrive defaultCommand;

    public Drivetrain() {
        leftLeader = new CANSparkMax(RobotMap.kLeftLeader, MotorType.kBrushless);
        leftLeader.setOpenLoopRampRate(Constants.kNeoRampTime);
        
        rightLeader = new CANSparkMax(RobotMap.kRightLeader, MotorType.kBrushless);
        rightLeader.setInverted(true);
        rightLeader.setOpenLoopRampRate(Constants.kNeoRampTime);

        leftFollowers = new CANSparkMax[RobotMap.kLeftFollowers.length];
        for(int i = 0; i < leftFollowers.length; ++i) {
            leftFollowers[i] = new CANSparkMax(RobotMap.kLeftFollowers[i], MotorType.kBrushless);
            leftFollowers[i].setIdleMode(IdleMode.kCoast);
            leftFollowers[i].setOpenLoopRampRate(Constants.kNeoRampTime);
            leftFollowers[i].follow(leftLeader);
        }

        rightFollowers = new CANSparkMax[RobotMap.kRightFollowers.length];
        for(int i = 0; i < rightFollowers.length; ++i) {
            rightFollowers[i] = new CANSparkMax(RobotMap.kRightFollowers[i], MotorType.kBrushless);
            rightFollowers[i].setInverted(true);
            rightFollowers[i].setIdleMode(IdleMode.kCoast);
            rightFollowers[i].setOpenLoopRampRate(Constants.kNeoRampTime);
            rightFollowers[i].follow(rightLeader);
        }

        lineSensors = new AnalogInput[4];
        leds = new Solenoid(RobotMap.kPCM, RobotMap.kLEDSolenoid);
        for(int i = 0; i < 4; ++i) {
            lineSensors[i] = new AnalogInput(RobotMap.kLineSensors[i]);
        }

        setDriveSpeed(false);
        setBrakeMode(true);
        setMotors(0, 0);

        setLimit();
        lineFound = false;
        initGyro();
    }

    private void setLimit() {
        leftLeader.setSmartCurrentLimit(Constants.kNeoAmpLimit);
        rightLeader.setSmartCurrentLimit(Constants.kNeoAmpLimit);
        for(int i = 0; i < leftFollowers.length; ++i) {
            leftFollowers[i].setSmartCurrentLimit(Constants.kNeoAmpLimit);
            rightFollowers[i].setSmartCurrentLimit(Constants.kNeoAmpLimit);
        }
    }
    public void initGyro(){
        try {
			gyro = new ADXRS450_Gyro();// SPI.Port.kOnboardCS1
			gyro.calibrate(); // takes 5 seconds
			gyro.reset();
			System.out.println("Gyro is working! :-)");
		} catch (NullPointerException e) {
			gyroDisabled = true;
			System.out.println("Gyro is unplugged, Disabling Gyro");
		}
    }
    public void initDefaultCommand() {
        if(defaultCommand == null) {
            defaultCommand = new StopDrive();
        }
        defaultCommand.start();
    }

    public void setMotors(double left, double right) {
        SmartDashboard.putNumber("LeftSideOutput", left);
        SmartDashboard.putNumber("RightSideOutput",right);

        leftLeader.set(left * speedMultiplier);
        rightLeader.set(right * speedMultiplier);
    }

    public void setBrakeMode(boolean isbrake) {
        IdleMode mode = isbrake ? IdleMode.kBrake : IdleMode.kCoast;
        leftLeader.setIdleMode(mode);
        rightLeader.setIdleMode(mode);
    }

    public void setDriveSpeed(boolean isSlow) {
        speedMultiplier = isSlow ? Constants.kSlowDrive : Constants.kFastDrive;
    }

    public int getLeftEncoder() {
        return (int)(leftLeader.getEncoder().getPosition() + 0.5); //+1/2 for rounding
    }

    public int getRightEncoder() {
        return (int)(rightLeader.getEncoder().getPosition() + 0.5); //+1/2 for rounding
    }

    public double getAverageDist() {
		return Util.average(getLeftDistance(), getRightDistance());
		// right side commented out due to potential wiring issues
		// return getLeftDistance();
    }
    public double getLeftDistance() {
        //TODO NOW THIS IS WHERE IT GETS FUNKY
        //TODO IF THIS BREAKS READ THIS FUNCTION
        //Multiply the # of ticks by distance per tick in feet, dont change distance per pulse
        //Note that leftLeader.getEncoder().getPosition()
        //YOU NEED TO FIX THIS FUNCTION TO RETURN THE DISTANCE TRAVELED IN FEET
        //Left side is returning value between 0 and 1 for how complete the rotation is
		return leftLeader.getEncoder().getPosition() * ((0.5 * Math.PI)*.12);
    }
    
    public double getRightDistance() {
        //TODO NOW THIS IS WHERE IT GETS FUNKY
		return rightLeader.getEncoder().getPosition() * ((0.5 * Math.PI)*.12);
    }
    // public double getGyroAngle(){
    //     if(!gyroDisabled)
    //         return gyro.getAngle();
    //     System.err.println("Gyro is Disabled, Angle is Incorrect");
    //     return 0.0;
    // }
    // public double getGyroRate() {
    //     if (!gyroDisabled)
    //         return gyro.getRate();
    //     System.err.println("Gyro is Disabled, Rate is Incorrect");
    //     return 0.0;
    // }
    // public double getGyroAngleInRadians() {
    //     return getGyroAngle() * (Math.PI/180);
    // }
    // public void resetGyro() {
    //     if(!gyroDisabled) {
    //         gyro.reset();
    //     } else { 
    //         System.err.println("Gyro is Disabled, Unable to Reset");
    //     }
    // }
    // public boolean getGyroDisabled() {
    //     return gyroDisabled;
    // } 
    // public void calibrateGyro() {
    //     if (!gyroDisabled) {
    //         System.out.println("Gyro calibrating");
    //         gyro.calibrate();
    //         System.out.println("Done calibrating " + " The current rate is " + gyro.getRate());
    //     }
    // }

    public int getLineSensors() {
        int res = 0;
        res |= lineSensors[0].getAverageVoltage() > Constants.kLineVoltCutoff ? 1 : 0;
        res |= lineSensors[1].getAverageVoltage() > Constants.kLineVoltCutoff ? 2 : 0;
        res |= lineSensors[2].getAverageVoltage() > Constants.kLineVoltCutoff ? 4 : 0;
        res |= lineSensors[3].getAverageVoltage() > Constants.kLineVoltCutoff ? 8 : 0;
        lineFound = (res & 15) > 0;
        // // pin0.set(true);
        // lineLight.setRaw(255);
        leds.set(lineFound);
        return res;
    }

    public double getLineSensor(int index)
    {
        return lineSensors[index].getAverageVoltage();
    }

    public void debug(){
        SmartDashboard.putNumber("Left Encoder", getLeftEncoder());
        SmartDashboard.putNumber("Right Encoder", getLeftEncoder());
        SmartDashboard.putNumber("Right Drivetrain 1", Robot.pdp.getCurrent(1));
        SmartDashboard.putNumber("Right Drivetrain 2", Robot.pdp.getCurrent(0));
        SmartDashboard.putNumber("Left Drivetrain 1", Robot.pdp.getCurrent(14));
        SmartDashboard.putNumber("Left Drivetrain 2", Robot.pdp.getCurrent(15));
        SmartDashboard.putNumber("Line Active", getLineSensors());
        SmartDashboard.putBoolean("Line Found", lineFound);
        SmartDashboard.putNumber("DT - Gyro angle", gyro.getAngle());
		SmartDashboard.putNumber("DT - Gyro rate", gyro.getRate());
        for(int i = 0; i < 4; ++ i) {
            SmartDashboard.putNumber("Line Voltage" + i, getLineSensor(i));
        }
    }
}  