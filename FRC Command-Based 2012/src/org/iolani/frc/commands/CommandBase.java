package org.iolani.frc.commands;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.iolani.frc.OI;
import org.iolani.frc.subsystems.*;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use CommandBase.exampleSubsystem
 * @author Author
 */
public abstract class CommandBase extends Command {

    public static OI oi;
    // Create a single static instance of all of your subsystems
    public static Box box = new Box();
    public static Camera camera = new Camera();
    public static Decorations decorations = new Decorations();
    public static Drivetrain drivetrain = new Drivetrain(false);
    public static Loader loader = new Loader();
    public static Pneumatics pneumatics = new Pneumatics();
    public static Thrower thrower = new Thrower();
    
    public static Preferences prefs = null;//Preferences.getInstance();
    
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
        oi = new OI();
        box.init();
        //camera.init();
        //decorations.init();
        drivetrain.init();
        loader.init();
        pneumatics.init();
        thrower.init();
        // Show what command your subsystem is running on the SmartDashboard
        //SmartDashboard.putData("SchedulerData", Scheduler.getInstance());
    }

    public static Camera getCamera() {
        return camera;
    }
    
    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }
}
