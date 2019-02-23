package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.commands.auto.AutoSetLifterPots;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.HatchManipulator;
import frc.robot.subsystems.Lifters;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.subsystems.CargoManipulator;
import frc.robot.util.Limelight;
import edu.wpi.cscore.UsbCamera;
//import edu.wpi.first.wpilibj.CameraServer;

public class Robot extends TimedRobot {
    long loopCounter = 0;

    public static OI oi;
    public static Drivetrain drivetrain;
    public static HatchManipulator hatchManipulator;
    public static CargoManipulator cargoManipulator; 
    public static Limelight limelight;
    public static Elevator elevator;
    public static Lifters lifters;
    public static PowerDistributionPanel pdp;
    public static Compressor compressor;
    private Command autoCommand;

    public static UsbCamera driver_cam;


    @Override
    public void robotInit() {
        drivetrain = new Drivetrain();
        lifters = new Lifters();
        pdp = new PowerDistributionPanel(RobotMap.kPDP);
        elevator = new Elevator();
        compressor = new Compressor(RobotMap.kPCM);
        compressor.start();
        hatchManipulator = new HatchManipulator();
        cargoManipulator = new CargoManipulator();
        
        oi = new OI();
        //driver_cam = CameraServer.getInstance().startAutomaticCapture("Driver Cam", 0); //TODO create camera code that works with the C920
        autoCommand = new AutoSetLifterPots();
    }
    
    @Override
    public void robotPeriodic() {
        // only run the debug stuff once every 10 loops
        if(++loopCounter % 10 != 0) {
            return;
        }
        lifters.debug();
        elevator.debug();
        cargoManipulator.debug();
        hatchManipulator.debug();
        drivetrain.debug();
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
