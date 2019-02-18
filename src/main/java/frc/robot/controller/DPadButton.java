package frc.robot.controller;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class DPadButton extends Button {
    private Joystick stick;
    private int direction;

    public DPadButton(Joystick stick, int direction) {
        this.direction = direction;
        this.stick = stick;
    }

    public boolean get() {
        return stick.getPOV() == direction * 90;
    }
}