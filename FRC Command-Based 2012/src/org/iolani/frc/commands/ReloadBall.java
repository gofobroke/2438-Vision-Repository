/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 * @author wkd
 */
public class ReloadBall extends CommandBase {
    
    public ReloadBall() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Command cmd = this.loader.getCurrentCommand();
        if((cmd instanceof LoadOneBall) || (cmd instanceof LoadBall)) {
            new LoadBall().start();
        }
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
    }
}
