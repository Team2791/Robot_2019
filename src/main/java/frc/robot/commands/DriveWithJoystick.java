package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveWithJoystick extends Command {
    private Joystick stick;
    private boolean isSquaredTurn;

    public DriveWithJoystick(Joystick stick) {
        super("DriveWithJoystick");
        requires(Robot.drivetrain);
        this.stick = stick;
    }

    public void execute() {
        double thrust = stick.getRawAxis(3) - stick.getRawAxis(2);
        if(Math.abs(thrust) < 0.1) {
            thrust = 0;
        }
        double turn = stick.getRawAxis(0);

        if(isSquaredTurn) {
            turn *= Math.abs(turn);
        }

        if(turn < 0.05 && turn > -0.05) {
            turn = 0;
        }

        double left = Math.max(Math.min(thrust - turn, 1), -1);
        double right = Math.max(Math.min(thrust + turn, 1), -1);
        
        Robot.drivetrain.setMotors(left, right);
    }

    public void end() {
        Robot.drivetrain.setMotors(0, 0);
    }

    public boolean isFinished() {
        return false;
    }
}