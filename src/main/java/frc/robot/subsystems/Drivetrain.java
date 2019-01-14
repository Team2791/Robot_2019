package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.BaseMotorController;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.StopDrive;

public class Drivetrain extends Subsystem {
    private TalonSRX leftLeader;
    private TalonSRX rightLeader;
    private VictorSPX leftFollower1;
    // private VictorSPX leftFollower2;
    private VictorSPX rightFollower1;
    // private VictorSPX rightFollower2;

    private BaseMotorController[] leftDrive;
    private BaseMotorController[] rightDrive;

    private double speedMultiplier;

    private StopDrive defaultCommand;

    public Drivetrain() {
        leftLeader = new TalonSRX(RobotMap.kLeftTalon);
        rightLeader = new TalonSRX(RobotMap.kRightTalon);
        leftFollower1 = new VictorSPX(RobotMap.kLeftVictors[0]);
        // leftFollower2 = new VictorSPX(RobotMap.kLeftVictors[1]);
        rightFollower1 = new VictorSPX(RobotMap.kRightVictors[0]);
        // rightFollower2 = new VictorSPX(RobotMap.kRightVictors[1]);

        // leftDrive = new BaseMotorController[]{leftLeader, leftFollower1, leftFollower2};
        // rightDrive = new BaseMotorController[]{rightLeader, rightFollower1, rightFollower2};

        leftDrive = new BaseMotorController[]{leftLeader, leftFollower1};
        rightDrive = new BaseMotorController[]{rightLeader, rightFollower1};

        for(int i = 0; i < rightDrive.length; ++i) {
            rightDrive[i].setInverted(true);
        }

        leftLeader.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);
        rightLeader.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 0);

        leftFollower1.follow(leftLeader);
        // leftFollower2.follow(leftLeader);
        rightFollower1.follow(rightLeader);
        // rightFollower2.follow(rightLeader);

        setDriveSpeed(false);
        setBrakeMode(false);
        setMotors(0, 0);
    }

    public void initDefaultCommand() {
        if(defaultCommand == null) {
            defaultCommand = new StopDrive();
        }
        defaultCommand.start();
    }

    public void setMotors(double left, double right) {
        System.out.println(left + ", " + right);
        leftLeader.set(ControlMode.PercentOutput, left * speedMultiplier);
        rightLeader.set(ControlMode.PercentOutput, right * speedMultiplier);
    }

    public void setBrakeMode(boolean isbrake) {
        NeutralMode mode = isbrake ? NeutralMode.Brake : NeutralMode.Coast;
        for(int i = 0; i < leftDrive.length; ++i) {
            leftDrive[i].setNeutralMode(mode);
            rightDrive[i].setNeutralMode(mode);
        }
    }

    public void setDriveSpeed(boolean isSlow) {
        speedMultiplier = isSlow ? Constants.kSlowDrive : Constants.kFastDrive;
    }
}