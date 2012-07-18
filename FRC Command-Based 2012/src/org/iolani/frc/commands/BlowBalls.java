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
public class BlowBalls extends CommandGroup {
    
    public BlowBalls() {
        this.addSequential(new SetBoxDeployed(true));
        this.addSequential(new SetBoxIntake(Box.IntakeMode.kBlow));
    }
}
