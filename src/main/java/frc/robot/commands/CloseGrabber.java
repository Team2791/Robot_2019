package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchManipulator;
import frc.robot.Robot;

public class CloseGrabber extends Command {
    public void execute() {
        Robot.hatchManipulator.setGrabber(false);
    }

    public boolean isFinished() {
        return true;
    }
}