package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchManipulator;
import frc.robot.*;

public class DropAligner extends Command {
    public void execute() {
        Robot.hatchManipulator.setAligner(true);
    }

    public boolean isFinished() {
        return true;
    }
}