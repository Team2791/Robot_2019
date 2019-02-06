package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RetractFrontLifter extends Command {
    public RetractFrontLifter() {
    }

    public void execute() {
        Robot.lifters.extendFront(-0.75);
    }

    public void end() {
        Robot.lifters.extendFront(0);
    }

    public boolean isFinished() {
        return Robot.lifters.isFrontRetracted();
    }
}