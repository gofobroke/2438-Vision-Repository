/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

import edu.wpi.first.wpilibj.DriverStation;

/**
 *
 * @author wkd
 */
public class SetTeamColor extends CommandBase {
    
    public SetTeamColor() {
        this.requires(decorations);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        this.decorations.setColor(DriverStation.getInstance().getAlliance());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        this.decorations.setColor(DriverStation.Alliance.kInvalid);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.end();
    }
}
