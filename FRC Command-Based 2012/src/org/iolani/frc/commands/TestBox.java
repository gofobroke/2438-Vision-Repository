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
public class TestBox extends CommandBase {
    
    public TestBox() {
        this.requires(box);
        this.requires(drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        SmartDashboard.putBoolean("ballInLoader", this.loader.hasBall());
        SmartDashboard.putBoolean("loaderFlipped", this.loader.isFlipped());
        SmartDashboard.putBoolean("ballAtExit", this.box.isBallAtExit());
        this.box.setExitRoller(Box.ExitRollerMode.kForward);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        this.box.setExitRoller(Box.ExitRollerMode.kOff);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.end();
    }
}
