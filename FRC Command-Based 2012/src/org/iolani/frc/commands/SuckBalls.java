/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.iolani.frc.subsystems.Box;

/**
 *
 * @author wkd
 */
public class SuckBalls extends CommandGroup {
    
    public SuckBalls() {
        this.addSequential(new SetBoxDeployed(true));
        this.addSequential(new SetBoxIntake(Box.IntakeMode.kSuck));
    }
}
