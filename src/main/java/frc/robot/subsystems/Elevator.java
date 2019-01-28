package frc.robot.subsystems;

import static java.lang.StrictMath.max;
import static java.lang.StrictMath.min;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.StopLift;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Elevator extends Subsystem {
    DigitalInput topLimitSwitch, bottomLimitSwitch;
    Solenoid breakSolenoid;

    TalonSRX leadTalon;
    VictorSPX followVictor;
    BaseMotorController[] motorControllers;
    AnalogInput poptAnalogInput;
    Timer breakReleaseTimer;
    boolean breakReleaseTimerStarted = false;
    public static double speedModifier;
    BaseMotorController leaderTalon;// followerVictor;

    public Elevator() {
        super("Elevator");
        topLimitSwitch = new DigitalInput(RobotMap.kElevatorTopLimit);
        bottomLimitSwitch = new DigitalInput(RobotMap.kElevatorBottomLimit);
        poptAnalogInput = new AnalogInput(RobotMap.kElevatorPot);

        breakSolenoid = new Solenoid(RobotMap.kPCM, RobotMap.kBreakSolenoid); // RobotMap ids need to be changed
        breakReleaseTimer = new Timer();

        leaderTalon = new TalonSRX(RobotMap.kElevatorTalon);
        leaderTalon.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
        // followerVictor = new VictorSPX(RobotMap.LIFT_VICTOR);
        // followerVictor.follow(leaderTalon);

        motorControllers = new BaseMotorController[] {
            leaderTalon,
      //      followerVictor
        };

        for (int i = 0; i < motorControllers.length; i++) {
            motorControllers[i].setNeutralMode(NeutralMode.Brake);
            // this will limit the motor controllers from shocking the lift with full power
            // it will take them 0.5s to ramp up to full power.
            motorControllers[i].configOpenloopRamp(0.25, 10);
        }

    }


    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new StopLift());
    }
    /**
     * Returns the lift height in inches from the bottom.
     *
     * @return
     */
    public double getHeight() {
        // From the TalonSRX software manual
        // - Analog-In Position, Analog-In Velocity, 10bit ADC Value, 
        // The value can be positive or negative so only divide by 2**9
        // THIS DOES NOT WORK!
        return convertSRXInitsToLiftHeight(getSRXVoltageFeedback()) + Constants.kElevatorPotOffset;
        //    	return potentiometer.get();
    }

    public double getVelocity() {
        return convertSRXInitsToLiftHeight(getSRXVoltageVelocityFeedback() * 10);
    }

    public int getSRXVoltageFeedback() {
        // return 1024 - (-leaderTalon.getSelectedSensorPosition(0));
        return leaderTalon.getSelectedSensorPosition(0);
    }

    public int getSRXVoltageVelocityFeedback() {
        return leaderTalon.getSelectedSensorVelocity(0);
    }

    public int convertLiftHeightToSRXUnits(double liftHeightIn) {
        return (int)((liftHeightIn - Constants.kElevatorPotOffset) / Constants.kElevatorPotFullRange * 1023);
    }

    public double convertSRXInitsToLiftHeight(int SRXUnits) {
        double potTravel = SRXUnits / 1023.0;
        return potTravel * Constants.kElevatorPotFullRange;
    }

    // this method is used to set the power of the lift and included saftey so the lift
    // is moving slowly near the top/bottom and once at the top/bottom can't break itself. 
    public void setPower(double power) {
        SmartDashboard.putNumber("Lift - set power input", power);
        // make sure the break is released before we let it move
        if (breakReleaseTimer.get() < 0.12) {
            setPowerUnsafe(0);
            return;
        }

        if (atBottom()) {
            power = max(0, power);
        } else if (closeToBottom()) {
            power = max(0, power); // was 0.2 without manipulator, if it needs to be something it can be -0.05 or something very small
        } else if (atTop()) {
            power = min(0.01, power); // let the lift hold itself at the top.
        } else if (closeToTop()) {
            power = min(0.35, power);
        }

        // increasing speed from -0.45 to -.6 before Utica.
        power = max(-.60, power);

        // now we use the internal method that has direct control to the motor
        // after we have made sure that power is a safe number.
        setPowerUnsafe(power);
    }
    public boolean atBottom() {
        return !bottomLimitSwitch.get() || getHeight() < Constants.kElelvatorMinHeight - 0.1;
    }

    public boolean closeToBottom() {
        return getHeight() < Constants.kElelvatorMinHeight + Constants.kElevatorBottomSafetyDistance;
    }

    public boolean atTop() {
        return !topLimitSwitch.get() || getHeight() > Constants.kElevatorMaxHeight + 0.1;
    }

    public boolean closeToTop() {
        return getHeight() > Constants.kElevatorMaxHeight - Constants.kElevatorTopSafetyDistance;
    }

    public void setPowerUnsafe(double power) {
        leaderTalon.set(ControlMode.PercentOutput, power);
    }
    public void setBreak(boolean breakOn) {
        breakSolenoid.set(!breakOn); // Solenoid is default on. True means the break will be off

        if (breakOn) {
            // reset and stop the timer when we put the break on.
            breakReleaseTimer.reset();
            breakReleaseTimer.stop();
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
        //        SmartDashboard.putBoolean("Lift - Top Limit Switch value", !topLimitSwitch.get());
        //        SmartDashboard.putBoolean("Lift - Bottom Limit Switch value", !bottomLimitSwitch.get());

        //        SmartDashboard.putBoolean("Lift - Close to top", closeToTop());
        //        SmartDashboard.putBoolean("Lift - Close to bottom", closeToBottom());

        SmartDashboard.putNumber("Elevator - Height", getHeight());
        SmartDashboard.putNumber("Elevator - Velocity", getVelocity());
        SmartDashboard.putNumber("Elevator - Velocity RAW", getSRXVoltageVelocityFeedback());
        //       SmartDashboard.putNumber("Elevator - MM - Error RAW", Robot.elevator.getMagicMotionInstantError()); not doing magic motion for right now
        //      SmartDashboard.putNumber("Elevator - MM - Error IN", Robot.elevator.getMagicMotionInstantErrorIn()); not doing magic motion for right now 
        //        SmartDashboard.putNumber("Lift - Analog voltage value", potAnalogInput.getVoltage());
        SmartDashboard.putNumber("Elevator - SRX Return value", getSRXVoltageFeedback());

        //        SmartDashboard.putNumber("Lift - Motor One value", motorOne.getMotorOutputPercent());
        //        SmartDashboard.putNumber("Lift - Motor Two value", motorTwo.getMotorOutputPercent());
        //        SmartDashboard.putNumber("Lift - Motor Three value", motorThree.getMotorOutputPercent());
        SmartDashboard.putBoolean("Elevator - Break value", !breakSolenoid.get());
        //        SmartDashboard.putNumber("Lift - break timer", breakReleaseTimer.get());
    }

}