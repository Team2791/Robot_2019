package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DriveLifterWheel;
import frc.robot.commands.DriveLifterWheelBackIR;
import frc.robot.commands.DriveLifterWheelFrontIR;
import frc.robot.commands.ExtendBothLifters;
import frc.robot.commands.RetractBackLifter;
import frc.robot.commands.RetractFrontLifter;
import frc.robot.subsystems.Lifters;
import frc.robot.commands.setLiftersToCoast;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Constants;

public class PlatformAuto3 extends CommandGroup {
    public PlatformAuto3 (){
        addSequential(new ExtendBothLifters());
        addSequential(new DriveLifterWheelBackIR());
        addSequential(new RetractBackLifter(-0.75));
        addSequential(new setLiftersToCoast());
        addSequential(new DriveLifterWheelFrontIR());
        addSequential(new RetractFrontLifter(-0.75));
    }
}