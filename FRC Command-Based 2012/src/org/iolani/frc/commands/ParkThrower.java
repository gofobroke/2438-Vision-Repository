/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

/**
 *
 * @author wkd
 */
public class ParkThrower extends CommandBase {
    
    private static final double PARKING_POWER = 0.15;
    
    private boolean _endWhenDone;
    public ParkThrower(boolean endWhenDone) {
        this.requires(thrower);
        _endWhenDone = endWhenDone;
    }
    public ParkThrower() {
        this(false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double pwr = (!this.thrower.isLowerLimitTripped())? -PARKING_POWER: 0.0;
        this.thrower.setThrowerPower(pwr);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return _endWhenDone && this.thrower.isLowerLimitTripped()
            && (this.thrower.getArmPositionDegrees() < 0);
    }

    // Called once after isFinished returns true
    protected void end() {
        this.thrower.setThrowerPower(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.end();
    }
}
