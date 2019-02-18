package frc.robot.commands.CargoManipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;
//This command group sets the cargo controls boolean to false, and then raises the cargo intake arms
public class ReleaseCargoIntake extends CommandGroup {
    public ReleaseCargoIntake() {
        addSequential(new SetCargoControlsFalse());
        addSequential(new RaiseCargo());
    }
}