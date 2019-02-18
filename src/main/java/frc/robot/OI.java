package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.controller.AnalogButton;
import frc.robot.controller.DPadButton;
import frc.robot.controller.MultiButton;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.ExtendBothLifters;
import frc.robot.commands.RetractBothLifters;
import frc.robot.commands.MoveElevator;
import frc.robot.commands.StallElevator;
import frc.robot.commands.SetElevatorPosition;
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
    private Joystick driverStick;
    private Joystick operatorStick;
    private Button driveButton;
    private Button driverLB, driverRB;
    private Button driverStart, driverBack;
    private Button driverA, driverB;
    private Button operatorRB, operatorLT, operatorLB, operatorRT;
    private Button operatorLS, operatorDown;

    public OI() {
        driverStick = new Joystick(0);
        operatorStick = new Joystick(1);
        initButtons();

        driveButton.whileHeld(new DriveWithJoystick(driverStick, 0.1));
        driverStart.whileHeld(new ExtendBothLifters(1));
        driverBack.whileHeld(new RetractBothLifters(-1));
        operatorLS.whileHeld(new MoveElevator(operatorStick));
        operatorLS.whenReleased(new StallElevator());
        
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

        operatorDown.whenPressed(new SetElevatorPosition(492));
    }

    private void initButtons(){
        try{
            driverA = new JoystickButton(driverStick, 1);
            driverB = new JoystickButton(driverStick, 2);
            driverBack = new JoystickButton(driverStick, 7);
            driverStart = new JoystickButton(driverStick, 8);
            driverRB = new JoystickButton(driverStick, 6);
            driverLB = new JoystickButton(driverStick, 5);
            driveButton = new MultiButton(new Button[] {
                new AnalogButton(driverStick, 3, 2, 0, 0.2),
                driverRB,
                driverLB
            });
            
            operatorRB = new JoystickButton(operatorStick, 6);
            operatorLB = new JoystickButton(operatorStick, 5);
            operatorLT = new AnalogButton(operatorStick, 2);
            operatorRT = new AnalogButton(operatorStick, 3);
            operatorLS = new AnalogButton(operatorStick, 1);
            operatorDown = new DPadButton(operatorStick, 2);
        }
        catch (Exception error){
            System.out.println("Error Init With Buttons");
            error.printStackTrace();
        }
    }
}
