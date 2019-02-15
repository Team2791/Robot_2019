package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RetractBackLifter extends Command {
    public RetractBackLifter() {
        super("RetractBackLifter");
        requires(Robot.lifters);
    }

    public void execute() {
        Robot.lifters.extendBack(-0.75);
    }

    public void end() {
        Robot.lifters.extendBack(0);
    }

    public boolean isFinished() {
        return Robot.lifters.isBackRetracted();
    }
}