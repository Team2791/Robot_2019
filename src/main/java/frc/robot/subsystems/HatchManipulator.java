package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import frc.robot.RobotMap;

public class HatchManipulator extends Subsystem {
    private DoubleSolenoid grabber;
    private boolean isOpen;

    public HatchManipulator() {
        grabber = new DoubleSolenoid(RobotMap.kGrabberOpen, RobotMap.kGrabberClose);
        closeGrabber();
    }

    @Override public void initDefaultCommand() {
    }

    public void closeGrabber() {
        grabber.set(DoubleSolenoid.Value.kReverse);
        isOpen = false;
    }

    public void openGrabber() {
        grabber.set(DoubleSolenoid.Value.kForward);
        isOpen = true;
    }

    public void toggleGrabber() {
        if(isOpen) {
            closeGrabber();
        } else {
            openGrabber();
        }
    }

    public boolean getIsOpen() {
        return isOpen;
    }
}