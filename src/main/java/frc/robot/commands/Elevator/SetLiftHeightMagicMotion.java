package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;
import frc.robot.util.DelayedBoolean;


public class SetLiftHeightMagicMotion extends Command {
    // Height measered inches
    private double targetHeight;
    private Elevator elevator;
    private DelayedBoolean finishDelayedBoolean;
    
    
    public SetLiftHeightMagicMotion(double height) {
        super("GoToHeight");
        requires(Robot.elevator);
        this.targetHeight = height;
        this.elevator = Robot.elevator;
        finishDelayedBoolean = new DelayedBoolean(0.3);
    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        elevator.setBreak(false);
        elevator.setTargetMagicMotion(targetHeight);
    }

    @Override
    protected void execute() {
    }

    @Override
    public boolean isFinished() {
    	double diff = Robot.elevator.getHeight() - targetHeight;
    	return finishDelayedBoolean.update(Math.abs(diff) < 0.6);
//    	if(targetHeight <= 0.01) {
//    		return abs(diff) < 0.1;
//    	} else {
//    		return Math.abs(diff) < 0.25;
//    	}
   }

    @Override
    protected void end () {
    	System.out.println("Lift magic motion done!");
    	Robot.elevator.setPower(0);
    	Robot.elevator.setBreak(true);
    }

    @Override
    protected void interrupted() {
        end();
    }
}