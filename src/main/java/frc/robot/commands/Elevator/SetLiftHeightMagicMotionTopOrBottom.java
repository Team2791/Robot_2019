package frc.robot.commands.Elevator;

import static java.lang.Math.abs;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class SetLiftHeightMagicMotionTopOrBottom extends Command {
    // Height measured in inches
    private double targetHeight;
    private double targetMagicMotionHeight;
    private boolean finishedMagicMotion;
   
    private final double MM_TO_BANGBANG_RANGE = 4;
    
    
    public SetLiftHeightMagicMotionTopOrBottom(boolean goingToTop) {
        super("GoToHeight");
        requires(Robot.elevator);
        targetHeight = goingToTop ? Constants.kElevatorMaxHeight - 0.5 : Constants.kElevatorMinHeight;
        targetMagicMotionHeight = goingToTop ? Constants.kElevatorMaxHeight - 2.75 : Constants.kElevatorMinHeight + 2.5;
    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */

    @Override
    protected void initialize() {
        Robot.elevator.setBreak(false);
        double magicMotionHeightDiff = Robot.elevator.getHeight() - targetMagicMotionHeight;
        if (abs(magicMotionHeightDiff) < MM_TO_BANGBANG_RANGE) {
			// exit to bang bang
			finishedMagicMotion = true;
		} else {
			finishedMagicMotion = false;
	        Robot.elevator.setTargetMagicMotion(targetMagicMotionHeight);
		}
        
    }

    @Override
    protected void execute() {
    	if(!finishedMagicMotion) { // Far from Target
    		double magicMotionHeightDiff = Robot.elevator.getHeight() - targetMagicMotionHeight;
    		if (abs(magicMotionHeightDiff) < MM_TO_BANGBANG_RANGE) {
    			// exit to bang bang
    			finishedMagicMotion = true;
    		}
    	} else { // close to target
        	double diff = Robot.elevator.getHeight() - targetHeight;
        	int diffSign = (int) Math.signum(diff);

    		if (abs(diff) > 0.1) {
//    			System.out.println("-Diff sign "+ (-diffSign));
                Robot.elevator.setPower(-diffSign * Constants.kCLOSE_POWER);
                Robot.elevator.setBreak(false);
    		} else {
    			Robot.elevator.setPower(0);
                Robot.elevator.setBreak(false);
    		}
	    }
    }

    @Override
    public boolean isFinished() {
    	return abs(Robot.elevator.getHeight() - targetHeight) < 0.1;
   }

    @Override
    protected void end () {
    	System.out.println("Lift magic motion top or bottom done!");
    	Robot.elevator.setPower(0);
    	Robot.elevator.setBreak(true);
    }

    @Override
    protected void interrupted() {
        end();
    }
}