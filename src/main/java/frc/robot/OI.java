package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.controller.AnalogButton;
import frc.robot.controller.MultiButton;
import frc.robot.commands.DriveWithJoystick;

public class OI {
    private Joystick driverStick;
    private Button driveButton;
    private Button driverLB, driverRB;

    public OI() {
        driverStick = new Joystick(0);
        driverRB = new JoystickButton(driverStick, 6);
        driverLB = new JoystickButton(driverStick, 5);
        driveButton = new MultiButton(new Button[] {
            new AnalogButton(driverStick, 3, 2, 0, 0.2),
            driverRB,
            driverLB
        });

        driveButton.whileHeld(new DriveWithJoystick(driverStick, 0.1));
    }
}
