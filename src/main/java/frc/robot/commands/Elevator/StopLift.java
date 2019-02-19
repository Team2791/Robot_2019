package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class StopLift extends Command {

    public StopLift() {
    	requires(Robot.elevator);
    }

    protected void initialize() {

    }

    @Override
    protected void execute() {
    	Robot.elevator.setPower(0);
}
    
    @Override
    public boolean isFinished() {
        return true;
    }
    @Override
    protected void end () {

    }
    protected void interrupted () {

    }
}