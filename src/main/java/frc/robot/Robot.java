package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.auto.FollowLimelight;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HatchManipulator;
import frc.robot.util.Limelight.Limelight;
import frc.robot.commands.StopDrive;


public class Robot extends TimedRobot {

    public static OI m_oi;
    public static Drivetrain drivetrain;
    public static HatchManipulator hatchManipulator;
    public static Limelight limelight;
    
    private Command m_autoCommand;

    @Override
    public void robotInit() {
        drivetrain = new Drivetrain();
        limelight = new Limelight();
        m_oi = new OI();
        m_autoCommand = new FollowLimelight();
        System.out.println("I am doing limelight debug rn. HI. :P ");
        limelight.debug();
    }


    @Override
    public void disabledInit() {
        System.out.println("disabledInit works lol");
        limelight.debug();
        m_autoCommand.cancel();
        drivetrain.setMotors(0, 0);
    }

    @Override
    public void disabledPeriodic() {
        System.out.println("disabledPeriodic works lol");
        System.out.println("Limelight horizontal offset : " + limelight.getHorizontalOffset());
        limelight.debug();
        Scheduler.getInstance().run();
        drivetrain.setMotors(0, 0);
    }

    @Override
    public void autonomousInit() {
        System.out.println("autonomousInit works lol");
        limelight.debug();
        m_autoCommand.start();
    }

    @Override
    public void autonomousPeriodic() {
        System.out.println("Autonomous Periodic works lol ");
        limelight.debug();
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        System.out.println("teleopINit works lol");
        limelight.debug();
        m_autoCommand.cancel();
    }

    @Override
    public void teleopPeriodic() {
        System.out.println("teleopPeriodic works lol");
        limelight.debug();
        Scheduler.getInstance().run();
        
    }
    
    @Override
    public void testPeriodic() {
    }
}
