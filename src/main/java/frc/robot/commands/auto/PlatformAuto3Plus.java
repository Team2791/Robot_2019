package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Lifter.DriveLifterWheelBackIR;
import frc.robot.commands.Lifter.DriveLifterWheelFrontIR;
import frc.robot.commands.Lifter.ExtendBothLifters;
import frc.robot.commands.Lifter.RetractBackLifter;
import frc.robot.commands.Lifter.RetractFrontLifterNoShock;
import frc.robot.commands.DoNothing;
import frc.robot.commands.auto.DriveForwardForTime;
import frc.robot.commands.auto.TurnOffCompressor;

public class PlatformAuto3Plus extends CommandGroup {
    public PlatformAuto3Plus(){
        addSequential(new AutoSetLifterPots()); //Think about this for a second - how can you speed this up?
        addSequential(new ExtendBothLifters(Constants.kLifterExtensionSpeed,true, Constants.kLifterExtraExtensionPotTravel)); //This is about as fast as it gets
    }
}

