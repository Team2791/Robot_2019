package frc.robot;

public class RobotMap {
    //The numbers in this map are temporary values until we get an actual robot to use.
    //Global
    public static final int kPCM = 1;
    //Drivetrain
    public static final int kLeftTalon = 20;
    public static final int kRightTalon = 21;
    public static final int[] kLeftVictors = {30, 32};
    public static final int[] kRightVictors = {31, 33};

    public static final int kFrontLiftTalon = 22;
    public static final int kBackLiftTalon = 23;
    public static final int kRollerVictor = 34;

    public static final int kElevatorTalon = 24;
    public static final int kIntakeVictor = 35;

    //Lift 
    public static final int TOP_LIMIT_SWITCH = 8;
    public static final int BOTTOM_LIMIT_SWITCH = 9;
    public static final int IR_SENSOR_LEFT = 3;
    public static final int IR_SENSOR_RIGHT = 2;
    public static final int LIFT_POT_PORT = 1;
    public static final int LIFT_TALON = 24;
    public static final int LIFT_VICTOR = 0;    // no follow victor change later
    public static final int BREAK_SOLENOID = 0; // don't know ID for break solenoid will change to id after robot is made

    //Piston ID
    public static final int PCM_CAN_ID = 10;
}
