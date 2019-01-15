/*
Name: Limelight.java
Author: Sam O
Date: 1/18/2018
Info:
This is the limelight class to control robots limelight.
More info: http://docs.limelightvision.io/en/latest/
*/


package frc.robot.util.Limelight;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.util.Limelight.CameraMode;

public class Limelight {
    NetworkTable table;
    private NetworkTableEntry camMode, ledMode, horizontalOffset, verticalOffset, targetArea, validTargets, targetSkew, pipelineLatency;
    private static double kProportion = 1.56;
    //private double horizontalOffset,
     //verticalOffset, validTarget, targetArea, targetSkew, latency;


    public Limelight(){
        // Set table to limelight
        table = NetworkTableInstance.getDefault().getTable("limelight");

        // Get stats
        horizontalOffset = table.getEntry("tx");
        verticalOffset = table.getEntry("ty");
        targetArea = table.getEntry("ta");
        validTargets = table.getEntry("tv");
        targetSkew = table.getEntry("ts");
        pipelineLatency = table.getEntry("tl");

        ledMode = table.getEntry("ledMode");
        camMode = table.getEntry("camMode");



    }


    // Methods to get information
    public double getHorizontalOffset(){
        return horizontalOffset.getDouble(0.0);
    }
    public double getVerticalOffset(){
        return verticalOffset.getDouble(0.0);
    }
    public double getTargetArea(){
        return targetArea.getDouble(0.0);
    }
    public double getTargetSkew(){
        return targetSkew.getDouble(0.0);
    }
    public double getLatency(){
        return pipelineLatency.getDouble(0.0);
    }
    public boolean targetValid(){
    	if(verticalOffset.getDouble(0.0) == 1.0) {
    		return true;
    	} else {
    		return false;
    	}

    }
    public double getDistance(){
        double distance = getTargetArea()*kProportion;
        return distance;
    }


    // Methods to set Camera settings

    // Controls Leds
    // String mode must be either "on" or "off" or "blink"
    public void setLed(LedMode mode){
        if(mode == LedMode.On){
            ledMode.setNumber(0);

        }
        else if(mode == LedMode.Off){
            ledMode.setNumber(1);
        }
        else if(mode == LedMode.Blink){
            ledMode.setNumber(2);
        }
        
    }
    // Sets the camera to a operation mode
    // String mode must be either "vision" or "driver"
    public void setCameraMode(CameraMode mode){
        if(mode == CameraMode.Vision){
            camMode.setNumber(0);
        }
        else if(mode == CameraMode.Driver){
            camMode.setNumber(1);
        }
        

    }

    public void debug(){
        SmartDashboard.putString("Limelight Horizontal", Double.toString(getHorizontalOffset()));
        SmartDashboard.putString("Limelight Vertical", Double.toString(getVerticalOffset()));
        SmartDashboard.putString("Limelight Area", Double.toString(getTargetArea()));
        SmartDashboard.putString("Limelight Skew", Double.toString(getTargetSkew()));
        SmartDashboard.putString("Limelight Latency", Double.toString(getLatency()));
        SmartDashboard.putString("Limelight Valid", Boolean.toString(targetValid()));
        SmartDashboard.putString("Limelight Distance from Object", Double.toString(getDistance()));


    }
}