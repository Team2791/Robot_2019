package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.FollowLine;
import frc.robot.commands.LimelightFollow;

public class LimeLightLineFollow extends CommandGroup {
    private double travelSpeed;
    public LimeLightLineFollow (double speed){
        travelSpeed = speed;
        addSequential(new LimelightFollow(travelSpeed,true));
        addSequential(new FollowLine());

    }
}


