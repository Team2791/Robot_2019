/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    //Global
    public static final int kPCM = 1;
    //Drivetrain
    public static final int kLeftTalon = 20;
    public static final int kRightTalon = 21;
    public static final int[] kLeftVictors = {30, 32};
    public static final int[] kRightVictors = {31, 33};

    //Lifters
    public static final int kFrontLiftTalon = 22;
    public static final int kBackLiftTalon = 23;
    public static final int kRollerVictor = 34;

    //Elevator
    public static final int kElevatorTalon = 24;
    public static final int kIntakeVictor = 35;

    public static final int kGrabberOpen = 0;
    public static final int kGrabberClose = 1;

    public static final int kElevatorTopLimit = 8;
    public static final int kElevatorBottomLimit = 9;
    public static final int kElevatorPot = 1;
    public static final int kBreakSolenoid = 2;

    //HatchManipulator
    public static final int kExtenderHatchSolenoid = -1;

    public static final int kGrabberHatchSolenoid = -1;

    public static final int kAlignerHatchSolenoid = -1;

    //CargoManipulator
    public static final int kCargoIntakeSolenoid = -1;
    public static final int kExtendHatchSolenoid = -1;
}
