/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.iolani.frc.subsystems.Box;

/**
 *
 * @author wkd
 */
public class LoadBall extends CommandBase {
    
    private static final int sSTART        = 0;
    private static final int sARMED        = 1;
    private static final int sWAIT_TO_FLIP = 2;
    private static final int sFLIPPED      = 3;
    private static final int sDONE         = 4;
    
    private static final double TRIP_DELAY = 0.6;
    private static final double DONE_DELAY = 1.5;
    
    private boolean _endWhenDone;
    private int _state;
    private double _delay_end;
    public LoadBall(boolean endWhenDone) {
        this.requires(loader);
        _endWhenDone = endWhenDone;
    }
    public LoadBall() {
        this(false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.updateDashboard();
        _state = sSTART;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        this.updateDashboard();
        switch(_state) {
            case sSTART:
                this.loader.setFlipped(false);
                if(!this.loader.isFlipped()) {
                    _state = sARMED;
                }
                break;
            case sARMED:
                if(this.loader.hasBall()) {
                    _state = sWAIT_TO_FLIP;
                    _delay_end = (this.timeSinceInitialized() + TRIP_DELAY);
                }
                break;
            case sWAIT_TO_FLIP:
                if((this.timeSinceInitialized() >= _delay_end) && this.thrower.isLowerLimitTripped()) {
                    this.loader.setFlipped(true);
                    _state = sFLIPPED;
                    _delay_end = (this.timeSinceInitialized() + DONE_DELAY);
                }
                break;
            case sFLIPPED:
                if(_endWhenDone && (this.timeSinceInitialized() >= _delay_end)) {
                    _state = sDONE;
                }
                break;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (_state == sDONE);
    }

    // Called once after isFinished returns true
    protected void end() {
        this.updateDashboard();
        this.loader.setFlipped(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.end();
    }
    
    private void updateDashboard() {
        //SmartDashboard.putInt("load_state", _state);
        //SmartDashboard.putBoolean("ballInLoader", this.loader.hasBall());
        //SmartDashboard.putBoolean("loaderFlipped", this.loader.isFlipped());
    }
}
