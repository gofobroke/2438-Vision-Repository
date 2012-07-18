/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;

/**
 *
 * @author wkd
 */
public class AutoBalanceBridge extends PIDCommandBase {
    
    private static final double Kp = 1.0;
    private static final double Ki = 0.0;
    private static final double Kd = 0.0;
    private static final double PID_FREQ_Hz = 100;
    private static final double TOLERANCE   = 5;
    private static final double MAX_POWER   = 0.5;
    
    public AutoBalanceBridge() {
        super(Kp, Ki, Kd, 1/PID_FREQ_Hz);
        this.requires(drivetrain);
        this.getPIDController().setInputRange(-20.0, 20.0);
        this.getPIDController().setTolerance(TOLERANCE);
        this.getPIDController().setOutputRange(-MAX_POWER, MAX_POWER);
    }

    protected void initialize() {
        this.setSetpoint(0.0); // move to 0 degree gyro angle //
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return this.getPIDController().onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
        this.drivetrain.setTank(0, 0);
    }

    protected void interrupted() {
        this.end();
    }
    
    protected void usePIDOutput(double out) {
        this.drivetrain.setTank(out, out);
    }
    
    protected double returnPIDInput() {
        return this.drivetrain.getPitchGyroDegrees();
    }
}
