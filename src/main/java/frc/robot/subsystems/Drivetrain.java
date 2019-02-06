package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.StopDrive;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Drivetrain extends Subsystem {
    private TalonSRX leftLeader;
    private TalonSRX rightLeader;
    private VictorSPX leftFollower1;
    private VictorSPX leftFollower2;
    private VictorSPX rightFollower1;
    private VictorSPX rightFollower2;
    private ADXRS450_Gyro gyro;
    private boolean gyroDisabled = false;

    private BaseMotorController[] leftDrive;
    private BaseMotorController[] rightDrive;

    private double speedMultiplier;

    private StopDrive defaultCommand;

    public Drivetrain() {
        leftLeader = new TalonSRX(RobotMap.kLeftTalon);
        rightLeader = new TalonSRX(RobotMap.kRightTalon);
        leftFollower1 = new VictorSPX(RobotMap.kLeftVictors[0]);
        leftFollower2 = new VictorSPX(RobotMap.kLeftVictors[1]);
        rightFollower1 = new VictorSPX(RobotMap.kRightVictors[0]);
        rightFollower2 = new VictorSPX(RobotMap.kRightVictors[1]);

        leftDrive = new BaseMotorController[]{leftLeader, leftFollower1, leftFollower2};
        rightDrive = new BaseMotorController[]{rightLeader, rightFollower1, rightFollower2};

        for(int i = 0; i < rightDrive.length; ++i) {
            rightDrive[i].setInverted(true);
        }

        leftLeader.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        rightLeader.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

        leftFollower1.follow(leftLeader);
        leftFollower2.follow(leftLeader);
        rightFollower1.follow(rightLeader);
        rightFollower2.follow(rightLeader);
        try {
			gyro = new ADXRS450_Gyro();// SPI.Port.kOnboardCS1
			gyro.calibrate(); // takes 5 seconds
			gyro.reset();
			System.out.println("Gyro is working! :'('");
		} catch (NullPointerException e) {
			gyroDisabled = true;
			System.out.println("Gyro is unplugged, Disabling Gyro");
		}
        setDriveSpeed(false);
        setBrakeMode(false);
        setMotors(0, 0);
    }

    public void initDefaultCommand() {
        if(defaultCommand == null) {
            defaultCommand = new StopDrive();
        }
        defaultCommand.start();
    }

    public void setMotors(double left, double right) {
        leftLeader.set(ControlMode.PercentOutput, left * speedMultiplier);
        rightLeader.set(ControlMode.PercentOutput, right * speedMultiplier);
    }

    public void setBrakeMode(boolean isbrake) {
        NeutralMode mode = isbrake ? NeutralMode.Brake : NeutralMode.Coast;
        for(int i = 0; i < leftDrive.length; ++i) {
            leftDrive[i].setNeutralMode(mode);
            rightDrive[i].setNeutralMode(mode);
        }
    }

    public void setDriveSpeed(boolean isSlow) {
        speedMultiplier = isSlow ? Constants.kSlowDrive : Constants.kFastDrive;
    }
    public double getGyroAngle(){
        if(!gyroDisabled)
            return gyro.getAngle();
        System.err.println("Gyro is Disabled, Angle is Incorrect");
        return 0.0;
    }
    public double getGyroRate() {
        if (!gyroDisabled)
            return gyro.getRate();
        System.err.println("Gyro is Disabled, Rate is Incorrect");
        return 0.0;
    }
    public double getGyroAngleInRadians() {
        return getGyroAngle() * (Math.PI/180);
    }
    public void resetGyro() {
        if(!gyroDisabled) {
            gyro.reset();
        } else { 
            System.err.println("Gyro is Disabled, Unable to Reset");
        }
    }
    public boolean getGyroDisabled() {
        return gyroDisabled;
    } 
    public void calibrateGyro() {
        if (!gyroDisabled) {
            System.out.println("Gyro calibrating");
            gyro.calibrate();
            System.out.println("Done calibrating " + " The current rate is " + gyro.getRate());
        }
    }
    public void debug(){
        // put PID values in here later
    }
}  
   