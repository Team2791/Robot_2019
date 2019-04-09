package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.FollowLine;
import frc.robot.commands.LimelightFollow;

public class LimeLightLineFollow extends CommandGroup {
    public LimeLightLineFollow (){
        addSequential(new LimelightFollow());
        addSequential(new FollowLine());

    }
}


