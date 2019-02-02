package frc.robot.util;

import edu.wpi.first.wpilibj.AnalogInput;

public class IrSensor {
    // Pulled from Sensor specs.  The range is 80 - 500, scaled up to 4096.  This might not be correct.
    private static int kMaxReadValue = 4096;
    private static int kMinReadValue = 655;
    
    // The specs for the IR Sensor were given in cm
    private static double kMaxDistance = 80 / 2.54;
    private static double kMinDistance = 10 / 2.54;
    AnalogInput sensor;
    public IrSensor(int channel) {
        sensor = new AnalogInput(channel);
    }

    public double getInches() {
        int value = sensor.getValue();
        double percent = (double)(value - kMinReadValue) / (kMaxReadValue - kMinReadValue);
        percent = 1 / percent;
        return percent * (kMaxDistance - kMinDistance) + kMinDistance;
    }
}