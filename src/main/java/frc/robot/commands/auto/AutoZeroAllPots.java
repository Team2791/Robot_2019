package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoZeroAllPots extends CommandGroup {
    public AutoZeroAllPots() {
        addParallel(new AutoSetElevatorPot());
        addParallel(new AutoSetLifterPots());
    }
}