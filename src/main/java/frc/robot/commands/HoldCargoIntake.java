package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.SetIntakeMotor;

public class HoldCargoIntake extends CommandGroup {
    public HoldCargoIntake (){
        addSequential(new DropCargo());
        addSequential(new SetIntakeMotor(0.75));
        addSequential(new CheckForBall());
        addSequential(new DoNothing(), 0.33);
        addSequential(new SetIntakeMotor(0.1));
        addSequential(new RaiseCargo());
    }
}