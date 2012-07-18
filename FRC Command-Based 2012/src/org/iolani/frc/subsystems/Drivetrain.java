/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

/**
 *
 * @author Michael
 */
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.OperatorDrive;
import org.iolani.frc.util.*;

public class Drivetrain extends Subsystem {

    // mode //
    private final boolean _victors;
    
    // real bot //
    private CANJaguar _LFj, _LRj, _RFj, _RRj;
    // junk bot //
    private Victor _LFv, _LRv, _RFv, _RRv;
    // shared drive //
    private RobotDrive _drive;
    private Gyro _pitchGyro = new Gyro(RobotMap.drivePitchGyroChannel);
    private Gyro _yawGyro   = new Gyro(RobotMap.driveYawGyroChannel);
    
    private PowerScaler _scale;
    public Drivetrain(boolean victors) {
        _victors = victors;
    }
  
    public void init() {
        if(!_victors) {
            _LFj = Utility.createJaguar("LF", RobotMap.driveLeftFrontJaguarID);
            _LRj = Utility.createJaguar("LR", RobotMap.driveLeftRearJaguarID);
            _RFj = Utility.createJaguar("RF", RobotMap.driveRightFrontJaguarID);
            _RRj = Utility.createJaguar("RR", RobotMap.driveRightRearJaguarID);
            _drive = new RobotDrive(_LFj, _LRj, _RFj, _RRj);
        } else {
            _LFv = new Victor(RobotMap.driveLeftFrontVictorPWM);
            _LRv = new Victor(RobotMap.driveLeftRearVictorPWM);
            _RFv = new Victor(RobotMap.driveRightFrontVictorPWM);
            _RRv = new Victor(RobotMap.driveRightRearVictorPWM);
            _drive = new RobotDrive(_LFv, _LRv, _RFv, _RRv);
        }
        _drive.setSafetyEnabled(false);
    }
    
    public double getPitchGyroDegrees() {
        return _pitchGyro.getAngle();
    }
    
    public void resetPitchGyro() {
        _pitchGyro.reset();
    }
    
    public double getYawGyroDegrees() {
        return _pitchGyro.getAngle();
    }
    
    public void resetYawGyro() {
        _pitchGyro.reset();
    }
    
    public void setMecanum(double mag, double dir, double rot) {
        _drive.mecanumDrive_Polar(mag, dir, rot);
    }
    
    public void setTank(double left, double right) {
        _drive.tankDrive(left, -right);
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand(new OperatorDrive());
    }
}
