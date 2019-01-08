package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.DriveWithJoystick;

public class OI {
    public static Joystick driverStick = new Joystick(0);
    private Button driveButton;
    public OI(){
        driveButton = new JoystickButton(driverStick, 1);
        driveButton.whileHeld(new DriveWithJoystick(driverStick));
    }
}
