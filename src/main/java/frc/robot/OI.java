package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.controller.AnalogButton;
import frc.robot.controller.MultiButton;
import frc.robot.commands.DriveWithJoystick;
import frc.robot.commands.FrameRetraction;
import frc.robot.commands.Lifter.ExtendBothLifters;
import frc.robot.commands.Lifter.RetractBothLifters;
import frc.robot.commands.Elevator.MagicMotionHatchBall;
import frc.robot.commands.Elevator.RunLiftWithJoystick;
import frc.robot.commands.HatchManipulator.GetPanelAutomatedHeld;
import frc.robot.commands.HatchManipulator.GetPanelAutomatedRelease;
import frc.robot.commands.HatchManipulator.ScorePanelAutomatedHeld;
import frc.robot.commands.HatchManipulator.ScorePanelAutomatedRelease;
import frc.robot.commands.CargoManipulator.FastShootCargo;
import frc.robot.commands.CargoManipulator.HoldCargoIntake;
import frc.robot.commands.CargoManipulator.ReleaseCargoIntake;
import frc.robot.commands.CargoManipulator.SlowShootCargo;
import frc.robot.commands.CargoManipulator.StopCargoMotor;
import frc.robot.commands.Elevator.MagicMotionHatchBall;
import frc.robot.commands.Elevator.SetLiftHeightMagicMotion;
import frc.robot.commands.CargoManipulator.CargoHumanPlayerIntake;
import frc.robot.util.Util;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class OI {
    public static Joystick driverStick;
    public static Joystick operatorStick;
    private Button driveButton;
    private Button driverLB, driverRB;
    private Button driverStart, driverBack;
    private Button operatorStart;
    private Button driverA, driverB;
    private Button operatorRB, operatorLT, operatorLB, operatorRT;
    public Button operatorLS, operatorBack;
    protected Button operatorLeftJoystickUsed, operatorRightJoystickUsed;
    private Button operatorA, operatorB, operatorX, operatorY;

    public OI() {
        driverStick = new Joystick(0);
        operatorStick = new Joystick(1);
        initButtons();
        initUsed();

        driveButton.whileHeld(new DriveWithJoystick(driverStick, 0.1));
        driverStart.whileHeld(new ExtendBothLifters(1));
        driverBack.whileHeld(new RetractBothLifters(-1));

        operatorLeftJoystickUsed.whenPressed(new RunLiftWithJoystick(operatorLeftJoystickUsed));
        operatorA.whenPressed(new MagicMotionHatchBall(operatorStick, Constants.kElevatorMinHeight + 3, Constants.kElevatorMinHeight + 3)); //This will make the lift go to 0
        operatorB.whenPressed(new MagicMotionHatchBall(operatorStick, Constants.kELEVATOR_PANEL_ONE, Constants.kELEVATOR_BALL_ONE));
        operatorX.whenPressed(new MagicMotionHatchBall(operatorStick, Constants.kELEVATOR_PANEL_TWO, Constants.kELEVATOR_BALL_TWO));
        operatorY.whenPressed(new MagicMotionHatchBall(operatorStick, Constants.kELEVATOR_PANEL_THREE, Constants.kELEVATOR_BALL_THREE));

        operatorStart.whenPressed(new FrameRetraction());

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
            driverB = new JoystickButton(driverStick, 2);
            operatorA = new JoystickButton(operatorStick, 1);
            operatorB = new JoystickButton(operatorStick, 2);
            operatorX = new JoystickButton(operatorStick, 3);
            operatorY = new JoystickButton(operatorStick, 4);

            driverBack = new JoystickButton(driverStick, 7);
            operatorBack = new JoystickButton(operatorStick,7);
            driverStart = new JoystickButton(driverStick, 8);
            operatorStart = new JoystickButton(driverStick, 8);
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
        }

        catch (Exception error){
            System.out.println("Error Init With Buttons");
            error.printStackTrace();
        }
    }
    private void initUsed(){
        operatorLeftJoystickUsed = new Button() {
			@Override
			public boolean get() {
				return Math.abs(Util.deadzone(Constants.DEADZONE, operatorStick.getRawAxis(1), 1.0)) > 0.08;
			}
		};

    }
}