package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.Lifter.DriveLifterWheelBackIR;
import frc.robot.commands.Lifter.DriveLifterWheelFrontIR;
import frc.robot.commands.Lifter.ExtendBothLifters;
import frc.robot.commands.Lifter.RetractBackLifter;
import frc.robot.commands.Lifter.RetractFrontLifterNoShock;
import frc.robot.commands.auto.DriveForwardForTime;
// import edu.wpi.first.wpilibj.Joystick;

public class PlatformAuto3 extends CommandGroup {
    // public Joystick sticky;
    public PlatformAuto3 (){
        if(Robot.lifters.getLevel2CompletionState()==true){
            //This if statement will run if the driver has successfully climbed to level 2 already
            // addSequential(new TurnOffCompressor());
            addSequential(new AutoSetLifterPots()); //Think about this for a second - how can you speed this up?
            addSequential(new ExtendBothLifters(Constants.kLifterExtensionSpeed,true, Constants.kLifterFrontPotTopTravel - Constants.kLiftersLevel2PotValue)); //This is about as fast as it gets
            // addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay); //FOR TESTING
            addSequential(new DriveLifterWheelBackIR()); //Think about this for a second - how can you speed this up?
            // addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay); //FOR TESTING
            addSequential(new RetractBackLifter(Constants.kLifterRetractionSpeed)); //Literally as fast as it possibly can get
            // addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay); //FOR TESTING
            addSequential(new DriveLifterWheelFrontIR()); //Think about this for a second - how can you speed this up?
            // addSequential(new DriveForwardForTime(Constants.kCreep, Constants.kInchTime)); //Add this back in if the bolts become an issue
            // addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay); //FOR TESTING
            addSequential(new RetractFrontLifterNoShock(Constants.kLifterRetractionSpeed,false)); //Think about this for a second - how can you speed this up?
            // addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay); //FOR TESTING
            addSequential(new DriveForwardForTime(Constants.kDrivetrainLifterCrawlSpeedEndOfSequence,Constants.kDrivetrainLifterEndOfSequenceTimeStagger));

        }
        else{

            addSequential(new TurnOffCompressor());
            addSequential(new AutoSetLifterPots()); //Think about this for a second - how can you speed this up?
            addSequential(new ExtendBothLifters(Constants.kLifterExtensionSpeed,false,0)); //This is about as fast as it gets
            // addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay); //FOR TESTING
            addSequential(new DriveLifterWheelBackIR()); //Think about this for a second - how can you speed this up?
            // addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay); //FOR TESTING
            addSequential(new RetractBackLifter(Constants.kLifterRetractionSpeed)); //Literally as fast as it possibly can get
            // addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay); //FOR TESTING
            addSequential(new DriveLifterWheelFrontIR()); //Think about this for a second - how can you speed this up?
            // addSequential(new DriveForwardForTime(Constants.kCreep, Constants.kInchTime)); //Add this back in if the bolts become an issue
            // addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay); //FOR TESTING
            addSequential(new RetractFrontLifterNoShock(Constants.kLifterRetractionSpeed,false)); //Think about this for a second - how can you speed this up?
            addSequential(new SetLevel3CompletionState(true));
            // addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay); //FOR TESTING
            addSequential(new DriveForwardForTime(Constants.kDrivetrainLifterCrawlSpeedEndOfSequence,Constants.kDrivetrainLifterEndOfSequenceTime));
    
    }
    }
}


