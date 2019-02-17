package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.*;

public class SetIntakeMotor extends Command {
    private double speedSetPoint;

    public SetIntakeMotor(double speed){
        speedSetPoint = speed;
    }

    public void execute() {
        Robot.cargoManipulator.setIntakeMotor(speedSetPoint);
    }

    public void end() {
        
    }

    public boolean isFinished() {
         return true;
    }
    
}