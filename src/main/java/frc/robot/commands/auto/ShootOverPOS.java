package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Lifter.DriveLifterWheelBackIR;
import frc.robot.commands.Lifter.DriveLifterWheelFrontIR;
import frc.robot.commands.Lifter.ExtendBothLifters;
import frc.robot.commands.Lifter.RetractBackLifter;
import frc.robot.commands.Lifter.RetractFrontLifterNoShock;
import frc.robot.commands.DoNothing;
import frc.robot.commands.CargoManipulator.FastShootCargo;
import frc.robot.commands.Elevator.MagicMotionHatchBall;
import frc.robot.commands.auto.DriveForwardForTime;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.Elevator.MagicMotionHatchBall;
import frc.robot.commands.CargoManipulator.FullShootCargo;

public class ShootOverPOS extends CommandGroup {
    public Joystick sticky;
    public ShootOverPOS (Joystick stick){
        this.sticky = stick;
        addSequential(new MagicMotionHatchBall(sticky, Constants.kElevatorShootOverHeight,Constants.kElevatorShootOverHeight));
        addSequential(new FullShootCargo());
        addSequential(new DoNothing(), 0.5);
    }
}

