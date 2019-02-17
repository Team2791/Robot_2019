package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.controller.AnalogButton;
import frc.robot.commands.CloseGrabber;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.DropAligner;
import frc.robot.commands.ExtendHatch;
import frc.robot.commands.GetPanelAutomatedHeld;
import frc.robot.commands.GetPanelAutomatedRelease;
import frc.robot.commands.RaiseAligner;
import frc.robot.commands.RetractHatch;
import frc.robot.commands.ScorePanelAutomatedHeld;
import frc.robot.commands.ScorePanelAutomatedRelease;
import frc.robot.commands.OpenGrabber;
import frc.robot.commands.RaiseCargo;
import frc.robot.commands.DropCargo;

public class OI {
    private Joystick driverStick = new Joystick(0);
    private Joystick operatorStick = new Joystick(1);
    private Button driveButton;
    private Button driverA, driverB;
    public Button operatorA, operatorB;

    public OI() {
        driverStick = new Joystick(0);
        initButtons();
        driveButton = new AnalogButton(driverStick, 3, 2, 0, 0.2);
        driveButton.whileHeld(new DriveWithJoystick(driverStick, 0.1));
        operatorA.whenPressed(new GetPanelAutomatedHeld());
        operatorA.whenReleased(new GetPanelAutomatedRelease());
        operatorB.whenPressed(new ScorePanelAutomatedHeld());
        operatorB.whenReleased(new ScorePanelAutomatedRelease());

    }
    private void initButtons(){
        try{
            operatorA = new JoystickButton(operatorStick, 1);
            operatorB = new JoystickButton(operatorStick, 2);
        }
        catch (Exception error){
            System.out.println("Error Init With Buttons");
            error.printStackTrace();
        }
    }
}
