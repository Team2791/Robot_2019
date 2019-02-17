package frc.robot.commands.CargoManipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
//import frc.robot.commands.DoNothing;
//This command group raises the cargo intake arms, and then sets the intake motor speed to kCargoIntakeMotorStallSpeed
public class FastShootCargo extends CommandGroup {
    public FastShootCargo() {
        addSequential(new SetCargoControlsTrue());
        addSequential(new RaiseCargo());
        addSequential(new SetIntakeMotor(Constants.kCargoFastShootMotorSpeed));
        //addSequential(new DoNothing(),0.25);
        //addSequential(new SetOverrideBooleanFalse());
    }
}