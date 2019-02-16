package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.subsystems.HatchManipulator;
public class GetPanelAutomatedHeld extends CommandGroup {
    public GetPanelAutomatedHeld (){
        addSequential(new RaiseCargo()); //This needs to be in here to ensure that there is no collision
        addSequential(new DropAligner());
        addSequential(new CloseGrabber());
        addSequential(new ExtendHatch());
    }
}