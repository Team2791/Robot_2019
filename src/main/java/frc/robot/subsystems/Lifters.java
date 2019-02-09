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
    TalonSRX frontLifter;
    TalonSRX backLifter;
    VictorSPX lifterDrive;
    IrSensor frontIR;
    IrSensor backIR;

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
        // double maxVel = Constants.kBackLifterRange / Constants.kLiftTime;
        // if(output > 1) {
        //     output = 1;
        // } else if(output < -1) {
        //     output = -1;
        // }

        // backLifter.set(ControlMode.Velocity, output * maxVel);
        int backHeight = getBackHeight() - Constants.kBackLifterPotMin;
        int frontHeight = getFrontHeight() - Constants.kFrontLifterPotMin;

        if(frontHeight > backHeight) {
            if(output >= 0) {
                frontLifter.set(ControlMode.PercentOutput, output / 2);
                backLifter.set(ControlMode.PercentOutput, output);
            } else {
                frontLifter.set(ControlMode.PercentOutput, output);
                backLifter.set(ControlMode.PercentOutput, 3 * output / 4);
            }
        } else if(backHeight > frontHeight) {
            if(output <= 0) {
                frontLifter.set(ControlMode.PercentOutput, 3 * output / 4);
                backLifter.set(ControlMode.PercentOutput, output);
            } else {
                frontLifter.set(ControlMode.PercentOutput, output);
                backLifter.set(ControlMode.PercentOutput,  output / 2);
            }
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

    public int getFrontHeight() {
        return 1023 - frontLifter.getSensorCollection().getAnalogIn();
    }

    public int getBackHeight() {
        return 1023 - backLifter.getSensorCollection().getAnalogIn();
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
        SmartDashboard.putString("Pot Front value ", Double.toString(getFrontHeight()));
        SmartDashboard.putString("Pot Back value ", Double.toString(getBackHeight()));
        SmartDashboard.putString("Front IR ", Integer.toString(frontIR.getValue()));
        SmartDashboard.putString("Back IR ", Integer.toString(backIR.getValue()));
        // System.out.println("");
    }
}