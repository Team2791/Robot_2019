package frc.robot;

public class Constants {
    public static final double kFastDrive = 1.0;
    public static final double kSlowDrive = 0.5;

    // Lift Constants All these constants need to be modified later on
	public static final int kElevatorPotOffset = 0; 
	public static final double kElevatorPotFullRange = 0; 
	public static final int kElevatorTopSafetyDistance = 0;
	public static final int kElevatorMaxHeight = 0;
	public static final int kElevatorBottomSafetyDistance = 0;
	public static final double kElelvatorMinHeight = 0;

	//Hatch Manipulator Values
	public static final double kGetPanelAutomatedReleaseRetractionDelay = 0.25;
	public static final double kGetPanelAutomatedReleaseAlignerRetractionDelay = 1.0;
	public static final double kScorePanelDelayGrabberCloseAndHatchRetraction = 0.25; //This is the delay between the grabber closing and the hatch retraction in the automated hatch scoring
	public static final double kScorePanelDelayHatchRetractionAndAlignerRaise = 1.0;


	//Cargo Intake Values
	public static final double kCargoIntakeMotorSpeed = 0.75; //This is the intake speed for the cargo
	public static final double kRaiseCargoArmsDelayAfterButtonPressed = 0.4; //This is the delay between when the ball presses the switch on the intake and when the intake is raised
	public static final double kCargoIntakeMotorStallSpeed = 0.1; //This is the speed at which the intake will apply a constant stall on the ball when the switch is pressed
	public static final double kCargoSlowShootMotorSpeed = -0.25;
	public static final double kCargoFastShootMotorSpeed = -0.7;
	public static final double kCargoIntakeHumanSpeed = 0.5;
}