package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RetractBothLifters extends Command {
    private double output;
    public RetractBothLifters() {
        // super("RetractBothLifters");
        // requires(Robot.lifters);
        // this.output = output;
    }

    public void execute() {
        Robot.lifters.ExtendBoth(-0.75);
    }

    public void end() {
        Robot.lifters.extendFront(0);
        Robot.lifters.extendBack(0);
    }

    public boolean isFinished() {
        return Robot.lifters.isFrontRetracted() && Robot.lifters.isBackRetracted();
    }
}