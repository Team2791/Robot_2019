package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;

public class CargoManipulator extends Subsystem {
    private Solenoid raiserSolenoid;
    private VictorSPX intakeVictor;

    public CargoManipulator() {
        raiserSolenoid = new Solenoid(RobotMap.kPCM, RobotMap.kRaiseCargoSolenoid);
        intakeVictor = new VictorSPX(RobotMap.kIntakeVictor);
    }
    @Override public void initDefaultCommand() {
    }

    public void setRaiser(boolean extended) {
         raiserSolenoid.set(extended);
    }
    public boolean getRaiser() {
        return raiserSolenoid.get();
    }
    public void setIntakeMotor(double output) {
        intakeVictor.set(ControlMode.PercentOutput, output);
    }
    public void debug() {
        SmartDashboard.putBoolean("CargoManipulator Raiser", getRaiser());
    }
}
