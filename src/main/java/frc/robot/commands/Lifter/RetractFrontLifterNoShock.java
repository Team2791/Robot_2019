package frc.robot.commands.Lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class RetractFrontLifterNoShock extends Command {
    private double output;

    public RetractFrontLifterNoShock(double output) {
        super("RetractFrontLifter");
        requires(Robot.lifters);
        this.output = output;
    }
    public void initialize(){
    }

    public void execute() {
        if(Robot.lifters.getFrontLifterHeight() > Constants.kLifterFrontSlowHeight){
            Robot.lifters.extendFront(Constants.kLifterFrontSlowSpeed);
        }
        else{
            Robot.lifters.extendFront(output);
        }
    }

    public void end() {
        Robot.lifters.extendFront(0);
    }

    public boolean isFinished() {
        return Robot.lifters.isFrontRetracted();
    }
}