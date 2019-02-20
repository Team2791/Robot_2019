package frc.robot.commands.Lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class DriveLifterWheelBackIR extends Command {
    public DriveLifterWheelBackIR() {
    }

    protected void execute() {
        Robot.lifters.driveMotor(Constants.kLifterDrivePower);
        // if (Robot.lifters.getBackIR() >= Constants.kBackPlatformCutoff) {
        //     Robot.lifters.driveMotor(0);
        // }
        // else{
        //     Robot.lifters.driveMotor(Constants.kLifterDrivePower);
        // }
    }

    protected boolean isFinished() {
        return Robot.lifters.isBackOverLedge(true);
    }

    protected void end() {
        Robot.lifters.driveMotor(0);
    }
}
