package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchManipulator;

public class CloseGrabber extends Command {
    public void execute() {
        Robot.hatchManipulator.CloseGrabber();
    }

    public boolean isFinished() {
        return true;
    }
}