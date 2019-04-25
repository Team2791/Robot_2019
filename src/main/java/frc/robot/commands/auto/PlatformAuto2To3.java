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

public class PlatformAuto2To3 extends CommandGroup {
    public PlatformAuto2To3(){
        addSequential(new AutoSetLifterPots()); //Think about this for a second - how can you speed this up?
        addSequential(new ExtendBothLifters(Constants.kLifterExtensionSpeed,true, 360 - Constants.kLiftersLevel2PotValue)); //This is about as fast as it gets
        addSequential(new DriveLifterWheelBackIR()); //Think about this for a second - how can you speed this up?
        addSequential(new RetractBackLifter(Constants.kLifterRetractionSpeed)); //Literally as fast as it possibly can get
        addSequential(new DriveLifterWheelFrontIR()); //Think about this for a second - how can you speed this up?
        addSequential(new RetractFrontLifterNoShock(Constants.kLifterRetractionSpeed,false)); //Think about this for a second - how can you speed this up?
        addSequential(new DriveForwardForTime(Constants.kDrivetrainLifterCrawlSpeedEndOfSequence,Constants.kDrivetrainLifterEndOfSequenceTimeStagger));
    }
}

