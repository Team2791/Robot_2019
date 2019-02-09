package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        // double maxVel = Constants.kFrontLifterRange / Constants.kLiftTime;
        // if(output > 1) {
        //     output = 1;
        // } else if(output < -1) {
        //     output = -1;
        // }

        // frontLifter.set(ControlMode.Velocity, output * maxVel);
        frontLifter.set(ControlMode.PercentOutput, output);
    }

    public void extendBack(double output) {
        // double maxVel = Constants.kBackLifterRange / Constants.kLiftTime;
        // if(output > 1) {
        //     output = 1;
        // } else if(output < -1) {
        //     output = -1;
        // }

        // backLifter.set(ControlMode.Velocity, output * maxVel);
        backLifter.set(ControlMode.PercentOutput, output);
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
    //    System.out.println("Front: " + isFrontRetracted() + ", " + isFrontExtended());
    //     System.out.println("Back: " + isBackRetracted() + ", " + isBackExtended());
    //     System.out.println("Pots: " + getFrontHeight() + ", " + getBackHeight());
    //     System.out.println("IRs: " + frontIR.getInches() + ", " + backIR.getInches());
        SmartDashboard.putString("Front Lifters ", Boolean.toString(isFrontRetracted()));
        SmartDashboard.putString("Front Lifters ", Boolean.toString(isFrontExtended()));
        SmartDashboard.putString("Front Lifters ", Boolean.toString(isBackRetracted()));
        SmartDashboard.putString("Front Lifters ", Boolean.toString(isBackExtended()));
        SmartDashboard.putString("Pot Front value ", Double.toString(getFrontHeight()));
        SmartDashboard.putString("Pot Back value ", Double.toString(getBackHeight()));
        SmartDashboard.putString("Front IR ", Double.toString(frontIR.getInches()));
        SmartDashboard.putString("Back IR ", Double.toString(backIR.getInches()));
        // System.out.println("");
    }
}