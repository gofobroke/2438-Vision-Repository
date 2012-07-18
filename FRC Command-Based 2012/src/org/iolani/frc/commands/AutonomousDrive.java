/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

/**
 *
 * @author wkd
 */
public class AutonomousDrive extends CommandBase {
    
    private final double _left, _right;
    public AutonomousDrive(double left, double right, double time) {
        this.requires(drivetrain);
        _left  = left;
        _right = right;
        this.setTimeout(time);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.drivetrain.resetYawGyro();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // FIXME: add PID drive-straight code //
        this.drivetrain.setTank(_left, _right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
        this.drivetrain.setTank(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.end();
    }
}
