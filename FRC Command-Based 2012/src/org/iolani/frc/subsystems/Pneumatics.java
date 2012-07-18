/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.RunCompressor;
import org.iolani.frc.util.Utility;

/**
 *
 * @author wkd
 */
public class Pneumatics extends Subsystem {
    
    private Compressor _compressor = new Compressor(
            RobotMap.pneumaticsPressureSwitch,
            RobotMap.pneumaticsCompressorRelay
        );
    private boolean _state;
    
    public void init() {
    }
    
    public void setEnabled(boolean state) {
        if(state) {
            _compressor.start();
        } else {
            _compressor.stop();
        }
        _state = state;
    }
    
    public boolean getEnabled() {
        return _state;
    }
    
    public boolean isCompressorRunning() {
        return _compressor.enabled();
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand(new RunCompressor());
    }
}
