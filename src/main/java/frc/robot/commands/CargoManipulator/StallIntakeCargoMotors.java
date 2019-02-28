package frc.robot.commands.CargoManipulator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Constants;

//This command does nothing when run, but only completes when the cargo switch state is true.
public class StallIntakeCargoMotors extends Command {
    public StallIntakeCargoMotors(){
        super("StallIntakeCargoMotors");
        requires(Robot.cargoManipulator);
    }
    public void execute() {
        Robot.cargoManipulator.setIntakeMotor(Constants.kCargoIntakeMotorStallSpeed);
        // if(Robot.cargoManipulator.getCargoSwitchState()==true && Robot.cargoManipulator.getCargoControls()==false){
        //     Robot.cargoManipulator.setIntakeMotor(Constants.kCargoIntakeMotorStallSpeed);
        // }

        // else if(Robot.cargoManipulator.getCargoSwitchState()==false && Robot.cargoManipulator.getCargoControls()==false){
        //     Robot.cargoManipulator.setIntakeMotor(0.0);
        // }
        
    }

    public boolean isFinished() {
        return true;
    }
}