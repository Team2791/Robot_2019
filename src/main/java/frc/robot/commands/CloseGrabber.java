package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchManipulator;
import frc.robot.*;

public class CloseGrabber extends Command {
    public void execute() {
        Robot.hatchManipulator.closeGrabber();
    }

    public boolean isFinished() {
        return true;
    }
}