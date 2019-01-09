package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.commands.auto.FollowLimelight;

public class Robot extends TimedRobot {

    public static OI m_oi;
    public static Drivetrain drivetrain;
    
    private Command m_autoCommand;

    @Override
    public void robotInit() {
        drivetrain = new Drivetrain();
        m_oi = new OI();
        m_autoCommand = new FollowLimelight();
    }

    @Override
    public void robotPeriodic() {
    }

    @Override
    public void disabledInit() {
        m_autoCommand.cancel();
        drivetrain.setMotors(0, 0);
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        drivetrain.setMotors(0, 0);
    }

    @Override
    public void autonomousInit() {
        m_autoCommand.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        m_autoCommand.cancel();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    @Override
    public void testPeriodic() {
    }
}
