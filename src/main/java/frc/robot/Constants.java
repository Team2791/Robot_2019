package frc.robot;

public class Constants {
    // Drivetrain constants
    public static final double kFastDrive = 1.0;
    public static final double kSlowDrive = 0.5;
    public static final double kCreep = 0.2;

    // Lifters constants
    public static final int kFrontLifterPotMin = 40;
    public static final int kBackLifterPotMin = 50;
    public static final int kLifetPotRange = 354;

    public static final int kFrontPlatformCutoff = 1100;
    public static final int kBackPlatformCutoff = 1100;
    public static final double kLifterDrivePower = 1;
    public static final double kFullDangerCurrent = 134;
    public static final int kDangerTimeout = 100;

    public static final double kLifterF = 0.135;
    public static final double kLifterP = 16;

    // Elevator Constants All these constants need to be modified later on 
	public static final int kElevatorPotFullRange = 985; 
	public static final int kElevatorTopSafetyDistance = 200;
	public static final int kElevatorBottomSafetyDistance = 200;
    public static final int kElevatorMinHeight = 20;
    public static final double kElevatorF = 0.38;
    public static final double kElevatorP = 1;
    public static final double kElevatorI = 0;
    public static final double kElevatorD = 0;
    public static final double kElevatorMinPower = 0.3;
    public static final double kElevatorStallSpeed = -0.2;
    public static final double kPotOffset = -16.0;

    //Joystick constant
    public static final double DEADZONE = 0;
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