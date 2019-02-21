package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;
import frc.robot.util.DelayedBoolean;


public class SetLiftHeightMagicMotion extends Command {
    private double targetHeight;
    private Elevator elevator;
    private DelayedBoolean finishDelayedBoolean;
    
    
    public SetLiftHeightMagicMotion(double height) {
        super("GoToHeight");
        requires(Robot.elevator);
        this.targetHeight = height;
        this.elevator = Robot.elevator;
        finishDelayedBoolean = new DelayedBoolean(0.3);
    }


    @Override
    protected void initialize() {
        elevator.setBreak(false);
        elevator.setTargetMagicMotion(targetHeight);
    }

    @Override
    protected void execute() {
    }

    @Override
    public boolean isFinished() {
        double diff = Robot.elevator.getSensorPosition() - targetHeight;
        return finishDelayedBoolean.update(Math.abs(diff) < 5);
    }

    @Override
    protected void end () {
        System.out.println("Lift magic motion done!");
        Robot.elevator.setMagicFinished(true);
    	Robot.elevator.setBreak(true);
    }

    @Override
    protected void interrupted() {
        end();
    }
}