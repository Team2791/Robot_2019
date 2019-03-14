package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
//TODO Anything with a comment means untested and I changed it
//TODO add a time out to this command
public class AutoSetLifterPots extends Command {
    public AutoSetLifterPots() {
        super("AutoSetLifterPots");
        requires(Robot.lifters);
    }
    public void initialize(){ //new
    } //new
    public void execute() {
        
        if(Robot.lifters.isBackRetracted()) {
            Robot.lifters.extendBack(0);
        } else {
            Robot.lifters.extendBack(-.50);
        }

        if(Robot.lifters.isFrontRetracted()) {
            Robot.lifters.extendFront(0);
        } else {
            Robot.lifters.extendFront(-.40);
        }
    }

    public void end() {
        Robot.lifters.extendFront(0);
        Robot.lifters.extendBack(0);
        Robot.lifters.zeroPots();
    }

    public boolean isFinished() {
        return Robot.lifters.isFrontRetracted() && Robot.lifters.isBackRetracted();
    }
}