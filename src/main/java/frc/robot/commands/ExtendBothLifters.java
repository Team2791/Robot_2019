package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ExtendBothLifters extends Command {
    public ExtendBothLifters() {
    }

    public void execute() {
        Robot.lifters.ExtendBoth(0.75);
    }

    public void end() {
        Robot.lifters.extendFront(0);
        Robot.lifters.extendBack(0);
    }

    public boolean isFinished() {
        return Robot.lifters.isFrontExtended() && Robot.lifters.isBackExtended();
    }
}