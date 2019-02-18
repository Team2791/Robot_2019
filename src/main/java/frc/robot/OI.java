package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.controller.AnalogButton;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.HatchManipulator.GetPanelAutomatedHeld;
import frc.robot.commands.HatchManipulator.GetPanelAutomatedRelease;
import frc.robot.commands.HatchManipulator.ScorePanelAutomatedHeld;
import frc.robot.commands.HatchManipulator.ScorePanelAutomatedRelease;
import frc.robot.commands.CargoManipulator.FastShootCargo;
import frc.robot.commands.CargoManipulator.HoldCargoIntake;
import frc.robot.commands.CargoManipulator.ReleaseCargoIntake;
import frc.robot.commands.CargoManipulator.SlowShootCargo;
import frc.robot.commands.CargoManipulator.StopCargoMotor;
import frc.robot.commands.CargoManipulator.CargoHumanPlayerIntake;

public class OI {
    private Joystick driverStick = new Joystick(0);
    private Joystick operatorStick = new Joystick(1);
    private Button driveButton;
    private Button driverA, driverB;
    private Button operatorRB, operatorLT, operatorLB, operatorRT;

    public OI() {
        initButtons();
        driveButton = new AnalogButton(driverStick, 3, 2, 0, 0.2);
        driveButton.whileHeld(new DriveWithJoystick(driverStick, 0.1));
        
        driverA.whenPressed(new GetPanelAutomatedHeld());
        driverA.whenReleased(new GetPanelAutomatedRelease());
        driverB.whenPressed(new ScorePanelAutomatedHeld());
        driverB.whenReleased(new ScorePanelAutomatedRelease());


        operatorRT.whenPressed(new HoldCargoIntake()); 
        operatorRT.whenReleased(new ReleaseCargoIntake());
        operatorRB.whenPressed(new CargoHumanPlayerIntake());
        operatorRB.whenReleased(new StopCargoMotor());
        operatorLB.whenPressed(new SlowShootCargo());
        operatorLB.whenReleased(new StopCargoMotor());
        operatorLT.whenPressed(new FastShootCargo());
        operatorLT.whenReleased(new StopCargoMotor());
    }

    private void initButtons(){
        try{
            driverA = new JoystickButton(driverStick, 1);
            driverB = new JoystickButton(driverStick,2);
            
            operatorRB = new JoystickButton(operatorStick, 6);
            operatorLB = new JoystickButton(operatorStick, 5);
            operatorLT = new AnalogButton(operatorStick, 2);
            operatorRT = new AnalogButton(operatorStick,3);
        }
        catch (Exception error){
            System.out.println("Error Init With Buttons");
            error.printStackTrace();
        }
    }
}
