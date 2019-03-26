package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class LimelightFollow extends Command {
    public LimelightFollow() {
        super("LimelightFollow");
        requires(Robot.drivetrain);
    }

    public void execute() {
        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        
        double turn = Constants.kCamTurn;
        double thrust = Constants.kCamStraight;
        
        if(tv < 1) {
            Robot.drivetrain.setMotors(thrust, thrust);
            return;
        }

        turn *= tx - Constants.kCamOffset;

        double left = Math.max(Math.min(thrust + turn, 1), -1);
        double right = Math.max(Math.min(thrust - turn, 1), -1);
        Robot.drivetrain.setMotors(left, right);
    }

    public void end() {
        Robot.drivetrain.setMotors(0, 0);
    }

    public boolean isFinished() {
        return Robot.drivetrain.getLineSensors() > 0;
    }
}