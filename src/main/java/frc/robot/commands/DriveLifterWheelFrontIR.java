package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class DriveLifterWheelFrontIR extends Command {
  public DriveLifterWheelFrontIR() {
  }

  protected void execute() {
      Robot.lifters.driveMotor(Constants.kLifterDrivePower);
      if (Robot.lifters.getFrontIR()>=1100){
        Robot.lifters.driveMotor(0);
    }
    else{
        Robot.lifters.driveMotor(Constants.kLifterDrivePower);
    }
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
    Robot.lifters.driveMotor(0);
  }
}
