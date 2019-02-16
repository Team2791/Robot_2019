package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.HatchManipulator;
import frc.robot.commands.DoNothing;

public class ScorePanelAutomatedHeld extends CommandGroup {
    public ScorePanelAutomatedHeld (){
        addSequential(new RaiseCargo()); //This needs to be in here to ensure that there is no collision
        addSequential(new OpenGrabber());
        addSequential(new DropAligner());
        addSequential(new ExtendHatch());
    }
}