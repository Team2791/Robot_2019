package frc.robot.commands.auto;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class setLiftersToCoast extends Command {
    public setLiftersToCoast(){
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