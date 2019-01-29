package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

class Lifters extends Subsystem {
    TalonSRX frontLifter;
    TalonSRX backLifter;
    VictorSPX lifterDrive;
    Ultrasonic frontIR;
    Ultrasonic backIR;

    public Lifters() {
        frontLifter = new TalonSRX(RobotMap.kFrontLiftTalon);
        backLifter = new TalonSRX(RobotMap.kBackLiftTalon);
        lifterDrive = new VictorSPX(RobotMap.kRollerVictor);

    }

    @Override
    protected void initDefaultCommand() {
    }

    public void extendFront(double output) {
    }

    public void extendBack(double output) {
    }

    public boolean isFrontRetracted() {
        return true;
    }

    public boolean isBackRetracted() {
        return true;
    }

    public boolean isFrontExtended() {
        return false;
    }
    
    public boolean isBackExtended() {
        return false;
    }

    public void driveMotor(double output) {
        lifterDrive.set(ControlMode.PercentOutput, output);
    }
}