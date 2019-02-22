package frc.robot.commands.Elevator;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;

public class MagicMotionHatchBall extends CommandGroup{
private Joystick stick;

    public MagicMotionHatchBall (Joystick stick, double HatchHeight, double BallHeight){

        this.stick = stick;

        if(Robot.cargoManipulator.getCargoSwitchState()==true || stick.getRawButton(7)){
            addSequential(new SetLiftHeightMagicMotion(BallHeight));
        }
        else{
            addSequential(new SetLiftHeightMagicMotion(HatchHeight));
        }
        }
    }