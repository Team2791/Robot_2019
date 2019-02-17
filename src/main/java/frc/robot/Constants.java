package frc.robot;

public class Constants {
    public static final double kCreep = 0.2;
    
    // Drivetrain constants
    public static final double kFastDrive = 1.0;
    public static final double kSlowDrive = 0.5;

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
    public static final int kElelvatorMinHeight = 20;
    public static final double kElevatorMinPower = 0.5;

    //Joystick constant
    public static final double DEADZONE = 0;
}