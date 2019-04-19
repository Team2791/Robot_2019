package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.util.LedMode;
import frc.robot.util.Limelight;

public class LimelightFollow extends Command {
    private double thrustBase;
    private boolean lineFollow;
    private boolean scaledFollow;
    public LimelightFollow(double thrustLevel, boolean followedByLineFollowing, boolean scaled) {
        super("LimelightFollow");
        thrustBase = thrustLevel;
        lineFollow = followedByLineFollowing;
        scaledFollow = scaled;
        requires(Robot.drivetrain);
    }

    public void initialize() {
        Robot.limelight.setLed(LedMode.On);
    }
    public void execute() {
        if(scaledFollow = false){
            double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        //tv is 0 if no targets are found, 1 if there are targets
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        //tx returns a value between -27 (center of target is left of frame) and 27 (center of target is right of frame)
        double lengthHori = NetworkTableInstance.getDefault().getTable("limelight").getEntry("thor").getDouble(0); //Horizontal sidelength of the rough bounding box (0 - 320 pixels)
        double lengthVert = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tvert").getDouble(0); //Vertical sidelength of the rough bounding box (0 - 320 pixels)
        double turn = Constants.kCamTurn;
        double thrust = thrustBase;
        
        if(tv < 1 || (lengthHori / lengthVert) > 3.1 || (lengthHori / lengthVert) < 1.0) {
            Robot.drivetrain.setMotors(thrust, thrust); //if I dont see a target, drive forward
            // Robot.drivetrain.setGreenLED(false);
            return;
        }
        // Robot.drivetrain.setGreenLED(true);
        turn *= (tx/27); //sets "turn" = to kCamTurn * the percentage of how off the target is

        double left = Math.max(Math.min(thrust + turn, 1), -1);
        double right = Math.max(Math.min(thrust - turn, 1), -1);
        Robot.drivetrain.setMotors(left, right);
        // System.out.print("Ratio = " + lengthHori / lengthVert);
        }

        else{
        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        //tv is 0 if no targets are found, 1 if there are targets
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        //tx returns a value between -27 (center of target is left of frame) and 27 (center of target is right of frame)
        double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        double lengthHori = NetworkTableInstance.getDefault().getTable("limelight").getEntry("thor").getDouble(0); //Horizontal sidelength of the rough bounding box (0 - 320 pixels)
        double lengthVert = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tvert").getDouble(0); //Vertical sidelength of the rough bounding box (0 - 320 pixels)
        double turn = Constants.kCamTurn;
        double thrust = Constants.kCamStraightSlow;
        
        if(tv < 1 || (lengthHori / lengthVert) > 3.1 || (lengthHori / lengthVert) < 1.0) {
            Robot.drivetrain.setMotors(thrust, thrust); //if I dont see a target, drive forward
            // Robot.drivetrain.setGreenLED(false);
            return;
        }
        // Robot.drivetrain.setGreenLED(true);
        thrust = Constants.kCamStraightSuperFast;
        ty = ty + 20.5;
        ty = 41 - ty;
        thrust = thrust * (ty/41);

        if(thrust < Constants.kCamStraightSlow){
            thrust = Constants.kCamStraightSlow;
        }
        
        turn *= (tx/27); //sets "turn" = to kCamTurn * the percentage of how off the target is

        double left = Math.max(Math.min(thrust + turn, 1), -1);
        double right = Math.max(Math.min(thrust - turn, 1), -1);
        Robot.drivetrain.setMotors(left, right);
        // System.out.print("Ratio = " + lengthHori / lengthVert);
        }

    }

    public void end() {
        Robot.drivetrain.setMotors(0, 0);
        // Robot.limelight.setLed(LedMode.Off);
    }

    public boolean isFinished() {
        if(lineFollow){
            return Robot.drivetrain.getLineSensors() > 0; //owo

        }
        return false;
    }
}