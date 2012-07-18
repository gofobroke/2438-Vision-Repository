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
public class SetThrowParameters extends CommandBase {
    
    private boolean _auto;
    private Thrower.ThrowParameters _params;
    private SetThrowParameters(boolean auto, Thrower.ThrowParameters params) {
        this.requires(thrower);
        if(!auto) {
            if(params.power < 0 || params.power > 1.0) 
                throw new IllegalArgumentException("Invalid power: " + params.power);
            if(params.angle < 0 || params.angle > 50) 
                throw new IllegalArgumentException("Invalid angle: " + params.angle);
            _params = params;
        }
        _auto = auto;
    }
    public SetThrowParameters() {
        this(true, new Thrower.ThrowParameters(0.0, 0.0));
    }
    public SetThrowParameters(double power, double angle) {
        this(false, new Thrower.ThrowParameters(power, angle));
    }
    public SetThrowParameters(Thrower.ThrowParameters params) {
        this(false, params);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Thrower.ThrowParameters params = _params; 
        if(_auto) {
            double angle = ((oi.getLeftStick().getTwist() + 1) * 0.5) * 50;
            double power = ((oi.getRightStick().getTwist() + 1) * 0.5);
            params = _params = new Thrower.ThrowParameters(power, angle);
        } else {
            double power = _params.power + (oi.getRightStick().getTwist() * -0.1);
            params = new Thrower.ThrowParameters(power, _params.angle);
        }
        //SmartDashboard.putDouble("throw angle", _params.angle);
        //SmartDashboard.putDouble("throw power", _params.power);
        System.out.println("throw: " + params.power + ", " + params.angle);
        this.thrower.setThrowParameters(params);
        
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
        this.end();
    }
}
