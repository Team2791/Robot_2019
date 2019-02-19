package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.OI;
import frc.robot.util.Util;



public class RunLiftWithJoystick extends Command {
	Button callingButton;
	
    public RunLiftWithJoystick(Button callingButton){
        requires(Robot.elevator);
        this.callingButton = callingButton;
    }
  
    protected void initialize(){
    	Robot.elevator.setBreak(false);
    }

    @Override
    protected void execute() {
        double speed = Util.deadzone(Constants.DEADZONE,OI.operatorStick.getRawAxis(1), 1.0) * Constants.MANUAL_POWER * Robot.elevator.speedModifier;

        if(Robot.oi.operatorLS.get()) {
	        Robot.elevator.setManualPower(speed);
        } 
        
        //else {
        //    if(Robot.elevator.getHeight() < 12) {
        //    	speed = Math.max(-0.3, speed);
        //    } //TODO NOAH FUCKED WITH THIS??
        //	Robot.elevator.setPower(speed);
        //}
        else{

        }
        Robot.elevator.setBreak(false);
    }

    @Override
    protected boolean isFinished() {
        return !callingButton.get();
    }

    @Override
    protected void end(){
    	Robot.elevator.setBreak(true);
    }
    
    @Override
    protected void interrupted(){

    }
}