package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchManipulator;
import frc.robot.Robot;

public class OpenGrabber extends Command {
    public OpenGrabber() {
        super("OpenGrabber");
        requires(Robot.hatchManipulator);
    }
    public void execute() {
        Robot.hatchManipulator.openGrabber();
    }

    public boolean isFinished() {
        return true;
    }
}