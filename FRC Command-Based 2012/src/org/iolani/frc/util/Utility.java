/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.util;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.*;

/**
 *
 * @author wkd
 */
public class Utility {
    
    public static CANJaguar createJaguar(String name, int id) {
        try {
            return new CANJaguar(id);
        } catch (CANTimeoutException e) {
            System.out.println("Error initializing Jaguar: " + name + "\n" + e);
            return null;
        }
    }
    
    public static boolean setJaguar(CANJaguar jag, double x) {
        try {
            jag.setX(x);
            return true;
        } catch (CANTimeoutException e) {
            System.out.println("Error setting Jaguar: " + jag.toString() + "\n" + e);
            return false;
        }
    }
    
    public static double window(double input, double max) {
        if(input >  max) return  max;
        if(input < -max) return -max;
        return input;
    }
    
    public static double sign(double input) {
        if(input == 0.0) return 0.0;
        return (input > 0.0) ? 1.0 : -1.0; 
    }
}
