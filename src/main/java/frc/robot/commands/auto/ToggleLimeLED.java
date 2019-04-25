package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.util.LedMode;

public class ToggleLimeLED extends Command {
    private boolean state;

    public ToggleLimeLED(Boolean goal) {
        this.state = goal;
    }

    public void execute() {
        if(state==true){
            Robot.limelight.setLed(LedMode.On);
        }
        else{
            Robot.limelight.setLed(LedMode.Off);
        } 
    }

    public boolean isFinished() {
        return true;
    }

    @Override
    protected void end(){}
}