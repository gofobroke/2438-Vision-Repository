/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.iolani.frc.subsystems.Thrower;

/**
 *
 * @author wkd
 */
public class ThrowBall extends CommandBase {
    
    private static final double LOADER_WAIT_DELAY = 1.0;
    //private static final double THROW_ANGLE = 30.0;
    //private static final double THROW_POWER = 1.0;
    
    private static final int sSTART       = 0;
    private static final int sWAIT_LOADER = 1;
    private static final int sTHROW       = 2;
    private static final int sDONE        = 3;
    
    private int _state;
    private double _delay_end;
    public ThrowBall() {
        this.requires(thrower);
        this.requires(loader);
        this.requires(pneumatics); // take over pneumatics to disable compressor //
        this.setInterruptible(false);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        if(this.thrower.isHomed()) {
            _state = sSTART;
        } else {
            _state = sDONE;
            System.out.println("ERROR: Cannot throw, thrower not homed.");
        }
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        switch(_state) {
            case sSTART:
                this.loader.setFlipped(false);
                _delay_end = this.timeSinceInitialized() + LOADER_WAIT_DELAY;
                _state = sWAIT_LOADER;
                break;
            case sWAIT_LOADER:
                if(!this.loader.isFlipped()) {
                    _state = sTHROW;
                    break;
                }
                // check for timeout //
                if(this.timeSinceInitialized() >= _delay_end) {
                    _state = sDONE;
                }
                break;
            case sTHROW:
                Thrower.ThrowParameters params = this.thrower.getThrowParameters();
                if(this.thrower.getArmPositionDegrees() > params.angle) {
                    this.thrower.setThrowerPower(0.0);
                    _state = sDONE;
                    break;
                }
                this.thrower.setThrowerPower(params.power);
                break;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (_state == sDONE);
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.end();
    }
}
