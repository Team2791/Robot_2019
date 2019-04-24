package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetLevel3CompletionState extends Command {
    private boolean state;

    public SetLevel3CompletionState(boolean targetState) {
        state = targetState;
    }
    public void initialize(){
        Robot.lifters.setLevel3CompletionState(state);
    }
    public void execute() {
        
    }

    public boolean isFinished() {
        return true;
    }

    @Override
    protected void end(){}
}