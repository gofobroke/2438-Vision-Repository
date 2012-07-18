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
public class EjectOneBall extends CommandBase {
    
    private static final int sSTART         = 0;
    private static final int sBALL_DEBOUNCE = 1;
    private static final int sBALL_LEAVING  = 2;
    private static final int sBALL_EJECT    = 3;
    private static final int sDONE          = 4;
    
    private static final double DEBOUNCE_TIME = 0.1;
    private static final double EJECT_TIME = 0.1;
    
    private int _state;
    private double _delay_end;
    public EjectOneBall() {
        this.requires(box);
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
                if(this.loader.hasBall()) {
                    _state = sDONE;
                    break;
                }
                this.box.setExitRoller(Box.ExitRollerMode.kForward);
                this.box.setConveyor(Box.ConveyorMode.kUp);
                if(this.box.isBallAtExit()) {
                    _state = sBALL_DEBOUNCE;
                    _delay_end = this.timeSinceInitialized() + DEBOUNCE_TIME;
                }
                break;
            case sBALL_DEBOUNCE:
                if(this.timeSinceInitialized() >= _delay_end) {
                    _state = sBALL_LEAVING;
                }
                break;
            case sBALL_LEAVING:
                if(!this.box.isBallAtExit()) {
                    _state = sBALL_EJECT;
                    _delay_end = this.timeSinceInitialized() + EJECT_TIME;
                }
                break;
            case sBALL_EJECT:
                if(this.timeSinceInitialized() >= _delay_end) {
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
        this.box.setExitRoller(Box.ExitRollerMode.kOff);
        this.box.setConveyor(Box.ConveyorMode.kOff);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.end();
    }
    
    private void updateDashboard() {
        //SmartDashboard.putInt("state", _state);
        //SmartDashboard.putBoolean("ballAtExit", this.box.isBallAtExit());
    }
}
