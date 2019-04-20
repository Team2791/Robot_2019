package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.FollowLine;
import frc.robot.commands.LimelightFollow;

public class LimeLightLineFollow extends CommandGroup {
    private double travelSpeedLOW;
    private double travelSpeedHIGH;
    private boolean useScaled;
    private boolean useLineFollow;
    public LimeLightLineFollow (double speedLOW, double speedHIGH, boolean LineFollow, boolean scaled){
        travelSpeedLOW = speedLOW;
        travelSpeedHIGH = speedHIGH;
        useLineFollow = LineFollow;
        useScaled = scaled;
        addSequential(new LimelightFollow(travelSpeedLOW, travelSpeedHIGH, useLineFollow, useScaled));
        addSequential(new FollowLine());
    }
}


