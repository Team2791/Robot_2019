package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.HatchManipulator;
import frc.robot.subsystems.Lifters;
import frc.robot.util.Limelight;

public class Robot extends TimedRobot {

    public static OI oi;
    public static Drivetrain drivetrain;
    public static HatchManipulator hatchManipulator;
    public static Limelight limelight;
    public static Elevator elevator;
    public static Lifters lifters;

    @Override
    public void robotInit() {
        drivetrain = new Drivetrain();
        oi = new OI();
        lifters = new Lifters();
    }

    @Override
    public void robotPeriodic() {
        lifters.debug();
    }

    @Override
    public void disabledInit() {
        drivetrain.setMotors(0, 0);
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        drivetrain.setMotors(0, 0);
    }

    @Override
    public void autonomousInit() {
        
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    @Override
    public void testPeriodic() {
    }
}
