package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.auto.AutoSetElevatorPot;
import frc.robot.commands.auto.AutoSetLifterPots;
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
    public static PowerDistributionPanel pdp;

    private Command autoCommand;


    @Override
    public void robotInit() {
        System.out.println("Robot config: " + (RobotMap.isFlightBot ? "Flight bot" : "Practice bot"));
        drivetrain = new Drivetrain();
        lifters = new Lifters();
        pdp = new PowerDistributionPanel(RobotMap.kPDP);
        elevator = new Elevator();
        oi = new OI();

        autoCommand = new AutoSetElevatorPot();
    }

    @Override
    public void robotPeriodic() {
        lifters.debug();
        elevator.debug();
    }

    @Override
    public void disabledInit() {
       drivetrain.setMotors(0, 0);
       lifters.resetSystem();
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
        drivetrain.setMotors(0, 0);
    }

    @Override
    public void autonomousInit() {
        autoCommand.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        autoCommand.cancel();
    }

    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    @Override
    public void testPeriodic() {
    }
}
