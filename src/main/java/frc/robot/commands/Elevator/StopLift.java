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
        // if(Robot.elevator.getElevatorHeight()<100){
        //     Robot.elevator.setPowerUnsafe(0);
        // }
        // else if(Robot.elevator.getMagicFinished()==true){
        //     Robot.elevator.setPowerUnsafe(0.06);
        // }
        // else{
        //     Robot.elevator.setPowerUnsafe(0);
        // }
        Robot.elevator.setPowerUnsafe(0);
        Robot.elevator.setBreak(true);
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