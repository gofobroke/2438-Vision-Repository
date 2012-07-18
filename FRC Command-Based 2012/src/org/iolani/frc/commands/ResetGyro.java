/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

/**
 *
 * @author wkd
 */
public class ResetGyro extends CommandBase {
    
    private final GyroType _type;
    public ResetGyro(GyroType type) {
        _type = type;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        switch(_type.value) {
            case GyroType.kPitch_val:
                this.drivetrain.resetPitchGyro();
                break;
            case GyroType.kYaw_val:
                this.drivetrain.resetYawGyro();
                break;
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
        this.end();
    }
    
    public static class GyroType {
        public static final int kPitch_val = 0;
        public static final int kYaw_val   = 1;
        public static final GyroType kPitch = new GyroType(kPitch_val);
        public static final GyroType kYaw   = new GyroType(kYaw_val);
        public final int value;
        public GyroType(int val) {
            this.value = val;
        }
    }
}
