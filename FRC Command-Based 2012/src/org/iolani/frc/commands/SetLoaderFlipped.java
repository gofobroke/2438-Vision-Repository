/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

/**
 *
 * @author wkd
 */
public class SetLoaderFlipped extends CommandBase {
    
    private static final double FLIP_TIME = 0.5;
    
    private final boolean _state;
    private boolean _wait;
    public SetLoaderFlipped(boolean state) {
        this.requires(loader);
        this.setTimeout(FLIP_TIME);
        _state = state;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        _wait = (this.loader.isFlipped() != _state);
        this.loader.setFlipped(_state);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (_wait)? this.isTimedOut(): true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
