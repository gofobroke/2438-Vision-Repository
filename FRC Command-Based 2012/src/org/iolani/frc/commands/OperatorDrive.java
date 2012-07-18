/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;
import org.iolani.frc.util.*;

/**
 *
 * @author wkd
 */
public class OperatorDrive extends CommandBase {

    public OperatorDrive() {
        requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double mag = oi.getRightStick().getMagnitude();
        double dir = oi.getRightStick().getDirectionDegrees();
        double rot = oi.getLeftStick().getX();
        PowerScaler scale = oi.getDriveScaler();
        if(scale != null) {
            mag = scale.get(mag);
            rot = scale.get(rot);
        }
        drivetrain.setMecanum(mag, dir, rot);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        drivetrain.setMecanum(0, 0, 0);
    }

    protected void interrupted() {
        this.end();
    }
}
