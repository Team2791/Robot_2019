package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Lifter.DriveLifterWheelBackIR;
import frc.robot.commands.Lifter.DriveLifterWheelFrontIR;
import frc.robot.commands.Lifter.ExtendBothLifters;
import frc.robot.commands.Lifter.RetractBackLifter;
import frc.robot.commands.Lifter.RetractFrontLifterNoShock;
import frc.robot.commands.DoNothing;
import frc.robot.commands.Elevator.SetLiftHeightMagicMotion;
import frc.robot.commands.auto.DriveForwardForTime;
import frc.robot.commands.auto.TurnOffCompressor;
import frc.robot.Robot;
// import edu.wpi.first.wpilibj.Joystick;

public class PlatformAuto2 extends CommandGroup {
    // public Joystick sticky;
    public PlatformAuto2 (){
        // this.sticky = stick;
        if(Robot.lifters.getLevel3CompletionState()==true){
            //This will be true if you press this button while on top of level 3
        // addSequential(new TurnOffCompressor());
        addSequential(new AutoSetLifterPots());
        addSequential(new SetLiftHeightMagicMotion(Constants.kElevatorMinHeight + 3));
        addSequential(new ExtendBothLifters(Constants.kLifterExtensionSpeed,true, Constants.kLifterExtraExtensionPotTravel));
        }
        
        addSequential(new TurnOffCompressor());
        addSequential(new AutoSetLifterPots());
        addSequential(new ExtendBothLifters(Constants.kLifterExtensionSpeed,true, Constants.kLiftersLevel2PotValue));
        // addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay);
        addSequential(new DriveLifterWheelBackIR());
        // addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay);
        addSequential(new RetractBackLifter(Constants.kLifterRetractionSpeed));
        // addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay);
        addSequential(new DriveLifterWheelFrontIR());
        // addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay);
        // addSequential(new DriveForwardForTime(Constants.kCreep, Constants.kInchTime));
        addSequential(new RetractFrontLifterNoShock(Constants.kLifterRetractionSpeed, true));
        addSequential(new SetLevel2CompletionState(true));
        // addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay);
        addSequential(new DriveForwardForTime(Constants.kDrivetrainLifterCrawlSpeedEndOfSequence,Constants.kDrivetrainLifterEndOfSequenceTime));
    }
}

