package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class FollowLine extends Command {
    public FollowLine() {
        super("FollowLine");
        requires(Robot.drivetrain);
    }

    public void execute() {
        int lineSensors = Robot.drivetrain.getLineSensors();

        switch(lineSensors) {
        case 1:
            Robot.drivetrain.setMotors(Constants.kLineFollowTurnMax, Constants.kLineFollowStraight);
            break;
        case 3:
        case 11:
            Robot.drivetrain.setMotors(Constants.kLineFollowTurnMid, Constants.kLineFollowStraight);
            break;
        case 2:
        case 5:
        case 7:
            Robot.drivetrain.setMotors(Constants.kLineFollowTurnLow, Constants.kLineFollowStraight);
            break;
        case 6:
        case 0:
        case 9:
        case 15:
            Robot.drivetrain.setMotors(Constants.kLineFollowStraight, Constants.kLineFollowStraight);
            break;
        case 4:
        case 10:
        case 14:
            Robot.drivetrain.setMotors(Constants.kLineFollowStraight, Constants.kLineFollowTurnLow);
            break;
        case 12:
        case 13:
            Robot.drivetrain.setMotors(Constants.kLineFollowStraight, Constants.kLineFollowTurnMid);
            break;
        case 8:
            Robot.drivetrain.setMotors(Constants.kLineFollowStraight, Constants.kLineFollowTurnMax);
            break;
        default:
            Robot.drivetrain.setMotors(Constants.kLineFollowStraight, Constants.kLineFollowStraight);
            break;
        }

    }

    public void end() {
        Robot.drivetrain.setMotors(0, 0);
    }

    public boolean isFinished() {
        return false;
    }
}