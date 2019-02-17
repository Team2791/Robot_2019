package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.CargoManipulator;
import frc.robot.*;

public class CheckForBall extends Command {
    public void execute() {
        
    }

    public boolean isFinished() {
        return Robot.cargoManipulator.getCargoSwitchState();
    }
}