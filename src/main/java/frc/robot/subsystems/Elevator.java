package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Elevator extends Subsystem {
    private TalonSRX driveTalon;
    private Solenoid breakSolenoid;
    private Timer breakReleaseTimer;
    private boolean breakReleaseTimerStarted = false;
    private int elevatorZero;
    private int elevatorMax;

    public Elevator() {
        driveTalon = new TalonSRX(RobotMap.kElevatorTalon);

        breakSolenoid = new Solenoid(RobotMap.kPCM, RobotMap.kBreakSolenoid); // RobotMap ids need to be changed
        breakReleaseTimer = new Timer();

        driveTalon.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
        driveTalon.setSensorPhase(false);
        driveTalon.setNeutralMode(NeutralMode.Brake);
        driveTalon.overrideLimitSwitchesEnable(false);
        elevatorZero = Constants.kElevatorMinHeight;
        elevatorMax = elevatorZero + Constants.kElevatorPotFullRange;
        //TODO: Tune PID
    }


    @Override
    protected void initDefaultCommand() {
    }

    public void zeroPot() {
        if(atBottom()) {
            elevatorZero = getHeight();
            elevatorMax = elevatorZero + Constants.kElevatorPotFullRange;
        }
    }
    
    private int getHeight() {
        return driveTalon.getSensorCollection().getAnalogIn();
    }

    // public int getElevatorHeight() {
    //     return getHeight() - elevatorZero;
    // }
    public int getElevatorHeight() {
        return getHeight() - elevatorZero;
    }

    public double getSRXHeight() {
        return convertSRXUnitstoHeight(getVoltageFeedback()) + Constants.kPotOffset;
    }
    
    public int getVoltageFeedback() {
        // return driveTalon.getSensorCollection().getVoltage();
        return 0;
    }
    public int getVelocity() {
        return driveTalon.getSensorCollection().getAnalogInVel();
    }

    public void setPower(double power) {
        //Check if the break is released before moving
        if(breakReleaseTimer.get() < 0.12) {
    		setPowerUnsafe(0);
    		return;
        }
        
        //Negative power is up
        power *= -1;
        if(power < -0.01) {
            setPowerUp(power);
        } else if(power > 0.01) {
            setPowerDown(power);
        } else { 
            setPowerUnsafe(0);
        }
    }
    public double convertSRXUnitstoHeight(int srxunits){
        double height = srxunits/1023.0; // taking the raw value and dividing up maximum potentiometer angle 
        return height * Constants.kElevatorPotFullRange;
    }
    private void setPowerUp(double power) {
        if(closeToTop()) {
            setPowerUnsafe( -Constants.kElevatorMinPower);
        } else {
            driveTalon.set(ControlMode.PercentOutput, power);
        }
    }
    
    private void setPowerDown(double power) {
        if(closeToBottom()) {
            setPowerUnsafe( Constants.kElevatorMinPower);
            } else {
            driveTalon.set(ControlMode.PercentOutput, power);
        }
    }

    public boolean atBottom() {
        return driveTalon.getSensorCollection().isRevLimitSwitchClosed();
    }

    public boolean closeToBottom() {
         return getElevatorHeight() <= Constants.kElevatorBottomSafetyDistance;
        
    }

    public boolean atTop() {
        return driveTalon.getSensorCollection().isFwdLimitSwitchClosed();
    }

    public boolean closeToTop() {
         return getElevatorHeight() >= elevatorMax - Constants.kElevatorTopSafetyDistance;
        
    }

    public void setPowerUnsafe(double power) {
        if(power > 0 && atBottom()) {
            driveTalon.set(ControlMode.PercentOutput, 0);
        } else if(power < 0 && atTop()) { 
            driveTalon.set(ControlMode.PercentOutput, -Constants.kElevatorMinPower);
        } else {
            driveTalon.set(ControlMode.PercentOutput, power);
        }
    }

    public int setPosition(int liftPosition) {
        int target = liftPosition + elevatorZero;
        
        driveTalon.set(ControlMode.Position, target);

        return target - driveTalon.getSensorCollection().getAnalogIn();
    }
    

    public void setBreak(boolean breakOn) {

        breakSolenoid.set(!breakOn); // Solenoid is default on. True means the break will be off
        System.out.println(breakOn);
        
        if (breakOn) {
            // reset and stop the timer when we put the break on.
            breakReleaseTimer.stop();
            breakReleaseTimer.reset();
            breakReleaseTimerStarted = false;
        } else {
            // when we release the break start the timer.
            // we need to check if we've already started the timer because the
            // start timer method also resets it.
                if (!breakReleaseTimerStarted) {
                breakReleaseTimer.start();
                breakReleaseTimerStarted = true;
            }
        }
    }

    public void debug() {
        SmartDashboard.putBoolean("Elevator - Top Limit Switch value", atTop());
        SmartDashboard.putBoolean("Elevator - Bottom Limit Switch value", atBottom());
        SmartDashboard.putBoolean("Elevator - Break value", !breakSolenoid.get());
        SmartDashboard.putNumber("Elevator - break timer", breakReleaseTimer.get());
        SmartDashboard.putNumber("Elevator - Height", getHeight());
        SmartDashboard.putNumber("Elevator - Adjusted Height", getElevatorHeight());
        SmartDashboard.putNumber("Elevator zero", elevatorZero);
        SmartDashboard.putNumber("Elevator max", elevatorMax);
        SmartDashboard.putBoolean("Elevator - Close to top", closeToTop());
        SmartDashboard.putBoolean("Elevator - Close to bottom", closeToBottom());
    }

}