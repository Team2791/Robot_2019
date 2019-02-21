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
    public static final double kElevatorMinPower = 0.3;
    public static final double kElevatorStallSpeed = -0.2; //Dont worry about this guy

    //SHIT THAT HAS BEEN ADDED FOR MAGIC MOTION STUFF

    public static double kLIFT_HOLD_VOLTAGE = 0.25; //Noah has determined this should be about 0.25

    public static double kELEVATOR_F_VALUE = 0.38; // F-gain = (100% X 1023) / MAX_VEL
    public static double kELEVATOR_P_VALUE = 7; //The P value of the lift's PID loop //16 SHOULD be a pretty good value
    public static double kELEVATOR_DOWN_P_VALUE = 1.5;
    public static double kELEVATOR_I_VALUE = 0.0; //The I value of the lift's PID loop
    public static double kELEVATOR_D_VALUE = 4.55; //The D value of the lift's PID loop 8 SHOULD be a pretty good value
    public static int kELEVATOR_VELOCITY = 125; //106; //TUNE MEEE
    public static int kELEVATOR_ACCELERATION = 300; //212; //TUNE MEEEEE
    public static final double kElevatorMaxHeight = 1023; //This is the maximum height of the elevator based on potentiometer values idrk
    public static final double kElevatorMinHeight = 41; //This is the minimum height of the elevator based on potentiometer values idrk

    public static final int kElevatorBottomSafetyDistance = 100; //This is the difference between absolute bottom and where it will stop
    public static final int kElevatorTopSafetyDistance = 100;
    public static final double MANUAL_POWER = 1.0; //Manual power is 0.75
    public static final double kPotOffset = 0.0;

    //Joystick constant
    public static final double DEADZONE = 0.05;
    //Hatch Manipulator Values
	public static final double kGetPanelAutomatedReleaseRetractionDelay = 0.25;
	public static final double kGetPanelAutomatedReleaseAlignerRetractionDelay = 1.0;
	public static final double kScorePanelDelayGrabberCloseAndHatchRetraction = 0.25; //This is the delay between the grabber closing and the hatch retraction in the automated hatch scoring
	public static final double kScorePanelDelayHatchRetractionAndAlignerRaise = 1.0;


	//Cargo Intake Values
	public static final double kCargoIntakeMotorSpeed = 1.0; //0.75 //This is the intake speed for the cargo
	public static final double kRaiseCargoArmsDelayAfterButtonPressed = 0.4; //This is the delay between when the ball presses the switch on the intake and when the intake is raised
	public static final double kCargoIntakeMotorStallSpeed = 0.1; //This is the speed at which the intake will apply a constant stall on the ball when the switch is pressed
	public static final double kCargoSlowShootMotorSpeed = -0.25;
	public static final double kCargoFastShootMotorSpeed = -0.7;
	public static final double kCargoIntakeHumanSpeed = 1.0; //0.5
}