/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.iolani.frc.util.Utility;

/**
 *
 * @author wkd
 */
public class TestThrower extends CommandBase {
    
    public TestThrower() {
        this.requires(drivetrain);
        this.requires(thrower);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double pwr = Utility.window(oi.getRightStick().getY(), 0.500);
        //if(this.thrower.isUpperLimitTripped()) pwr = 0.0;
        this.thrower.setThrowerPower(pwr);
        SmartDashboard.putDouble("arm power", pwr);
        SmartDashboard.putBoolean("lower limit", this.thrower.isLowerLimitTripped(1));
        SmartDashboard.putBoolean("upper limit", this.thrower.isUpperLimitTripped(1));
        SmartDashboard.putBoolean("lower limit2", this.thrower.isLowerLimitTripped(2));
        SmartDashboard.putBoolean("upper limit2", this.thrower.isUpperLimitTripped(2));
        SmartDashboard.putDouble("arm degrees", this.thrower.getArmPositionDegrees());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
