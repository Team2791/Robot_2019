package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class StallElevator extends Command {
    private int frameCounter;
    public StallElevator() {
        super("StallElevator");
        requires(Robot.elevator);
        frameCounter = 0;
    }

    public void initialize() {
        frameCounter = 0;
    }

    public void execute() {
        Robot.elevator.setBreak(true);
        if(!Robot.elevator.closeToBottom()) {
            Robot.elevator.setPowerUnsafe(Constants.kElevatorMinPower);
        }
        ++frameCounter;
    }

    public void end() {
        Robot.elevator.setPowerUnsafe(0);
    }

    public boolean isFinished() {
        return frameCounter > 15;
    }
}