package frc.robot.commands.CargoManipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;
//import frc.robot.Constants;
//This command group raises the cargo intake arms//, and then sets the intake motor speed to kCargoIntakeMotorStallSpeed
public class ReleaseCargoIntake extends CommandGroup {
    public ReleaseCargoIntake() {
        addSequential(new SetCargoControlsFalse());
        addSequential(new RaiseCargo());
        //addSequential(new SetIntakeMotor(Constants.kCargoIntakeMotorStallSpeed));
    }
}