package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.StopDrive;

public class Drivetrain extends Subsystem{
    private Talon leftMotor;
    private Talon rightMotor;

    public Drivetrain(){
        leftMotor = new Talon(RobotMap.LEFT_MOTOR);
        rightMotor = new Talon(RobotMap.RIGHT_MOTOR);
    }

    public void initDefaultCommand(){
        setDefaultCommand(new StopDrive());
    }

    public void setMotors(double left, double right){
        leftMotor.set(left);
        rightMotor.set(-right);
    }
}