package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class AutoSetElevatorPot extends Command {
    public AutoSetElevatorPot() {
        super("AutoSetElevatorPot");
        requires(Robot.elevator);
    }

    public void execute() {
        Robot.elevator.setPower(-1);
    }

    public void end() {
        Robot.elevator.setPower(0);
        Robot.elevator.zeroPot();
    }

    public boolean isFinished() {
        return Robot.elevator.atBottom();
    }
}