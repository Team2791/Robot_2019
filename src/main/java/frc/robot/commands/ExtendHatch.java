package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchManipulator;
import frc.robot.*;

public class ExtendHatch extends Command {
    public void execute() {
        Robot.hatchManipulator.setExtender(true);
    }

    public boolean isFinished() {
        return true;
    }
}
