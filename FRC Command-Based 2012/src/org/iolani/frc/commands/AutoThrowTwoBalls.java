/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 * @author wkd
 */
public class AutoThrowTwoBalls extends CommandGroup {
    
    public AutoThrowTwoBalls() {
        this.addSequential(new HomeThrower());
        this.addSequential(new ParkThrower(true));
        this.addSequential(new SetThrowParameters(CommandBase.oi.KEY_THROW));
        this.addSequential(new LoadBall(true));
        this.addSequential(new WaitCommand(1.0));
        this.addSequential(new ThrowBall());
        this.addSequential(new ParkThrower(true));
        this.addSequential(new EjectOneBall());
        this.addSequential(new LoadBall(true));
        this.addSequential(new WaitCommand(1.0));
        this.addSequential(new ThrowBall());
        this.addSequential(new ParkThrower(true));
    }
}
