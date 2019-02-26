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
import frc.robot.commands.CargoManipulator.CargoHumanPlayerIntake;
import frc.robot.commands.auto.PlatformAuto3;
import frc.robot.util.Util;

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
    private Button driverX;
    protected Button operatorLeftJoystickUsed, operatorRightJoystickUsed;
    private Button operatorA, operatorB, operatorX, operatorY;

    public OI() {
        driverStick = new Joystick(0);
        operatorStick = new Joystick(1);
        initButtons();
        initUsed();

        driveButton.whileHeld(new DriveWithJoystick(driverStick, 0.1)); //TODO this should be the default command of the DT
        driverStart.whileHeld(new ExtendBothLifters(.8)); //TODO NOT FOR COMPETITION
        driverBack.whileHeld(new RetractBothLifters(-1)); //TODO NOT FOR COMPETITION

        operatorLeftJoystickUsed.whenPressed(new RunLiftWithJoystick(operatorLeftJoystickUsed)); //Elevator manual drive
        operatorA.whenPressed(new MagicMotionHatchBall(operatorStick, Constants.kElevatorMinHeight + 3, Constants.kElevatorMinHeight + 3)); //This will make the lift go to the bottom + 3 pot turns
        operatorB.whenPressed(new MagicMotionHatchBall(operatorStick, Constants.kELEVATOR_PANEL_ONE, Constants.kELEVATOR_BALL_ONE)); //Sets elevator to panel height 1 / ball height 1
        operatorX.whenPressed(new MagicMotionHatchBall(operatorStick, Constants.kELEVATOR_PANEL_TWO, Constants.kELEVATOR_BALL_TWO)); //Sets elevator to panel height 2 / ball height 2
        operatorY.whenPressed(new MagicMotionHatchBall(operatorStick, Constants.kELEVATOR_PANEL_THREE, Constants.kELEVATOR_BALL_THREE)); //Sets elevator to panel height 2 / ball height 2

        operatorStart.whenPressed(new FrameRetraction()); //Moves everything within frame perimeter for defense

        driverA.whenPressed(new GetPanelAutomatedHeld()); //Gets panel
        driverA.whenReleased(new GetPanelAutomatedRelease()); //Gets panel
        driverB.whenPressed(new ScorePanelAutomatedHeld()); //Scores panel
        driverB.whenReleased(new ScorePanelAutomatedRelease()); //Scores panel

        driverX.whenPressed(new PlatformAuto3()); //Runs autonomous lifting sequence

        operatorRT.whenPressed(new HoldCargoIntake()); //Intakes cargo off floor
        operatorRT.whenReleased(new ReleaseCargoIntake());

        operatorRB.whenPressed(new CargoHumanPlayerIntake()); //Intakes cargo from human player
        operatorRB.whenReleased(new StopCargoMotor());

        operatorLB.whenPressed(new SlowShootCargo()); //Shoots cargo slow
        operatorLB.whenReleased(new StopCargoMotor());

        operatorLT.whenPressed(new FastShootCargo()); //Shoots cargo fast
        operatorLT.whenReleased(new StopCargoMotor());
    }

    private void initButtons(){
        try{
//DRIVER BUTTONS//
            driverA = new JoystickButton(driverStick, 1);
            driverB = new JoystickButton(driverStick, 2);
            driverX = new JoystickButton(driverStick,3);
            driverBack = new JoystickButton(driverStick, 7);
            driverStart = new JoystickButton(driverStick, 8);
            driverRB = new JoystickButton(driverStick, 6);
            driverLB = new JoystickButton(driverStick, 5);

            driveButton = new MultiButton(new Button[] {
                new AnalogButton(driverStick, 3, 2, 0, 0.2),
                driverRB,
                driverLB
            });

//OPERATOR BUTTONS//
            operatorA = new JoystickButton(operatorStick, 1);
            operatorB = new JoystickButton(operatorStick, 2);
            operatorX = new JoystickButton(operatorStick, 3);
            operatorY = new JoystickButton(operatorStick, 4);
            operatorBack = new JoystickButton(operatorStick,7);
            operatorStart = new JoystickButton(driverStick, 8);
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