package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.CargoManipulator;
import frc.robot.*;

public class ReleaseCargoIntake extends CommandGroup {
    public ReleaseCargoIntake() {
        addSequential(new RaiseCargo());
        addSequential(new SetIntakeMotor(0.1));
    }
}