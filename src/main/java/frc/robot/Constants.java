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

    public static double kLIFT_HOLD_VOLTAGE = 0.01; //Noah is guessing that this is the stall voltage on the motors for when the moments between the target value being hit and the break being engaged
    public static double kLIFT_P_VALUE = 10; //The P value of the lift's PID loop
    public static double kLIFT_I_VALUE = 0.008; //The I value of the lift's PID loop
    public static double kLIFT_D_VALUE = 50; //The D value of the lift's PID loop
    public static final double LIFT_MAX_SPEED_RAW_UNITS = 85.0;// max velocity is 85 U/.1s up and 95 U/.1s down? //was 85
    public static final int MOTION_VELOCITY = (int) (LIFT_MAX_SPEED_RAW_UNITS * 1.0); 
    public static final int MOTION_ACCELERATION = (int) (MOTION_VELOCITY / 0.65); //This is the amount of time it takes to reach cruising speed, this was 0.75 seconds last year, changed to 0.5 seconds this year because of springs
    public static final int MM_PID_SLOT_ID = 0; //TODO Figure out what the fuck this means
    public static final double LIFT_F_VALUE = 0.38; // F-gain = (100% X 1023) / MAX_VEL
    public static final int kElevatorMinHeight = -20; //This is the minimum height of the elevator based on potentiometer values idrk
    public static final double kElevatorMaxHeight = -985; //This is the maximum height of the elevator based on potentiometer values idrk
    public static final int kElevatorBottomSafetyDistance = 100; //This is the difference between absolute bottom and where it will stop
    public static final int kElevatorTopSafetyDistance = 100;
    public static final double kCLOSE_POWER = 0.05; //40% speed if travel is small
    public static final double kFAR_AWAY_DISTANCE = 12; //Anything further than 12 inches (Or 12 pot turns idrk) will use "far away speed"
    public static final double kCLOSE_DISTANCE = 1; //Not sure here
    public static final double kFAR_AWAY_POWER = 0.25; //100% power when traveling far
    public static final double MANUAL_POWER = 0.25; //Manual power is 0.75
    public static final double kPotOffset = 0.0;

    //Joystick constant
    public static final double DEADZONE = 0.05;
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