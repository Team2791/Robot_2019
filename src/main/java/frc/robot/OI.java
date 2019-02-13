package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.controller.AnalogButton;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.OpenGrabber;

public class OI {
    private Joystick driverStick;
    private Button driveButton;
    private Button openManipulator;
    private Button closeManipulator;

    public OI() {
        driverStick = new Joystick(0);
        driveButton = new AnalogButton(driverStick, 3, 2, 0, 0.2);
        

        driveButton.whileHeld(new DriveWithJoystick(driverStick, 0.1));
        

        
    }
}
