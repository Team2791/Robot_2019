package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Lifter.DriveLifterWheelBackIR;
import frc.robot.commands.Lifter.DriveLifterWheelFrontIR;
import frc.robot.commands.Lifter.ExtendBothLifters;
import frc.robot.commands.Lifter.RetractBackLifter;
import frc.robot.commands.Lifter.RetractFrontLifter;
import frc.robot.commands.Lifter.RetractFrontLifterNoShock;
import frc.robot.commands.Lifter.SetLiftersToCoast;
import frc.robot.commands.DoNothing;
import frc.robot.commands.auto.DriveForwardForTime;

public class PlatformAuto3 extends CommandGroup {
    public PlatformAuto3 (){
        addSequential(new ExtendBothLifters(.8));
        addSequential(new DoNothing(),1);
        addSequential(new DriveLifterWheelBackIR());
        addSequential(new DoNothing(),1);
        addSequential(new RetractBackLifter(-1));
        addSequential(new DoNothing(),1);
        addSequential(new DriveLifterWheelFrontIR());
        addSequential(new DoNothing(),1);
        addSequential(new RetractFrontLifterNoShock(-1));
        addSequential(new DoNothing(),1);
        addSequential(new DriveForwardForTime(-0.18,.75));
    }
}

