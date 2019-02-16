package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchManipulator;
import frc.robot.*;

public class RaiseAligner extends Command {
    public void execute() {
        Robot.hatchManipulator.setAligner(false);
    }

    public boolean isFinished() {
        return true;
    }
}