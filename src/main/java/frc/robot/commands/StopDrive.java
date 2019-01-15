package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.Limelight.Limelight;

public class StopDrive extends Command{

    private Limelight myLimelight = Robot.limelight;
    public StopDrive(){
        super("StopDrive");
        requires(Robot.drivetrain);
    }

    public void execute(){
        Robot.drivetrain.setMotors(0, 0);
        myLimelight.debug();
    }

    public void end(){
        Robot.drivetrain.setMotors(0, 0);
    }

    public boolean isFinished(){
        return true;
    }
}