package frc.robot.commands;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetLiftersToCoast extends Command {
    public SetLiftersToCoast(){
    }

    protected void execute() {
        Robot.lifters.setLifterstoCoastMode();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
    public void end(){

    }

}