package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoSetElevatorPot extends Command {
    public AutoSetElevatorPot() {
        super("AutoSetElevatorPot");
        requires(Robot.elevator);
    }

    public void execute() {
        Robot.elevator.zeroPot();
    }

    public boolean isFinished() {
        return true;
    }
}