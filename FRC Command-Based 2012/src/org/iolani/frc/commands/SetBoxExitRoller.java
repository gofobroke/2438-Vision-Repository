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
public class SetBoxExitRoller extends CommandBase {
    
    private final Box.ExitRollerMode _mode;
    public SetBoxExitRoller(Box.ExitRollerMode mode) {
        this.requires(box);
        _mode = mode;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.box.setExitRoller(_mode);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.box.setExitRoller(Box.ExitRollerMode.kOff);
    }
}
