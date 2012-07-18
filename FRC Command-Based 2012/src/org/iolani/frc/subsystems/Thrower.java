/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;


import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.ParkThrower;
import org.iolani.frc.util.Utility;
/**
 *
 * @author wkd
 */
public class Thrower extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public static CANJaguar _jaguar1;
    public static CANJaguar _jaguar2;
    public static Encoder   _encoder = new Encoder(
            RobotMap.throwerEncoderChannelA,
            RobotMap.throwerEncoderChannelB,
            RobotMap.throwerEncoderIndex
        );
    private ThrowParameters _params;
    private double _power;
    private boolean _homed;
    
    public void init() {
        _jaguar1 = Utility.createJaguar("thrower 1", RobotMap.throwerJaguarID1);
        _jaguar2 = Utility.createJaguar("thrower 2", RobotMap.throwerJaguarID2);
        _encoder.start();
    }
    
    public double getArmPositionDegrees() {
        return (double) (_encoder.get()) * 360.0 / 2048;
    }
    
    public void setHomed(boolean state) {
        _homed = state;
    }
    
    public boolean isHomed() {
        return _homed;
    }
    
    public void setThrowerPower(double pwr) {
        if(_power == pwr) return;
        try {
            _jaguar1.setX(pwr, (byte) 1);
            _jaguar2.setX(pwr, (byte) 1);
            CANJaguar.updateSyncGroup((byte) 1);
            _power = pwr;
        } catch(CANTimeoutException e) {
            System.out.println("Error updating thrower Jaguars: " + e);
        }
    }
    
    public double getThrowerPower() {
        return _power;
    }
    
    public boolean isLowerLimitTripped() {
        return this.isLowerLimitTripped(1);
    }
    
    public boolean isLowerLimitTripped(int jaguarNum) {
        try {
            return !this.getJaguar(jaguarNum).getReverseLimitOK();
        } catch(CANTimeoutException e) {
            System.out.println("Error checking arm lower limit: " + e);
            return false;
        }
    }
    
    public boolean isUpperLimitTripped() {
        return this.isUpperLimitTripped(1);
    }
    
    public boolean isUpperLimitTripped(int jaguarNum) {
        try {
            return !this.getJaguar(jaguarNum).getForwardLimitOK();
        } catch(CANTimeoutException e) {
            System.out.println("Error checking arm upper limit: " + e);
            return false;
        }
    }
    
    private CANJaguar getJaguar(int jaguarNum) {
        switch(jaguarNum) {
            case 1: return _jaguar1;
            case 2: return _jaguar2;
            default: 
                throw new IllegalArgumentException("Invalid Jaguar ID: " + jaguarNum);
        }
    }
    
    public void setThrowParameters(ThrowParameters params) {
        _params = params; 
    }
    
    public ThrowParameters getThrowParameters() {
        if(_params == null) throw new IllegalStateException("Throw parameters not set");
        return _params;
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand(new ParkThrower());
    }
    
    public static class ThrowParameters {
        public final double power;
        public final double angle;
        public ThrowParameters(double pwr, double ang) {
            this.power = pwr;
            this.angle = ang;
        }
    }
}
