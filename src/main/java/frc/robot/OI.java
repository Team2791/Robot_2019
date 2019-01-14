package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import frc.robot.controller.AnalogButton;
import frc.robot.commands.DriveWithJoystick;

public class OI {
    private Joystick driverStick;
    private Button driveButton;

    public OI() {
        driverStick = new Joystick(0);
        driveButton = new AnalogButton(driverStick, 3, 2, 0, 0.2);

        driveButton.whileHeld(new DriveWithJoystick(driverStick, 0.1));
    }
}
