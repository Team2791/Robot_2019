package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.commands.CargoManipulator.StallIntakeCargoMotors;

public class CargoManipulator extends Subsystem {
    private Solenoid raiserSolenoid;
    private VictorSPX intakeVictor;
    private DigitalInput cargoSwitch;
    public boolean CargoControls = false;

    public CargoManipulator() {
        raiserSolenoid = new Solenoid(RobotMap.kPCM, RobotMap.kRaiseCargoSolenoid);
        intakeVictor = new VictorSPX(RobotMap.kIntakeVictor);
        cargoSwitch = new DigitalInput(RobotMap.kCargoLimitSwitch);
    }
    @Override public void initDefaultCommand() {
        setDefaultCommand(new StallIntakeCargoMotors());
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
    public boolean getCargoSwitchState(){
        return !cargoSwitch.get();
    }

    public boolean getCargoControls() {
        //This should be false 99.9% of the time
        return CargoControls;
    }

    public void setCargoControls(boolean sent){
        CargoControls = sent;
    }
    public void debug() {
        SmartDashboard.putBoolean("CargoManipulator Raiser", getRaiser());
        SmartDashboard.putBoolean("Cargo Limit Switch State", getCargoSwitchState());
    }
}
