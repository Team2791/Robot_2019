package frc.robot.controller;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

public class DPadButton extends Button {
    public static final int kDPadUp = 0;
    public static final int kDPadRight = 1;
    public static final int kDPadDown = 2;
    public static final int kDPadLeft = 3;
    private Joystick stick;
    private int direction;

    public DPadButton(Joystick stick, int direction) {
        this.direction = direction;
        this.stick = stick;
    }

    public boolean get() {
        return stick.getPOV() == direction * 90 ||
            stick.getPOV() == (direction * 90 - 45) % 360 ||
            stick.getPOV() == (direction * 90 + 45) % 360;
    }
}