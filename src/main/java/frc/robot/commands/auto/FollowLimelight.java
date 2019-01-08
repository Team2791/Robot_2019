package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import main.java.frc.robot.util.Limelight;


public class FollowLimelight extends Command {
    private static double kMoveSpeed = 0.5;
    private static double kTurnSpeed = 0.5;
    private Limelight myLimelight;


    public FollowLimelight(Joystick stick){
        super("FollowLimelight");
        requires(Robot.drivetrain);
        myLimelight = new Limelight();
    }

    public void execute() {

        
        double thrust = 1;
        thrust *= kMoveSpeed;
        if(Math.abs(thrust) < 0.1){
            thrust = 0;
        }

        double turn = myLimelight.getHorizontalOffset();

        myLimelight.debug();

        turn *= kTurnSpeed;
        if(turn < 0.05 && turn > -0.05) {
            turn = 0;
        }
        double left = Math.max(Math.min(thrust - turn, 1), -1);
        double right = Math.max(Math.min(thrust + turn, 1), -1);
        
        Robot.drivetrain.setMotors(left, right);
    }

    public void end(){
        Robot.drivetrain.setMotors(0, 0);
    }

    public boolean isFinished(){
        return false;
    }
}