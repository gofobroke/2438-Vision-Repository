/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

/**
 *
 * @author wkd
 */
public class HomeThrower extends CommandBase {
    
    private static final double HOMING_POWER           = 0.25;
    private static final double HOME_THRESHOLD_DEGREES = 10.0;
    
    private static final int sSTART  = 0;
    private static final int sHOMING = 1;
    private static final int sDONE   = 2;
    
    private int _state;
    private double _last_angle;
    public HomeThrower() {
        this.requires(thrower);
        this.requires(loader);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        _state = (!this.thrower.isHomed())? sSTART: sDONE;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        switch(_state) {
            case sSTART:
                this.loader.setFlipped(false);
                if(!this.loader.isFlipped()) {
                    _state = sHOMING;
                }
                break;
            case sHOMING:
                this.thrower.setThrowerPower(HOMING_POWER);
                double delta = (this.thrower.getArmPositionDegrees() - _last_angle);
                if(delta < -HOME_THRESHOLD_DEGREES) {
                    _state = sDONE;
                }
                break;
        }
        _last_angle = this.thrower.getArmPositionDegrees();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (_state == sDONE);
    }

    // Called once after isFinished returns true
    protected void end() {
        this.thrower.setHomed(true);
        this.thrower.setThrowerPower(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
       this.thrower.setHomed(false);
    }
}
