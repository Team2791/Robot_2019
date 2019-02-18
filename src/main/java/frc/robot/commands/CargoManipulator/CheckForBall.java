package frc.robot.commands.CargoManipulator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//This command does nothing when run, but only completes when the cargo switch state is true.
public class CheckForBall extends Command {

    public CheckForBall() {
        super("CheckForBall");
        requires(Robot.cargoManipulator);
        
    }

    public void execute() {
        
    }

    public boolean isFinished() {
        return Robot.cargoManipulator.getCargoSwitchState();
    }
}