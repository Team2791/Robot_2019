package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
// import frc.robot.commands.FollowLine;
import frc.robot.commands.Elevator.SetLiftHeightMagicMotion;

public class FollowLineAndSetLift extends CommandGroup {
    public FollowLineAndSetLift(double height, double speedLOW, double speedHIGH, boolean lineFollow, boolean scaled) {
        addParallel(new SetLiftHeightMagicMotion(height));
        addSequential(new LimelightFollow(speedLOW, speedHIGH, lineFollow, scaled));
    }
}
