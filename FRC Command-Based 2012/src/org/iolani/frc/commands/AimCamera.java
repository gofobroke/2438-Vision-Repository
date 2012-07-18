/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author wkd
 */
public class AimCamera extends CommandBase {
    
    private static final double PITCH_JOG = 5.0;
    private static final double YAW_JOG   = 5.0;
    
    private static final double MIN_PITCH = 0.0;
    private static final double MAX_PITCH = 180.0;
    private static final double MIN_YAW   = 0.0;
    private static final double MAX_YAW   = 180.0;
        
    private static final CameraPosition pFORWARD = new CameraPosition(77, 80);
    private static final CameraPosition pDOWN    = new CameraPosition(0, 80);
    private static final CameraPosition pDRIVE   = new CameraPosition(77, 0);
    
    public AimCamera() {
        this.requires(camera);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        this.setPosition(pFORWARD);
        this.camera.setRingLight(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // implement manual jog //
        double y = oi.getNESStick().getY();
        if(Math.abs(y) > 0.1) {
            double val = this.camera.getPitch() - y*PITCH_JOG;
            if(val > MAX_PITCH) val = MAX_PITCH;
            if(val < MIN_PITCH) val = MIN_PITCH;
            this.camera.setPitch(val);
        }
        double x = oi.getNESStick().getX();
        if(Math.abs(x) > 0.1) {
            double val = this.camera.getYaw() + x*YAW_JOG;
            if(val > MAX_YAW) val = MAX_YAW;
            if(val < MIN_YAW) val = MIN_YAW;
            this.camera.setYaw(val);
        }
        // preset positions //
        if(oi.getNESStick().getAButton().get()) 
            this.setPosition(pFORWARD);
        else if(oi.getNESStick().getBButton().get()) 
            this.setPosition(pDOWN);
        else if(oi.getNESStick().getStartButton().get()) 
            this.setPosition(pDRIVE);
        SmartDashboard.putDouble("camera pitch", this.camera.getPitch());
        SmartDashboard.putDouble("camera yaw", this.camera.getYaw());
    }

    private void setPosition(CameraPosition pos) {
        this.camera.setPitch(pos.pitch);
        this.camera.setYaw(pos.yaw);
    }
    
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
        this.setPosition(pFORWARD);
        this.camera.setRingLight(false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        this.end();
    }
    
    // camera preset position class //
    public static class CameraPosition {
        public double pitch, yaw;
        public CameraPosition(double ipitch, double iyaw) {
            this.pitch = ipitch;
            this.yaw = iyaw;
        }
    }
}
