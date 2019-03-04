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
import edu.wpi.first.wpilibj.Joystick;

public class PlatformAuto3 extends CommandGroup {
    public Joystick sticky;
    public PlatformAuto3 (Joystick stick){
        this.sticky = stick;
        addSequential(new AutoSetLifterPots());
        addSequential(new ExtendBothLifters(Constants.kLifterExtensionSpeed,true,sticky,false));
        addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay);
        addSequential(new DriveLifterWheelBackIR());
        addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay);
        addSequential(new RetractBackLifter(Constants.kLifterRetractionSpeed));
        addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay);
        addSequential(new DriveLifterWheelFrontIR());
        addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay);
        addSequential(new RetractFrontLifterNoShock(Constants.kLifterRetractionSpeed,false));
        addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay);
        addSequential(new DriveForwardForTime(Constants.kDrivetrainLifterCrawlSpeedEndOfSequence,Constants.kDrivetrainLifterEndOfSequenceTime));
    }
}

