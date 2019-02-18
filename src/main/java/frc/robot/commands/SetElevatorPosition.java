package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetElevatorPosition extends Command {
    private int errorThreshold;
    private int target;
    private int error;

    public SetElevatorPosition(int target) {
        super("SetElevatorPosition");
        requires(Robot.elevator);
        this.target = target;
        errorThreshold = 15;
        error = Integer.MAX_VALUE;
    }

    public SetElevatorPosition(int target, int errorThreshold) {
        super("SetElevatorPosition");
        requires(Robot.elevator);
        this.target = target;
        this.errorThreshold = errorThreshold;
        error = Integer.MAX_VALUE;
    }

    public void initialize() {
        Robot.elevator.setBreak(false);
    }

    public void execute() {
        error = Robot.elevator.setPosition(target);
    }

    public void end() {
        Robot.elevator.setBreak(true);
        Robot.elevator.setPowerUnsafe(0);
    }

    public boolean isFinished() {
        return Math.abs(error) < errorThreshold;
    }
}