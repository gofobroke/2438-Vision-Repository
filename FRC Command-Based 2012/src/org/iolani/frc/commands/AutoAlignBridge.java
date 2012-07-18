/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 * @author wkd
 */
public class AutoAlignBridge extends CommandGroup {
    
    public AutoAlignBridge() {
        this.addSequential(new AutonomousDrive(-0.5, -0.5, 0.3));
        this.addSequential(new ResetGyro(ResetGyro.GyroType.kPitch));
    }
}
