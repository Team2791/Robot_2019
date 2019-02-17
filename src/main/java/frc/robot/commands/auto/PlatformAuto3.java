package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DriveLifterWheelBackIR;
import frc.robot.commands.DriveLifterWheelFrontIR;
import frc.robot.commands.ExtendBothLifters;
import frc.robot.commands.RetractBackLifter;
import frc.robot.commands.RetractFrontLifter;
import frc.robot.commands.SetLiftersToCoast;

public class PlatformAuto3 extends CommandGroup {
    public PlatformAuto3 (){
        addSequential(new ExtendBothLifters(1));
        addSequential(new DriveLifterWheelBackIR());
        addSequential(new RetractBackLifter(-1));
        addSequential(new SetLiftersToCoast());
        addSequential(new DriveLifterWheelFrontIR());
        addSequential(new RetractFrontLifter(-1));
    }
}