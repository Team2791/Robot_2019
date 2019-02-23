package frc.robot;

public class Constants {

    // Drivetrain constants
    public static final double kFastDrive = 1.0;
    public static final double kSlowDrive = 0.5;
    public static final double kCreep = 0.2;
    public static final double kSlowish = 0.5;
    //TODO different speeds based on height

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

    //Elevator constants

    public static double kLIFT_HOLD_VOLTAGE = 0.25; //Noah has determined this should be about 0.25
    public static double kELEVATOR_F_VALUE = 0; // F-gain = (100% X 1023) / MAX_VEL
    public static double kELEVATOR_P_VALUE = 15; //The P value of the lift's PID loop
    public static double kELEVATOR_I_VALUE = 0.01; //The I value of the lift's PID loop
    public static double kELEVATOR_D_VALUE = 500.0; //The D value of the lift's PID loop
    public static int kELEVATOR_I_ZONE_VALUE = 50;
    public static double kELEVATOR_ERROR_LEVEL = 15;

    // MAKE SURE THAT THIS OFFSET IS USE EVERYWHERE IT NEEDS TO BE
    public static final double kPotOffset = 13.0;
    // this should be set so that all of the distances assume the bottom of the lift is 0
    // eg: if the robot reads 14 at the bottom this should be 14

    public static double kELEVATOR_PANEL_ONE = 101.0 - kPotOffset; //This is correct yay
    public static double kELEVATOR_PANEL_TWO = 552.0 - kPotOffset; //This is correct yay
    public static double kELEVATOR_PANEL_THREE = 982.0 - kPotOffset; //This is correct yay

    public static double kELEVATOR_BALL_ONE = 194 - kPotOffset; //THIS IS THE CARGO SHIP //This is correct yay
    public static double kELEVATOR_BALL_TWO = 442 - kPotOffset; //This is correct yay
    public static double kELEVATOR_BALL_THREE = 878 - kPotOffset; //This is correct yay

    public static final double kElevatorMaxHeight = 1004 - kPotOffset; //This is the maximum height of the elevator based on potentiometer values 
    public static final double kElevatorMinHeight = 11 - kPotOffset; //This is the minimum height of the elevator based on potentiometer values 
    public static final int kElevatorBottomSafetyDistance = 100;
    public static final int kElevatorTopSafetyDistance = 100;
    public static final double MANUAL_POWER = 1.0; //Manual power is 0.75
    
    
    //Joystick constant
    public static final double DEADZONE = 0.05;

    //Hatch Manipulator Values
	public static final double kGetPanelAutomatedReleaseRetractionDelay = 0.25;
	public static final double kGetPanelAutomatedReleaseAlignerRetractionDelay = 1.0;
	public static final double kScorePanelDelayGrabberCloseAndHatchRetraction = 0.25; //This is the delay between the grabber closing and the hatch retraction in the automated hatch scoring
	public static final double kScorePanelDelayHatchRetractionAndAlignerRaise = 1.0;

	//Cargo Intake Values
	public static final double kCargoIntakeMotorSpeed = 0.75; //0.75 //This is the intake speed for the cargo
	public static final double kRaiseCargoArmsDelayAfterButtonPressed = 0.4; //This is the delay between when the ball presses the switch on the intake and when the intake is raised
	public static final double kCargoIntakeMotorStallSpeed = 0.1; //This is the speed at which the intake will apply a constant stall on the ball when the switch is pressed
	public static final double kCargoSlowShootMotorSpeed = -0.25;
	public static final double kCargoFastShootMotorSpeed = -0.7;
	public static final double kCargoIntakeHumanSpeed = 0.75; //0.5
}