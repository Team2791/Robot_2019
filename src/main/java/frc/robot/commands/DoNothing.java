package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class DoNothing extends Command {
    public DoNothing() {
        super("DoNothing");
    }

    public boolean isFinished() {
        return false;
    }
}