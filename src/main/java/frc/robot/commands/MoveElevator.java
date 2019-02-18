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
    public void initialize(){
        // commented this out because break hasn't been tested yet
        Robot.elevator.setBreak(false);
    }

    public void execute() {
        Robot.elevator.setPower(-stick.getRawAxis(1));
    }

    public void end() {
        Robot.elevator.setPower(0);
        Robot.elevator.setBreak(true);
    }

    public boolean isFinished() {
        return false;
    }
}