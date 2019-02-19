package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import static java.lang.Math.abs;
import frc.robot.Constants;

public class SetLiftHeightBangBang extends Command {
    private double targetHeight;
    // Height measered inches
    public SetLiftHeightBangBang(double height) {
        super("GoToHeight");
        requires(Robot.elevator);
        targetHeight = height;
    }

    protected void initialize() {
    	Robot.elevator.setBreak(false);
    }

    @Override
    protected void execute(){
        double diff = Robot.elevator.getHeight() - targetHeight;
        int diffSign = (int) Math.signum(diff);
        if (abs(diff) > Constants.kFAR_AWAY_DISTANCE) {
            Robot.elevator.setPower(-diffSign * Constants.kFAR_AWAY_POWER);
            Robot.elevator.setBreak(false);
         // we special case going to 0 because we need to hit it almost exactly.
        } else if (abs(diff) > Constants.kCLOSE_DISTANCE || (
        		targetHeight <=0.01 && abs(diff) > 0.1)) {
            Robot.elevator.setPower(-diffSign * Constants.kCLOSE_POWER);
            Robot.elevator.setBreak(false);
        } else {
            Robot.elevator.setPower(0);
            Robot.elevator.setBreak(true);
        }
    }
    
    @Override
    public boolean isFinished() {
    	double diff = Robot.elevator.getHeight() - targetHeight; // TODO make this a method
    	if(targetHeight <= 0.01) {
    		return abs(diff) < 0.1; 
    	} else {
    		return Math.abs(diff) < Constants.kCLOSE_DISTANCE;
    	}
   }
    @Override
    protected void end () {
    	Robot.elevator.setPower(0);
    	Robot.elevator.setBreak(true);
    }
    protected void interrupted () {
    	end();
    }
}