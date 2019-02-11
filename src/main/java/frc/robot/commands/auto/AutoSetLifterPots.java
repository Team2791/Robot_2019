package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoSetLifterPots extends Command {
    public AutoSetLifterPots() {
        super("AutoSetLifterPots");
        requires(Robot.lifters);
    }

    public void execute() {
        Robot.lifters.ExtendBoth(-.75);
    }

    public void end() {
        Robot.lifters.extendFront(0);
        Robot.lifters.extendBack(0);
        Robot.lifters.zeroPots();
    }

    public boolean isFinished() {
        return Robot.lifters.isFrontRetracted() && Robot.lifters.isBackRetracted();
    }
}