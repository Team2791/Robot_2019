package frc.robot;

public class Constants {
    // Drivetrain constants
    public static final double kFastDrive = 1.0;
    public static final double kSlowDrive = 0.5;

    // Lifters constants
    public static final int kFrontLifterPotMin = 91;
    public static final int kFrontLifterPotMax = 442;
    public static final int kFrontLifterRange = kFrontLifterPotMax - kFrontLifterPotMin;
    public static final int kBackLifterPotMin = 20;
    public static final int kBackLifterPotMax = 376;
    public static final int kBackLifterRange = kBackLifterPotMax - kBackLifterPotMin;
    public static final double kLiftTime = 1.5;

    public static final int kFrontPlatformCutoff = 1100;
    public static final int kBackPlatformCutoff = 1100;
    public static final double kLifterDrivePower = 1;

    public static final double kLifterFrontF = 0;
    public static final double kLifterBackF = 0;
    public static final double kLifterFrontP = 0;
    public static final double kLifterBackP = 0;

    // Elevator Constants All these constants need to be modified later on
	public static final int kElevatorPotOffset = 0; 
	public static final double kElevatorPotFullRange = 0; 
	public static final int kElevatorTopSafetyDistance = 0;
	public static final int kElevatorMaxHeight = 0;
	public static final int kElevatorBottomSafetyDistance = 0;
    public static final double kElelvatorMinHeight = 0;
    

}