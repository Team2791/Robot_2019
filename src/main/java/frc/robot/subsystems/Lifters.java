package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
// import edu.wpi.first.wpilibj.InterruptableSensorBase;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.util.IrSensor;

class DiffPIDDriver implements PIDSource, PIDOutput {
    private double output;
    private Lifters lifters;

    public DiffPIDDriver(Lifters lifters) {
        this.lifters = lifters;
    }

    public void pidWrite(double output) {
        this.output = output;
    }

    public PIDSourceType getPIDSourceType() {
        return PIDSourceType.kDisplacement;
    }

    public void setPIDSourceType(PIDSourceType p) {
    }

    public double pidReadOut() {
        return output;
    }

    public double pidGet() {
        return lifters.getFrontLifterHeight() - lifters.getBackLifterHeight();
    }
}

public class Lifters extends Subsystem {
    

    private TalonSRX frontLifter;
    private TalonSRX backLifter;
    private VictorSPX lifterDrive;
    private IrSensor frontIR;
    private IrSensor backIR;
    private int frontPotZero;
    private int backPotZero;
    private PIDController syncPID;
    private DiffPIDDriver syncDriver;

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

        syncDriver = new DiffPIDDriver(this);

        // TODO: Tune PID
        syncPID = new PIDController(
            Constants.kLifterP,
            Constants.kLifterI,
            Constants.kLifterD,
            Constants.kLifterF, 
            syncDriver,
            syncDriver,
            0.02
        );
    }

    @Override
    protected void initDefaultCommand() {
    }
    public void setLifterstoCoastMode(){ //sets Lifters to coast as a method for command
        frontLifter.setNeutralMode(NeutralMode.Coast);
        backLifter.setNeutralMode(NeutralMode.Coast);
        lifterDrive.setNeutralMode(NeutralMode.Coast);
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

        if(frontHeight > backHeight) {
            if(output >= 0) {
                extendFront(output / 2);
                extendBack(output);
            } else {
                extendBack(output / 2);
                extendFront(output);
            }
        } else if(backHeight > frontHeight) {
            if(output <= 0) {
                extendFront(output / 2);
                extendBack(output);
            } else {
                extendBack(output / 2);
                extendFront(output);
            }
        } else {
            extendFront(output);
            extendBack(output);
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

    public boolean isFrontOverLedge(boolean isHigh) {
        // double comparison = isHigh ? Constants.kHighPlatformHeight : Constants.kLowPlatformHeight;
        // return frontIR.getInches() <= comparison;
        return false;
    }

    public boolean isBackOverLedge(boolean isHigh) {
        // double comparison = isHigh ? Constants.kHighPlatformHeight : Constants.kLowPlatformHeight;
        // return backIR.getInches() <= comparison;
        return false;
    }

    public void debug() {
        SmartDashboard.putBoolean("Front Lifter Retracted", isFrontRetracted());
        SmartDashboard.putBoolean("Front Lifter Extended", isFrontExtended());
        SmartDashboard.putBoolean("Back Lifter Retracted", isBackRetracted());
        SmartDashboard.putBoolean("Back Lifter Extended", isBackExtended());
        SmartDashboard.putNumber("Pot Front value ", getFrontHeight());
        SmartDashboard.putNumber("Pot Back value ", getBackHeight());
        SmartDashboard.putNumber("Pot Back velocity ", getBackVelocity());
        SmartDashboard.putNumber("Front IR ", frontIR.getValue());
        SmartDashboard.putNumber("Back IR ", backIR.getValue());
        SmartDashboard.putNumber("Adjusted Front Pot", getFrontLifterHeight());
        SmartDashboard.putNumber("Adjusted Back Pot", getBackLifterHeight());
        SmartDashboard.putNumber("Adjusted Pot Diff", getFrontLifterHeight() - getBackLifterHeight());
        SmartDashboard.putNumber("Diff PID Output", syncDriver.pidReadOut());
    }
}