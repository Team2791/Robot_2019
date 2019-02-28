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
    private int frontPotZero;// = 191; // TODO remove this
    private int backPotZero;// = 342; // TODO remove this
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
        // TODO rename these to be more clear that they're the offset and take the offets I put higher in the code and put them in constants.
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
        //SmartDashboard.putNumber("LIFTER - front output", output);
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
        SmartDashboard.putNumber("LIFTER - back output", output);
        if(output < 0 && isBackRetracted()) { // stop driving once we're fully back
            backLifter.set(ControlMode.PercentOutput, 0);
        } else if(output > 0 && isBackExtended()) { // stop driving once we're fully out
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
        lifterDrive.setNeutralMode(NeutralMode.Brake);
    }

    public void ExtendBoth(double output) {
        // if(frontDangerCounter >= Constants.kDangerTimeout || backDangerCounter >= Constants.kDangerTimeout) {
        //     enabled = false;
        // }
        
        // if(!enabled) {
        //     extendFront(0);
        //     extendBack(0);
        //     return;
        // }

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
            // IDK what this is for
            extendFront(output - feedback / 2);
            extendBack(output + feedback / 2);
        } else {
            extendFront(output - feedback);
            extendBack(output + feedback);
        }
        // } else if(output > 0) {
        //     if(feedback > 0) {
        //         extendFront(output - feedback);
        //         extendBack(output);
        //     } else {
        //         extendFront(output);
        //         extendBack(output + feedback);
        //     }
        // } else { // output > 0.5. This is where we are if we're lifting    
        // if(feedback > 0) { // positive feedback means run the back harder
        //         extendFront(output);
        //         extendBack(output + feedback);
        //     } else {
        //         extendFront(output - feedback);
        //         extendBack(output);
        //     }
        // }
    }

    public void zeroPots()
    {
        if(isFrontRetracted()) {
            frontPotZero = getFrontHeightRAW();
        }

        if(isBackRetracted()) {
            backPotZero = getBackHeightRAW();
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

    private int getFrontHeightRAW() {
        return 1023 - frontLifter.getSensorCollection().getAnalogIn();
    }

    private int getBackHeightRAW() {
        return 1023 - backLifter.getSensorCollection().getAnalogIn();
    }

    public int getFrontLifterHeight() {
        return getFrontHeightRAW() - frontPotZero;
    }

    public int getBackLifterHeight() {
        return getBackHeightRAW() - backPotZero;
    }

    public int getBackVelocity() {
        return backLifter.getSensorCollection().getAnalogInVel();
    }

    public boolean isFrontOverLedge(boolean isHigh) {
        return frontIR.getValue() > Constants.k_IR_SENSOR_THREASHOLD;
    }

    public boolean isBackOverLedge(boolean isHigh) {
        return backIR.getValue() > Constants.k_IR_SENSOR_THREASHOLD;
    }

    public double getFrontCurrent() {
        return Robot.pdp.getCurrent(RobotMap.kPowerFrontLift);
    }

    public double getBackCurrent() {
        return Robot.pdp.getCurrent(RobotMap.kPowerBackLift);
    }

    public void debug() {
        SmartDashboard.putBoolean("LFT - Front Lifter Retracted", isFrontRetracted());
        SmartDashboard.putBoolean("LFT - Front Lifter Extended", isFrontExtended());
        SmartDashboard.putBoolean("LFT - Back Lifter Retracted", isBackRetracted());
        SmartDashboard.putBoolean("LFT - Back Lifter Extended", isBackExtended());
        SmartDashboard.putNumber("LFT - Pot Front value raw", getFrontHeightRAW());
        SmartDashboard.putNumber("LFT - Pot Back value raw", getBackHeightRAW());
        SmartDashboard.putNumber("LFT - Front IR raw", frontIR.getValue());
        SmartDashboard.putNumber("LFT - Back IR raw", backIR.getValue());
        SmartDashboard.putNumber("LFT - Adjusted Front Pot", getFrontLifterHeight());
        SmartDashboard.putNumber("LFT - Adjusted Back Pot", getBackLifterHeight());
        SmartDashboard.putNumber("LFT - Adjusted Pot Diff", getFrontLifterHeight() - getBackLifterHeight());
        SmartDashboard.putNumber("LFT - PID Output", pid);
        SmartDashboard.putNumber("LFT - Front Lift Current", getFrontCurrent());
        SmartDashboard.putNumber("LFT - Back Lift Current", getBackCurrent());

        proportional = SmartDashboard.getNumber("LifterKP", Constants.kLifterP);
        feedForward = SmartDashboard.getNumber("LifterKF", Constants.kLifterF);
    }
}
