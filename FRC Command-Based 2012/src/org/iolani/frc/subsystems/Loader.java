/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;

/**
 *
 * @author wkd
 */
public class Loader extends Subsystem {
    
    private static DoubleSolenoid _loader = new DoubleSolenoid(
            RobotMap.loaderFlipSolenoid,
            RobotMap.loaderUnflipSolenoid
        );
    private static DigitalInput _ballSwitch = new DigitalInput(RobotMap.loaderBallSwitch);
    private static DigitalInput _homeSwitch = new DigitalInput(RobotMap.loaderHomeSwitch);
    
    public void init() {
    }
    
    public void setFlipped(boolean state) {
        if(state) {
            _loader.set(DoubleSolenoid.Value.kForward);
        } else {
            _loader.set(DoubleSolenoid.Value.kReverse);
        }
    }
    
    public boolean isFlipped() {
        return _homeSwitch.get();
    }
    
    public boolean hasBall() {
        return !_ballSwitch.get();
    }
    
    public void initDefaultCommand() {
    }
}
