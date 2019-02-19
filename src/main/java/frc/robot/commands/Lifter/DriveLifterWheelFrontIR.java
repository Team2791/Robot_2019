package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class DriveLifterWheelFrontIR extends Command {
    public DriveLifterWheelFrontIR() {
    }

    protected void execute() {
        Robot.lifters.driveMotor(Constants.kLifterDrivePower);
    }

    protected boolean isFinished() {
        return Robot.lifters.isFrontOverLedge(true);
    }

    protected void end() {
        Robot.lifters.driveMotor(0);
    }
}
