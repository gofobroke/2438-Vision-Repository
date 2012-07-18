/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.AimCamera;

/**
 *
 * @author wkd
 */
public class Camera extends Subsystem {
    
    private static AxisCamera _camera;
    private static Relay      _ringLight  = new Relay(RobotMap.cameraRingLightRelay);
    private static Servo      _pitchServo = new Servo(RobotMap.cameraPitchServo),
                              _yawServo   = new Servo(RobotMap.cameraYawServo);
    private boolean _started;
    private double _pitch;
    private double _yaw;
    private boolean _lightOn;
    
    public void init() {
        _ringLight.setDirection(Relay.Direction.kForward);
    }
    
    public AxisCamera getCamera() {
        if(!_started) throw new IllegalStateException("Camera is not started");
        return _camera;
    }
    
    public boolean isStarted() {
        return _started;
    }
    
    public void startCamera() {
        if(_started) return;
        new InitializeCamera().start();
    }
    
    public void setPitch(double angle) {
        _pitchServo.setAngle(angle);
        _pitch = angle;
    }
    
    public double getPitch() {
        return _pitch;
    }
    
    public void setYaw(double angle) {
        _yawServo.setAngle(angle);
        _yaw = angle;
    }
    
    public double getYaw() {
        return _yaw;
    }
    
    public void setRingLight(boolean state) {
        _ringLight.set(state? Relay.Value.kOn: Relay.Value.kOff);
        _lightOn = state;
    }
    
    public boolean getRingLight() {
        return _lightOn;
    }
    
    public void initDefaultCommand() {
        //this.setDefaultCommand(new AimCamera());
    }
    
    private class InitializeCamera extends Command {

        private static final double CAMERA_INIT_TIME = 10.0;
        
        public InitializeCamera() {
            this.setTimeout(CAMERA_INIT_TIME);
            this.setRunWhenDisabled(true);
        }
        
        // Called just before this Command runs the first time
        protected void initialize() {
            Camera.this._camera = AxisCamera.getInstance(RobotMap.cameraIPAddress);
        }

        // Called repeatedly when this Command is scheduled to run
        protected void execute() {
            // just wait for time out //
        }

        // Make this return true when this Command no longer needs to run execute()
        protected boolean isFinished() {
            return this.isTimedOut();
        }

        // Called once after isFinished returns true
        protected void end() {
            Camera.this._camera.writeResolution(AxisCamera.ResolutionT.k320x240);
            Camera.this._camera.writeBrightness(50);
            Camera.this._camera.writeExposurePriority(AxisCamera.ExposurePriorityT.frameRate);
            Camera.this._camera.writeExposureControl(AxisCamera.ExposureT.automatic);
            Camera.this._started = true;
        }

        // Called when another command which requires one or more of the same
        // subsystems is scheduled to run
        protected void interrupted() {
            this.end();
        }
    }
}
