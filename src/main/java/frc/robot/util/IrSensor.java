package frc.robot.util;

import edu.wpi.first.wpilibj.AnalogInput;

public class IrSensor {
    //This is an abstraction of a Sharp 2Y0A21 IR sensor.
    AnalogInput sensor;
    public IrSensor(int channel) {
        sensor = new AnalogInput(channel);
    }

    public double getInches() {
        int value = sensor.getValue();
        double cm = 4800.0/(value - 20);
        return cm / 2.54;
    }
}