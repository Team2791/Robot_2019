package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import static java.lang.StrictMath.max;
import static java.lang.StrictMath.min;
import frc.robot.commands.Elevator.StopLift;
import frc.robot.util.Util;
public class Elevator extends Subsystem {

    private Solenoid breakSolenoid;
    private TalonSRX driveTalon;
    private Timer breakReleaseTimer;
    private boolean breakReleaseTimerStarted = false;
    public static double speedModifier;



    //private TalonSRX driveTalon;
    //private Solenoid breakSolenoid;
    //private Timer breakReleaseTimer;
    //private boolean breakReleaseTimerStarted = false;
    //private int elevatorZero;
    //private int elevatorMax;
    //private double kP;
    //private double kI;
    //private double kD;
    //private double kF;

    public Elevator() {
        super("ShakerLift");


        driveTalon = new TalonSRX(RobotMap.kElevatorTalon);
        breakSolenoid = new Solenoid(RobotMap.kPCM, RobotMap.kBreakSolenoid); // RobotMap ids need to be changed
        breakReleaseTimer = new Timer();
        driveTalon.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
        driveTalon.setSensorPhase(true); //This was true //TODO FIX ME
        //driveTalon.setInverted(true);
        driveTalon.setNeutralMode(NeutralMode.Brake);
        driveTalon.overrideLimitSwitchesEnable(false);


        // Setting up Magic Motion Profiling
        // I don't know how it workis if you have leaders and followers, this code is just for leaderTalon
        // https://www.ctr-electronics.com/downloads/pdf/Talon%20SRX%20Software%20Reference%20Manual.pdf  ---> Page 99
        // TODO replace this ^^ with the current manual!
        driveTalon.configNominalOutputForward(Constants.kLIFT_HOLD_VOLTAGE, 0);
        driveTalon.configNominalOutputReverse(0.0, 0);

    	SmartDashboard.putNumber("LIFT - MM - kP", Constants.kLIFT_P_VALUE);
    	SmartDashboard.putNumber("LIFT - MM - kI", Constants.kLIFT_I_VALUE);
    	SmartDashboard.putNumber("LIFT - MM - kD", Constants.kLIFT_D_VALUE);
    	SmartDashboard.putNumber("Lift - MM - Target", 0);
        
        //leaderTalon.
//        leaderTalon.configPeakCurrentLimit(12, 1); // Need to tune these values, I am just guessing
        driveTalon.configMotionCruiseVelocity(Constants.MOTION_VELOCITY, 0);
        driveTalon.configMotionAcceleration(Constants.MOTION_ACCELERATION, 0);
        driveTalon.config_kF(Constants.MM_PID_SLOT_ID, Constants.LIFT_F_VALUE, 0);
        updateMagicMotionPIDGains();
        speedModifier = 1;
    }

    public void updateMagicMotionPIDGains() {
    	Constants.kLIFT_P_VALUE = SmartDashboard.getNumber("LIFT - MM - kP", Constants.kLIFT_P_VALUE);
    	Constants.kLIFT_I_VALUE = SmartDashboard.getNumber("LIFT - MM - kI", Constants.kLIFT_I_VALUE);
    	Constants.kLIFT_D_VALUE = SmartDashboard.getNumber("LIFT - MM - kD", Constants.kLIFT_D_VALUE);
        driveTalon.config_kP(Constants.MM_PID_SLOT_ID, Constants.kLIFT_P_VALUE, 0);
        driveTalon.config_kI(Constants.MM_PID_SLOT_ID, Constants.kLIFT_I_VALUE, 0);
        driveTalon.config_kD(Constants.MM_PID_SLOT_ID, Constants.kLIFT_D_VALUE, 0);
    }



    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new StopLift());
    }


    public double getHeight() {
        return convertSRXUnitsToLiftHeight(getSRXVoltageFeedback()) + Constants.kPotOffset; //+1023 //TODO FIX ME
        //return driveTalon.getSensorCollection().getAnalogIn();
    }

    public int getVelocity() {
        return driveTalon.getSensorCollection().getAnalogInVel();
    }

    public int getSRXVoltageFeedback() {
                return driveTalon.getSelectedSensorPosition(0);
                // return driveTalon.getSensorCollection().getVoltage();
                // return 0
            }

    public int getSRXVoltageVelocityFeedback() {
                return driveTalon.getSelectedSensorVelocity(0);
            }

    public int convertLiftHeightToSRXUnits(double liftHeightIn) {
                return (int) ((liftHeightIn - Constants.kPotOffset) / Constants.kElevatorPotFullRange * 1023);
            }

    public double convertSRXUnitsToLiftHeight(int SRXUnits) {
                double potTravel = SRXUnits / 1023.0;
                return potTravel * Constants.kElevatorPotFullRange;
            }

    public void setManualPower(double power){
        boolean pickedOne;
        pickedOne = false;
        if(atBottom() && pickedOne == false){
            if(power<=0){
                setPowerUnsafe(power);
                pickedOne = true;
            }
            else{
                //Don't allow the operator to move further down
                setPowerUnsafe(0);
                pickedOne=true;
            }
        }
        else if(closeToBottom() && pickedOne == false){
            if(power<=0){
                setPowerUnsafe(power);
                pickedOne = true;
            }
            else{
                setPowerUnsafe(power * .25); //TODO Make this a variable
                pickedOne = true;
            }
        }
        else if(atTop() && pickedOne == false){
            if(power>=0){
                setPowerUnsafe(power);
                pickedOne = true;
            }
            else{
                setPowerUnsafe(0);
                pickedOne = true;
            }
        }
        else if(closeToTop() && pickedOne == false){
            if(power>=0){
                setPowerUnsafe(power);
                pickedOne = true;
            }
            else{
                setPowerUnsafe(power*.25);//TODO MAKE THIS A VARIABLE
                pickedOne = true;
            }
        }
        else{
            setPowerUnsafe(power);
            pickedOne = true;
        }
    }
    public void setPower(double power) {
                SmartDashboard.putNumber("Lift - set power input", power);
                // make sure the break is released before we let it move
                if(breakReleaseTimer.get() < 0.12) {
                    setPowerUnsafe(0);
                    return;
                }
                
                if (atBottom()) {
                    power = min(0, power);
                } else if (closeToBottom()) {
                    power = min(0.02, power); // was 0.2 without manipulator, if it needs to be something it can be -0.05 or something very small
                } else if (atTop()) {
                    power = max(-0.01, power); // let the lift hold itself at the top.
                } else if (closeToTop()) {
                    power = max(-0.25, power);
                }
        
                // increasing speed from -0.45 to -.6 before Utica.
                power = min(.60, power);
                
                // now we use the internal method that has direct control to the motor
                // after we have made sure that power is a safe number.
                setPowerUnsafe(power);
            }

    public boolean atBottom() {
                return driveTalon.getSensorCollection().isRevLimitSwitchClosed() || getHeight() > Constants.kElevatorMinHeight - 1;
            }
        
            
    public boolean closeToBottom() {
                return getHeight() > Constants.kElevatorMinHeight - Constants.kElevatorBottomSafetyDistance;
            }
        
    public boolean atTop() {
        return driveTalon.getSensorCollection().isFwdLimitSwitchClosed() || getHeight() < Constants.kElevatorMaxHeight + 1;
            }
        
    public boolean closeToTop() {
                return getHeight() < Constants.kElevatorMaxHeight + Constants.kElevatorTopSafetyDistance;
            }

    public void setBreak(boolean breakOn){
        breakSolenoid.set(!breakOn); // Solenoid is default on. True means the break will be off
        
        if(breakOn) {
        	// reset and stop the timer when we put the break on.
        	breakReleaseTimer.reset();
        	breakReleaseTimer.stop();
        	breakReleaseTimerStarted = false;
        } else {
        	// when we release the break start the timer.
        	// we need to check if we've already started the timer because the
        	// start timer method also resets it.
        	if(!breakReleaseTimerStarted) {
        		breakReleaseTimer.start();
        		breakReleaseTimerStarted = true;
        	}
        }
    }

    public void setPowerUnsafe(double power) {
		driveTalon.set(ControlMode.PercentOutput, power);
    }

    public void setTargetMagicMotion(double targetHeight) {
    	SmartDashboard.putNumber("Lift - MM - Target", convertLiftHeightToSRXUnits(targetHeight));
        driveTalon.set(ControlMode.MotionMagic, convertLiftHeightToSRXUnits(targetHeight));
    }
    
    public int getMagicMotionInstantError() {
    	return driveTalon.getClosedLoopError(Constants.MM_PID_SLOT_ID);
    }
    
    public double getMagicMotionInstantErrorIn() {
    	return convertSRXUnitsToLiftHeight(getMagicMotionInstantError());
    }

    public void debug() {
        SmartDashboard.putBoolean("Elevator - Top Limit Switch value", atTop());
        SmartDashboard.putBoolean("Elevator - Bottom Limit Switch value", atBottom());
        SmartDashboard.putBoolean("Elevator - Break value", !breakSolenoid.get());
        SmartDashboard.putNumber("Elevator - break timer", breakReleaseTimer.get());
        SmartDashboard.putNumber("Elevator - Height", getHeight());
        SmartDashboard.putNumber("Lift - Velocity", getVelocity());
        SmartDashboard.putBoolean("Elevator - Close to top", closeToTop());
        SmartDashboard.putBoolean("Elevator - Close to bottom", closeToBottom());
        SmartDashboard.putNumber("Lift - Velocity RAW", getSRXVoltageVelocityFeedback());
        SmartDashboard.putNumber("Lift - SRX Return value", getSRXVoltageFeedback());
        SmartDashboard.putNumber("OperatorLSAxis", OI.operatorStick.getRawAxis(1));
        SmartDashboard.putNumber("ManualSpeed", Util.deadzone(Constants.DEADZONE, OI.operatorStick.getRawAxis(1),1.0) * Constants.MANUAL_POWER * Robot.elevator.speedModifier);
    }

}