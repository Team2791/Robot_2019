package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.HatchManipulator;
import frc.robot.commands.DoNothing;

public class GetPanelAutomatedRelease extends CommandGroup {
    public GetPanelAutomatedRelease (){
        addSequential(new RaiseCargo()); //This needs to be in here to ensure that there is no collision
        addSequential(new OpenGrabber());
        addSequential(new DoNothing(), 0.25); //This is how you add a 1/3 second delay
        addSequential(new RetractHatch());
        addSequential(new RaiseAligner());
    }
}