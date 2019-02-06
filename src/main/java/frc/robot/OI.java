package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.controller.AnalogButton;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.ExtendBothLifters;
import frc.robot.commands.RetractBothLifters;
import frc.robot.commands.DriveLifterWheel;

public class OI {
    private Joystick driverStick;
    private Button driveButton;
    private Button driveRB;
    private Button driveLB;
    private Button driveB;

    public OI() {
        driverStick = new Joystick(0);
        driveButton = new AnalogButton(driverStick, 3, 2, 0, 0.2);
        driveRB = new JoystickButton(driverStick, 6);
        driveLB = new JoystickButton(driverStick, 5);
        driveB = new JoystickButton(driverStick, 2);

        driveButton.whileHeld(new DriveWithJoystick(driverStick, 0.1));
        driveRB.whileHeld(new ExtendBothLifters());
        driveLB.whileHeld(new RetractBothLifters());
        driveB.whileHeld(new DriveLifterWheel());
    }
}
