/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.subsystems;


import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.iolani.frc.RobotMap;
import org.iolani.frc.commands.DisableBox;
import org.iolani.frc.util.Utility;

/**
 *
 * @author Michael
 */
public class Box extends Subsystem {
    
    private CANJaguar _intake;
    private CANJaguar _exitRoller;
    private PWM       _conveyor     = new PWM(RobotMap.boxConveyorPWM);
    private DoubleSolenoid _boxLift = new DoubleSolenoid(
            RobotMap.boxLiftUpSolenoid, 
            RobotMap.boxLiftDownSolenoid
        );
    private DigitalInput _ballSense = new DigitalInput(RobotMap.boxExitSensor);
    private boolean _deployed;
    private IntakeMode     _intakeMode;
    private ExitRollerMode _exitMode;
    private ConveyorMode   _conveyorMode;
    
    public void init() {
        _intake     = Utility.createJaguar("intake",      RobotMap.boxIntakeJaguarID);
        _exitRoller = Utility.createJaguar("exit roller", RobotMap.boxExitRollerJaguarID);
    }
    
    public void setDeployed(boolean state) {
        if(state) {
            _boxLift.set(DoubleSolenoid.Value.kForward);
        } else {
            _boxLift.set(DoubleSolenoid.Value.kReverse);
        }
        _deployed = state;
    }
    
    public boolean isDeployed() {
        return _deployed;
    }
    
    public void setIntake(IntakeMode mode) {
        if(_intakeMode == mode) return;
        switch(mode.value) {
            case IntakeMode.kOff_val:
                Utility.setJaguar(_intake, 0.0);
                break;
            case IntakeMode.kSuck_val:
                Utility.setJaguar(_intake, -1.0);
                break;
            case IntakeMode.kBlow_val:
                Utility.setJaguar(_intake, 1.0);
                break;
        }
        _intakeMode = mode;
    }
    
    public IntakeMode getIntake() {
        return _intakeMode;
    }
    
    public void setExitRoller(ExitRollerMode mode) {
        if (_exitMode == mode) return;
        switch(mode.value) {
            case ExitRollerMode.kOff_val:
                Utility.setJaguar(_exitRoller, 0.0);
                break;
            case ExitRollerMode.kForward_val:
                Utility.setJaguar(_exitRoller, -1.0);
                break;
            case ExitRollerMode.kReverse_val:
                Utility.setJaguar(_exitRoller, 1.0);
                break;
        }
        _exitMode = mode;
    }
    
    public ExitRollerMode getExitRoller() {
        return _exitMode;
    }
    
    public boolean isBallAtExit() {
        return _ballSense.get();
    }
    
    public void setConveyor(ConveyorMode mode) {
        if(_conveyorMode == mode) return;
        switch(mode.value) {
            case ConveyorMode.kOff_val:
                _conveyor.setRaw(0);
                break;
            case ConveyorMode.kUp_val:
                _conveyor.setRaw(1);
                break;
            case ConveyorMode.kDown_val:
                _conveyor.setRaw(250);
                break;
        }
        _conveyorMode = mode;
    }
    
    public ConveyorMode getConveyor() {
        return _conveyorMode;
    }

    public void initDefaultCommand() {
        this.setDefaultCommand(new DisableBox());
    }
    
    public static final class IntakeMode {
        // int values //
        public static final int kOff_val  = 0;
        public static final int kSuck_val = 1;
        public static final int kBlow_val = 2;
        // singletons //
        public static final IntakeMode kOff  = new IntakeMode(kOff_val);
        public static final IntakeMode kSuck = new IntakeMode(kSuck_val);
        public static final IntakeMode kBlow = new IntakeMode(kBlow_val);
       
        public final int value;
        private IntakeMode(int val) {
            this.value = val;
        }
    }
    
    public static final class ExitRollerMode {
        // int values //
        public static final int kOff_val     = 0;
        public static final int kForward_val = 1;
        public static final int kReverse_val = 2;
        // singletons //
        public static final ExitRollerMode kOff     = new ExitRollerMode(kOff_val);
        public static final ExitRollerMode kForward = new ExitRollerMode(kForward_val);
        public static final ExitRollerMode kReverse = new ExitRollerMode(kReverse_val);
       
        public final int value;
        private ExitRollerMode(int val) {
            this.value = val;
        }
    }
    
    public static final class ConveyorMode {
        // int values //
        public static final int kOff_val  = 0;
        public static final int kUp_val   = 1;
        public static final int kDown_val = 2;
        // singletons //
        public static final ConveyorMode kOff  = new ConveyorMode(kOff_val);
        public static final ConveyorMode kUp   = new ConveyorMode(kUp_val);
        public static final ConveyorMode kDown = new ConveyorMode(kDown_val);
       
        public final int value;
        private ConveyorMode(int val) {
            this.value = val;
        }
    }
}
