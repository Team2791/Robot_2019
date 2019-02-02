package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.util.IrSensor;
import frc.robot.Constants;

class Lifters extends Subsystem {
    TalonSRX frontLifter;
    TalonSRX backLifter;
    VictorSPX lifterDrive;
    IrSensor frontIR;
    IrSensor backIR;

    public Lifters() {
        frontLifter = new TalonSRX(RobotMap.kFrontLiftTalon);
        backLifter = new TalonSRX(RobotMap.kBackLiftTalon);
        lifterDrive = new VictorSPX(RobotMap.kRollerVictor);

        frontIR = new IrSensor(RobotMap.kFrontIrReadout);
        backIR = new IrSensor(RobotMap.kBackIrReadout);

        // TODO: Tune PID
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void extendFront(double output) {
        double maxVel = Constants.kFrontLifterRange / Constants.kLiftTime;
        if(output > 1) {
            output = 1;
        } else if(output < -1) {
            output = -1;
        }

        frontLifter.set(ControlMode.Velocity, output * maxVel);
    }

    public void extendBack(double output) {
        double maxVel = Constants.kBackLifterRange / Constants.kLiftTime;
        if(output > 1) {
            output = 1;
        } else if(output < -1) {
            output = -1;
        }

        backLifter.set(ControlMode.Velocity, output * maxVel);
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

    public boolean isFrontOverLedge(boolean isHigh) {
        double comparison = isHigh ? Constants.kHighPlatformHeight : Constants.kLowPlatformHeight;
        return frontIR.getInches() <= comparison;
    }

    public boolean isBackOverLedge(boolean isHigh) {
        double comparison = isHigh ? Constants.kHighPlatformHeight : Constants.kLowPlatformHeight;
        return backIR.getInches() <= comparison;
    }
}