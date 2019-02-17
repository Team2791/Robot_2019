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
//import frc.robot.commands.CargoManipulator.SetIntakeMotor;
import frc.robot.commands.CargoManipulator.SlowShootCargo;
import frc.robot.commands.CargoManipulator.StopCargoMotor;

public class OI {
    private Joystick driverStick = new Joystick(0);
    private Joystick operatorStick = new Joystick(1);
    private Button driveButton;
    private Button operatorA, operatorB, operatorRB, operatorLT, operatorLB;

    public OI() {
        initButtons();
        driveButton = new AnalogButton(driverStick, 3, 2, 0, 0.2);
        driveButton.whileHeld(new DriveWithJoystick(driverStick, 0.1));
        
        operatorA.whenPressed(new GetPanelAutomatedHeld());
        operatorA.whenReleased(new GetPanelAutomatedRelease());
        operatorB.whenPressed(new ScorePanelAutomatedHeld());
        operatorB.whenReleased(new ScorePanelAutomatedRelease());
        operatorRB.whenPressed(new HoldCargoIntake());
        operatorRB.whenReleased(new ReleaseCargoIntake());
        operatorLB.whenPressed(new SlowShootCargo()); //Spit Ball Slow
        operatorLB.whenReleased(new StopCargoMotor());
        operatorLT.whenPressed(new FastShootCargo()); //Spit Ball Fast
        operatorLT.whenReleased(new StopCargoMotor());
    }
    private void initButtons(){
        try{
            operatorA = new JoystickButton(operatorStick, 1);
            operatorB = new JoystickButton(operatorStick, 2);
            operatorRB = new JoystickButton(operatorStick, 6);
            operatorLB = new JoystickButton(operatorStick, 5);
            operatorLT = new AnalogButton(operatorStick, 2);
        }
        catch (Exception error){
            System.out.println("Error Init With Buttons");
            error.printStackTrace();
        }
    }
}
