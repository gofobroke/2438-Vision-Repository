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
public class LoadOneBall extends CommandGroup {
    
    public LoadOneBall() {
        this.addSequential(new EjectOneBall());
        this.addSequential(new LoadBall());
    }
}
