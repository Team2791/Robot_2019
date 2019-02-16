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
    private Joystick driverStick;
    private Button driveButton;
    private Button driverA, driverB, driverX, driverY, driverRB, driverLB;

    public OI() {
        driverStick = new Joystick(0);
        initButtons();
        driveButton = new AnalogButton(driverStick, 3, 2, 0, 0.2);
        driveButton.whileHeld(new DriveWithJoystick(driverStick, 0.1));
        driverA.whenPressed(new GetPanelAutomatedHeld());
        driverA.whenReleased(new GetPanelAutomatedRelease());
        driverB.whenPressed(new ScorePanelAutomatedHeld());
        driverB.whenReleased(new ScorePanelAutomatedRelease());
        driverX.whenPressed(new DropAligner());
        driverX.whenReleased(new RaiseAligner());
        driverY.whenPressed(new OpenGrabber());
        driverY.whenReleased(new CloseGrabber());
        driverRB.whenPressed(new ExtendHatch());
        driverRB.whenReleased(new RetractHatch());
        driverLB.whenPressed(new DropCargo());
        driverLB.whenReleased(new RaiseCargo());

    }
    private void initButtons(){
        try{
            driverA = new JoystickButton(driverStick, 1);
            driverB = new JoystickButton(driverStick, 2);
            driverX = new JoystickButton(driverStick, 3);
            driverY = new JoystickButton(driverStick, 4);
            driverLB = new JoystickButton(driverStick, 5);
            driverRB = new JoystickButton(driverStick, 6);
        }
        catch (Exception error){
            System.out.println("Error Init With Buttons");
            error.printStackTrace();
        }
    }
}
