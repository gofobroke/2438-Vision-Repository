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
public class DisableBox extends CommandGroup {
    
    public DisableBox() {
        this.addSequential(new SetBoxExitRoller(Box.ExitRollerMode.kOff));
        this.addSequential(new SetBoxConveyor(Box.ConveyorMode.kOff));
        this.addSequential(new SetBoxDeployed(false));
        this.addSequential(new SetBoxIntake(Box.IntakeMode.kOff));
    }
}
