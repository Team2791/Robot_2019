package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class MoveElevator extends Command{
    private Joystick stick;
    public MoveElevator(Joystick stick) {
        super("MoveElevator");
        this.stick = stick;
        requires(Robot.elevator);
    }

    public void execute() {
        Robot.elevator.setPower(-stick.getRawAxis(5));
    }

    public void end() {
        Robot.elevator.setPower(0);
    }

    public boolean isFinished() {
        return false;
    }
}