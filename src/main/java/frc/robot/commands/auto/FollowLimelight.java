package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.Limelight.*;


public class FollowLimelight extends Command {
    private static double kMoveSpeed = 0.5;
    private static double kTurnSpeed = 0.01;
    private Limelight myLimelight;

    public FollowLimelight() {
        super("FollowLimelight");
        requires(Robot.drivetrain);
        myLimelight = Robot.limelight;
        System.out.println("In FollowLimelight");
        myLimelight.setCameraMode(CameraMode.Driver);
    }

    public void execute() {

        
        double thrust = 1;

        
        thrust *= kMoveSpeed;
        if(Math.abs(thrust) < 0.1){
            thrust = 0;
        }

        double turn = myLimelight.getHorizontalOffset();

        System.out.println(turn);
        myLimelight.debug();

        turn *= kTurnSpeed;
        if(turn < 0.05 && turn > -0.05) {
            turn = 0;
        }
        double left = Math.max(Math.min(thrust - turn, 1), -1);
        System.out.println("left: "+left);
        double right = Math.max(Math.min(thrust + turn, 1), -1);
        System.out.println("right: "+right);
        Robot.drivetrain.setMotors(left, right);
        
        myLimelight.debug();
    }

    public void end(){
        Robot.drivetrain.setMotors(0, 0);
    }

    public boolean isFinished(){
        return false;
    }
}