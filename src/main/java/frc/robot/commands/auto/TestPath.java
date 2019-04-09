package frc.robot.commands.auto;

import java.util.function.Function;

import edu.wpi.first.wpilibj.command.CommandGroup;
// import frc.robot.Robot;


public class TestPath extends CommandGroup {
	Function<Double, Double> driveToSwitchSpeedFunction = x -> {
		if (x < 0.15) {
			return 0.4;
		} else if (x < 0.8) {
			return 0.75;
		} else {
			return 0.20;
		}
	};
	Function<Double, Double> backupFromSwitchSpeedFunction = x -> {
		if (x < 0.5) {
			return -0.6;
		} else {
			return -0.30;
		}
	};
	
	Function<Double, Double> driveIntoCubeSpeedFunc = x -> {
		if(x < 0.5) {
			return 0.6;
		} else {
			return 0.35;
		}
	};
	
	public TestPath(boolean isRight) {
        if(isRight){
            //Normally you should put the right side path here
            //ADD A NUMBER TO MAKE A TIME OUT
            addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_RIGHT, driveToSwitchSpeedFunction));

        }
        else{
            //Normally you should put the left side path here
            addSequential(new RunPath(ShakerPaths.FROM_CENTER.SWITCH_RIGHT, driveToSwitchSpeedFunction));
        }
	}
}