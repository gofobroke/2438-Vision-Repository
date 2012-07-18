/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.PrintCommand;
import org.iolani.frc.commands.SetThrowParameters;

/**
 *
 * @author wkd
 */
public class RobotStartup extends CommandGroup {
    
    public RobotStartup() {
        this.addSequential(new PrintCommand("Robot Started"));
        this.addSequential(new SetThrowParameters(CommandBase.oi.FENDER_THROW));
        this.addSequential(new HomeThrower());
    }
}
