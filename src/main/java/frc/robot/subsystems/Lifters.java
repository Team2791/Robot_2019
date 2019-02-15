package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.util.IrSensor;

public class Lifters extends Subsystem {
    private TalonSRX frontLifter;
    private TalonSRX backLifter;
    private VictorSPX lifterDrive;
    private IrSensor frontIR;
    private IrSensor backIR;
    private int frontPotZero;
    private int backPotZero;

    public Lifters() {
        frontLifter = new TalonSRX(RobotMap.kFrontLiftTalon);
        backLifter = new TalonSRX(RobotMap.kBackLiftTalon);
        lifterDrive = new VictorSPX(RobotMap.kRollerVictor);
        frontLifter.setNeutralMode(NeutralMode.Brake);
        backLifter.setNeutralMode(NeutralMode.Brake);
        lifterDrive.setNeutralMode(NeutralMode.Brake);

        frontLifter.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
        frontLifter.setSensorPhase(false);

        backLifter.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
        backLifter.setSensorPhase(false);

        frontIR = new IrSensor(RobotMap.kFrontIrReadout);
        backIR = new IrSensor(RobotMap.kBackIrReadout);
        frontPotZero = Constants.kFrontLifterPotMin;
        backPotZero = Constants.kBackLifterPotMin;

		// TODO: Tune PID
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void extendFront(double output) {
        if(output < 0 && isFrontRetracted()) {
            frontLifter.set(ControlMode.PercentOutput, 0);
        } else if(output > 0 && isFrontExtended()) {
            frontLifter.set(ControlMode.PercentOutput, 0);
        } else{
            frontLifter.set(ControlMode.PercentOutput, output);
        }
    }

    public void extendBack(double output) {
        if(output < 0 && isBackRetracted()) {
            backLifter.set(ControlMode.PercentOutput, 0);
        } else if(output > 0 && isBackExtended()) {
            backLifter.set(ControlMode.PercentOutput, 0);
        } else{
            backLifter.set(ControlMode.PercentOutput, output);
        }
    }

    public void ExtendBoth(double output) {
        int backHeight = getBackLifterHeight();
        int frontHeight = getFrontLifterHeight();

        extendBack(output);

        if(frontHeight > backHeight) {
            if(output >= 0) {
                extendFront(output / 2);
            } else {
                extendFront(5 * output / 4);
            }
        } else if(backHeight > frontHeight) {
            if(output <= 0) {
                extendFront(output / 2);
            } else {
                extendFront(5 * output / 4);
            }
        } else {
            extendFront(output);
        }
    }

    public void zeroPots()
    {
        if(isFrontRetracted()) {
            frontPotZero = getFrontHeight();
        }

        if(isBackRetracted()) {
            backPotZero = getBackHeight();
        }
    }

    public boolean isFrontRetracted() {
        return frontLifter.getSensorCollection().isRevLimitSwitchClosed();
    }

    public boolean isBackRetracted() {
        return backLifter.getSensorCollection().isRevLimitSwitchClosed();
    }

    public boolean isFrontExtended() {
        return frontLifter.getSensorCollection().isFwdLimitSwitchClosed();
    }
     
    public boolean isBackExtended() {
        return backLifter.getSensorCollection().isFwdLimitSwitchClosed();
    }

    public void driveMotor(double output) {
        lifterDrive.set(ControlMode.PercentOutput, output);
    }

    private int getFrontHeight() {
        return 1023 - frontLifter.getSensorCollection().getAnalogIn();
    }

    private int getBackHeight() {
        return 1023 - backLifter.getSensorCollection().getAnalogIn();
    }

    public int getFrontLifterHeight() {
        return getFrontHeight() - frontPotZero;
    }

    public int getBackLifterHeight() {
        return getBackHeight() - backPotZero;
    }

    public int getBackVelocity() {
        return backLifter.getSensorCollection().getAnalogInVel();
    }

    // public boolean isFrontOverLedge(boolean isHigh) {
    //     double comparison = isHigh ? Constants.kHighPlatformHeight : Constants.kLowPlatformHeight;
    //     return frontIR.getInches() <= comparison;
    // }

    // public boolean isBackOverLedge(boolean isHigh) {
    //     double comparison = isHigh ? Constants.kHighPlatformHeight : Constants.kLowPlatformHeight;
    //     return backIR.getInches() <= comparison;
    // }

    public void debug() {
        SmartDashboard.putString("Front Lifter Retracted", Boolean.toString(isFrontRetracted()));
        SmartDashboard.putString("Front Lifter Extended", Boolean.toString(isFrontExtended()));
        SmartDashboard.putString("Back Lifter Retracted", Boolean.toString(isBackRetracted()));
        SmartDashboard.putString("Back Lifter Extended", Boolean.toString(isBackExtended()));
        SmartDashboard.putNumber("Pot Front value ", getFrontHeight());
        SmartDashboard.putNumber("Pot Back value ", getBackHeight());
        SmartDashboard.putNumber("Pot Back velocity ", getBackVelocity());
        SmartDashboard.putNumber("Front IR ", frontIR.getValue());
        SmartDashboard.putNumber("Back IR ", backIR.getValue());
        SmartDashboard.putNumber("Adjusted Front Pot", getFrontLifterHeight());
        SmartDashboard.putNumber("Adjusted Back Pot", getBackLifterHeight());
    }
}