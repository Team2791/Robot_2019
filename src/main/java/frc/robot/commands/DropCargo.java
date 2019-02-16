package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchManipulator;
import frc.robot.*;

public class DropCargo extends Command {
    public void execute() {
        Robot.cargoManipulator.setRaiser(false);
    }

    public boolean isFinished() {
         return true;
    }
    
}