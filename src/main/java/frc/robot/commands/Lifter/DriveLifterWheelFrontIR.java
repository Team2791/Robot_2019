package frc.robot.commands.Lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.DelayedBoolean;

public class DriveLifterWheelFrontIR extends Command {
    DelayedBoolean frontOverLedge;

    public DriveLifterWheelFrontIR() {
        requires(Robot.drivetrain);
        frontOverLedge = new DelayedBoolean(0.5);
    }
    protected void initialize(){
        frontOverLedge.update(false);
    }
    protected void execute() {
        double driveTrainPower = -0.07; // todo make this a constant
        //This negative value makes the drive train drive backwards
        Robot.drivetrain.setMotors(driveTrainPower, driveTrainPower);
        if(Robot.lifters.isFrontOverLedge(true)) {
            // TODO make this a constant
            Robot.lifters.driveMotor(.15);
        } else {
            Robot.lifters.driveMotor(Constants.kLifterDrivePower);
        }
    }

    protected boolean isFinished() {
        return frontOverLedge.update(Robot.lifters.isFrontOverLedge(true));
    }

    protected void end() {
        Robot.lifters.driveMotor(0);
        Robot.drivetrain.setMotors(0,0);
    }
}
