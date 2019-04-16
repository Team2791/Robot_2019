package frc.robot.commands.auto;

import java.util.function.Function;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DoNothing;
import frc.robot.Constants;
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
	
	public TestPath(boolean isRocket) {
        if(isRocket){
            //Normally you should put the right side path here
            //ADD A NUMBER TO MAKE A TIME OUT
			//addSequential(new RunPath(ShakerPaths.FROM_CENTER.STATION_TO_CENTER_ROCKET, -0.5, RunPath.Direction.BACKWARDS));
			//addSequential(new RunPath(ShakerPaths.FROM_CENTER.CENTER_ROCKET_FINISH, -0.5,RunPath.Direction.BACKWARDS));
			
			//Goes from right HP to Far Side Rocket, than turns and goes in to score direction. Driver still scores
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT_HP.HP_TO_FAR_SIDE_ROCKET,-0.5,RunPath.Direction.FORWARDS));
			addSequential(new DoNothing(),Constants.kFarRocketPathDelay);
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT_HP.FAR_SIDE_ROCKET_TO_SCORE,-0.5,RunPath.Direction.FORWARDS));
			

        }
        else{
            //Normally you should put the left side path here
            //addSequential(new RunPath(ShakerPaths.FROM_CENTER.STATION_TO_CENTER_ROCKET, 0.5,RunPath.Direction.BACKWARDS));
			//addSequential(new RunPath(ShakerPaths.FROM_CENTER.CENTER_ROCKET_FINISH, 0.5,RunPath.Direction.BACKWARDS));

			//Goes from right HP to near Cargoship Bay 1, than turns and drives into Bay 1
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT_HP.HP_TO_NEAR_CARGSHIP_BAY1,-0.5,RunPath.Direction.FORWARDS));
			addSequential(new DoNothing(),Constants.kCargoshipBay1Delay);
			addSequential(new RunPath(ShakerPaths.FROM_RIGHT_HP.NEAR_CARGOSHIP_BAY1_TO_SCORE,-0.5,RunPath.Direction.FORWARDS));
		}
	}
}