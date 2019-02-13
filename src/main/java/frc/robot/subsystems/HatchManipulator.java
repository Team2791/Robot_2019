package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;

public class HatchManipulator extends Subsystem {
    private Solenoid extender, grabber, aligner;
    private boolean isOpen;

    public HatchManipulator() {
        extender = new Solenoid(RobotMap.kPCM, RobotMap.kExtendHatchSolenoid);
        grabber = new Solenoid(RobotMap.kPCM, RobotMap.kGrabberHatchSolenoid);
        aligner = new Solenoid(RobotMap.kPCM, RobotMap.kAlignerHatchSolenoid);
        closeGrabber();
    }

    @Override public void initDefaultCommand() {
    }

    public void setExtender(boolean extended) {
        extender.set(extended);
    }

    public boolean getExtender() {
        return extender.get();
    }

    public void setGrabber(boolean open) {
        grabber.set(open);
    }
    public boolean getGrabber() {
        return grabber.get();
    }

    public void setAligner(boolean extended) {
         aligner.set(extended);
    }
    public boolean getAligner() {
        return aligner.get();
    }

    public void debug() {
        SmartDashboard.putBoolean("HatchManipulator Extender Solenoid", getExtender());
        SmartDashboard.putBoolean("HatchManipulator Grabber Solenoid", getGrabber());
        SmartDashboard.putBoolean("HatchManipulator Aligner Solenoid", getAligner());
    }
}
