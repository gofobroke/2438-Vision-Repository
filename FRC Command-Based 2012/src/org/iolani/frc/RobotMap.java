package org.iolani.frc;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // drive system //
    public static final int driveLeftFrontJaguarID  = 9;
    public static final int driveLeftRearJaguarID   = 8;
    public static final int driveRightFrontJaguarID = 5;
    public static final int driveRightRearJaguarID  = 7;
    public static final int drivePitchGyroChannel   = 1;
    public static final int driveYawGyroChannel     = 2;
    // drive system (junkbot) //
    public static final int driveLeftFrontVictorPWM  = 1;
    public static final int driveLeftRearVictorPWM   = 2;
    public static final int driveRightFrontVictorPWM = 3;
    public static final int driveRightRearVictorPWM  = 4;
    
    // box //
    public static final int boxIntakeJaguarID     = 25;
    public static final int boxExitRollerJaguarID = 26;
    public static final int boxConveyorPWM        = 5;
    public static final int boxLiftUpSolenoid     = 1;
    public static final int boxLiftDownSolenoid   = 2;
    public static final int boxExitSensor         = 5;
    
    // thrower //
    public static final int throwerJaguarID1       = 23;
    public static final int throwerJaguarID2       = 24;
    public static final int throwerEncoderChannelA = 2;
    public static final int throwerEncoderChannelB = 3;
    public static final int throwerEncoderIndex    = 4;
    
    // loader //
    public static final int loaderFlipSolenoid   = 3;
    public static final int loaderUnflipSolenoid = 4;
    public static final int loaderBallSwitch     = 6;
    public static final int loaderHomeSwitch     = 7;
    
    // pneumatics //
    public static final int pneumaticsPressureSwitch  = 1;
    public static final int pneumaticsCompressorRelay = 1;

    // camera //
    public static final String cameraIPAddress   = "10.24.38.11";
    public static final int cameraRingLightRelay = 2;
    public static final int cameraPitchServo     = 6;
    public static final int cameraYawServo       = 7;
    
    // decorations //
    public static final int decorationsRelay = 3;
    
}
