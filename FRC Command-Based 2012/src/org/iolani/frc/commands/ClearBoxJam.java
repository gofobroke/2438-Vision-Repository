/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import org.iolani.frc.subsystems.Box;

/**
 *
 * @author wkd
 */
public class ClearBoxJam extends CommandBase {
    
    public ClearBoxJam() {
        this.requires(box);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        this.box.setExitRoller(Box.ExitRollerMode.kReverse);
        this.box.setConveyor(Box.ConveyorMode.kDown);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        this.box.setExitRoller(Box.ExitRollerMode.kOff);
        this.box.setConveyor(Box.ConveyorMode.kOff);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.end();
    }
}
