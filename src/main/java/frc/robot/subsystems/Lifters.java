package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
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
    private double proportional;
    private double feedForward;
    private int frontDangerCounter;
    private int backDangerCounter;
    private boolean enabled;

    private double pid;

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
        proportional = Constants.kLifterP;
        feedForward = Constants.kLifterF;
        SmartDashboard.putNumber("LifterKP", proportional);
        SmartDashboard.putNumber("LifterKF", feedForward);

        frontDangerCounter = 0;
        backDangerCounter = 0;
        enabled = true;
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void setLifterstoCoastMode() {
        //sets Lifters to coast as a method for command
        lifterDrive.setNeutralMode(NeutralMode.Coast);
    }

    public void extendFront(double output) {
        if(output < 0 && isFrontRetracted()) {
            frontLifter.set(ControlMode.PercentOutput, 0);
        } else if(output > 0 && isFrontExtended()) {
            frontLifter.set(ControlMode.PercentOutput, 0);
        } else {
            frontLifter.set(ControlMode.PercentOutput, output);
        }

        if(getFrontCurrent() >= Math.abs(output) * Constants.kFullDangerCurrent) {
            ++frontDangerCounter;
        } else {
            frontDangerCounter = frontDangerCounter < 1 ? 0 : frontDangerCounter - 1;
        }
        
    }

    public void extendBack(double output) {
        if(output < 0 && isBackRetracted()) {
            backLifter.set(ControlMode.PercentOutput, 0);
        } else if(output > 0 && isBackExtended()) {
            backLifter.set(ControlMode.PercentOutput, 0);
        } else {
            backLifter.set(ControlMode.PercentOutput, output);
        }
        
        if(getBackCurrent() >= Math.abs(output) * Constants.kFullDangerCurrent) {
            ++backDangerCounter;
        } else {
            frontDangerCounter = backDangerCounter < 1 ? 0 : backDangerCounter - 1;
        }
    }

    public void resetSystem() {
        enabled = true;
    }

    public void ExtendBoth(double output) {
        if(frontDangerCounter >= Constants.kDangerTimeout || backDangerCounter >= Constants.kDangerTimeout) {
            enabled = false;
        }
        
        if(!enabled) {
            extendFront(0);
            extendBack(0);
            return;
        }

        if(Math.abs(output) < 0.01) {
            extendFront(0);
            extendBack(0);
            return;
        }

        int backHeight = getBackLifterHeight();
        int frontHeight = getFrontLifterHeight();
        double diff = (double)(frontHeight - backHeight)/Constants.kLifetPotRange;
        double pTerm = diff * proportional;
        double feedback = pTerm + feedForward;
        pid = feedback;
        
        //Motor speed should not go above 1 or below -1, so if we are close to either,
        //we should only decrease the absolute value of motor outputs.
        if(Math.abs(output) < 0.5) {
            extendFront(output - feedback / 2);
            extendBack(output + feedback / 2);
        } else if(output > 0) {
            if(feedback > 0) {
                extendFront(output - feedback);
                extendBack(output);
            } else {
                extendFront(output);
                extendBack(output + feedback);
            }
        } else {
            if(feedback > 0) {
                extendFront(output);
                extendBack(output + feedback);
            } else {
                extendFront(output - feedback);
                extendBack(output);
            }
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

    public double getFrontCurrent() {
        return Robot.pdp.getCurrent(RobotMap.kPowerFrontLift);
    }

    public double getBackCurrent() {
        return Robot.pdp.getCurrent(RobotMap.kPowerBackLift);
    }

    public void debug() {
        SmartDashboard.putBoolean("Front Lifter Retracted", isFrontRetracted());
        SmartDashboard.putBoolean("Front Lifter Extended", isFrontExtended());
        SmartDashboard.putBoolean("Back Lifter Retracted", isBackRetracted());
        SmartDashboard.putBoolean("Back Lifter Extended", isBackExtended());
        SmartDashboard.putNumber("Pot Front value ", getFrontHeight());
        SmartDashboard.putNumber("Pot Back value ", getBackHeight());
        SmartDashboard.putNumber("Front IR ", frontIR.getValue());
        SmartDashboard.putNumber("Back IR ", backIR.getValue());
        SmartDashboard.putNumber("Adjusted Front Pot", getFrontLifterHeight());
        SmartDashboard.putNumber("Adjusted Back Pot", getBackLifterHeight());
        SmartDashboard.putNumber("Adjusted Pot Diff", getFrontLifterHeight() - getBackLifterHeight());
        SmartDashboard.putNumber("PID Output", pid);
        SmartDashboard.putNumber("Front Lift Current", getFrontCurrent());
        SmartDashboard.putNumber("Back Lift Current", getBackCurrent());
        proportional = SmartDashboard.getNumber("LifterKP", Constants.kLifterP);
        feedForward = SmartDashboard.getNumber("LifterKF", Constants.kLifterF);
    }
}