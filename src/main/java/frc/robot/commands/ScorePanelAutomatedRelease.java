package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.HatchManipulator;
import frc.robot.commands.DoNothing;

public class ScorePanelAutomatedRelease extends CommandGroup {
    public ScorePanelAutomatedRelease (){
        addSequential(new RaiseCargo()); //This needs to be in here to ensure that there is no collision
        addSequential(new CloseGrabber());
        addSequential(new DoNothing(), 0.33);
        addSequential(new RetractHatch());
        addSequential(new RaiseAligner());
    }
}