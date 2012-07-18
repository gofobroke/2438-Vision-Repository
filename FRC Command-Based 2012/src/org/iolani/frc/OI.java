
package org.iolani.frc;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.DigitalIOButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.iolani.frc.commands.*;
import org.iolani.frc.subsystems.*;
import org.iolani.frc.util.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    
    // standard operator joysticks //
    private final Joystick _lStick = new Joystick(1);
    private final Joystick _rStick = new Joystick(2);
    private final NESStick _nesStick = new NESStick(3);
    
    public Joystick getLeftStick() {
        return _lStick;
    }
    
    public Joystick getRightStick() {
        return _rStick;
    }
    
    public NESStick getNESStick() {
        return _nesStick;
    }
    
    // suck balls //
    final Button _suckButton    = new JoystickButton(_lStick, 1);
    //final Button _suckButton    = new JoystickButtonToggle(_lStick, 1);
    final Button _blowButton    = new JoystickButton(_lStick, 3);
    final Button _feedButton    = new JoystickButton(_lStick, 4);
    final Button _bridgeButton  = new JoystickButton(_lStick, 5);
    final Button _jamButton     = new JoystickButton(_lStick, 7);
    final Button _balanceButton = new JoystickButton(_lStick, 2);
    
    final Button _loadButton = new JoystickButton(_rStick, 3);
    final Button _cancelLoadButton = new JoystickButton(_rStick, 4);
    final Button _shootButton = new JoystickButton(_rStick, 1);
    final Button _reloadButton = new JoystickButton(_rStick, 5);
    
    final Button _power1Button    = new JoystickButton(_rStick, 6);
    final Button _power2Button    = new JoystickButton(_rStick, 7);
    final Button _power3Button    = new JoystickButton(_rStick, 10);
    final Button _power4Button    = new JoystickButton(_rStick, 11);
    final Button _powerAutoButton = new JoystickButton(_rStick, 9);
    
    final Button _armTestButton = new JoystickButton(_rStick, 8);
    final Button _boxTestButton = new JoystickButton(_rStick, 9);
    
    private PowerScaler _driveScaler;
    
    public static final Thrower.ThrowParameters FENDER_THROW = new Thrower.ThrowParameters(0.75, 10.0);
    public static final Thrower.ThrowParameters KEY_THROW    = new Thrower.ThrowParameters(0.78, 26.5);
    public static final Thrower.ThrowParameters KEY2_THROW   = new Thrower.ThrowParameters(0.85, 32.0);
    public static final Thrower.ThrowParameters FIELD_THROW  = new Thrower.ThrowParameters(1.0, 20.0);
    
    public OI() {
        //_suckButton.whenActive(new SuckBalls());
        _suckButton.whileHeld(new SuckBalls());
        
        _blowButton.whileHeld(new BlowBalls());
        _feedButton.whileHeld(new SetBoxIntake(Box.IntakeMode.kSuck));
        _jamButton.whileHeld(new ClearBoxJam());
        _bridgeButton.whenPressed(new AutoAlignBridge());
        _balanceButton.whileHeld(new AutoBalanceBridge());
        
        _loadButton.whenPressed(new LoadOneBall());
        _shootButton.whenPressed(new ThrowBall());
        _cancelLoadButton.whenPressed(new DisableBox());
        _reloadButton.whenPressed(new ReloadBall());
        
        _power1Button.whenPressed(new SetThrowParameters(FENDER_THROW));
        _power2Button.whenPressed(new SetThrowParameters(KEY_THROW));
        _power3Button.whenPressed(new SetThrowParameters(KEY2_THROW));
        _power4Button.whenPressed(new SetThrowParameters(FIELD_THROW));
        _powerAutoButton.whenPressed(new SetThrowParameters());
        
        _armTestButton.whileHeld(new TestThrower());
        _boxTestButton.whileHeld(new TestBox());
        
        // set up drive scalar //
        _driveScaler = new PowerScaler(new PowerScaler.PowerPoint[] {
                new PowerScaler.PowerPoint(0.05, 0.1),
                new PowerScaler.PowerPoint(0.8, 0.5),
                new PowerScaler.PowerPoint(0.95, 1.0)
            });
    }
    
    public PowerScaler getDriveScaler() {
        return _driveScaler;
    }
}

