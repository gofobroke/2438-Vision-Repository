/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.SetTeamColor;

/**
 *
 * @author wkd
 */
public class Decorations extends Subsystem {
    
    private static Relay _lights = new Relay(RobotMap.decorationsRelay);
    
    public void init() {
        _lights.set(Relay.Value.kOff);
    }

    public void setColor(DriverStation.Alliance alliance) {
        if(alliance == DriverStation.Alliance.kBlue) {
            _lights.set(Relay.Value.kForward);
        } else if(alliance == DriverStation.Alliance.kRed) {
            _lights.set(Relay.Value.kReverse);
        } else {
            _lights.set(Relay.Value.kOff);
        }
    }
    
    public void initDefaultCommand() {
        this.setDefaultCommand(new SetTeamColor());
    }
}
