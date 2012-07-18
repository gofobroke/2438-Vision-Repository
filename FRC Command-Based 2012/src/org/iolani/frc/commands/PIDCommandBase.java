/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.iolani.frc.commands;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.PIDCommand;
import org.iolani.frc.OI;
import org.iolani.frc.subsystems.*;

/**
 *
 * @author wkd
 */
public abstract class PIDCommandBase extends PIDCommand {
    
    // copy static subsystem references from CommandBase //
    public static OI oi = CommandBase.oi;
    public static Box box = CommandBase.box;
    public static Camera camera = CommandBase.camera;
    public static Decorations decorations = CommandBase.decorations;
    public static Drivetrain drivetrain = CommandBase.drivetrain;
    public static Loader loader = CommandBase.loader;
    public static Pneumatics pneumatics = CommandBase.pneumatics;
    public static Thrower thrower = CommandBase.thrower;
    public static Preferences prefs = CommandBase.prefs;
    
    public PIDCommandBase(double p, double i, double d, double period) {
        super(p, i, d, period);
    }

    public PIDCommandBase(double p, double i, double d) {
        super(p, i, d);
    }

    public PIDCommandBase(String name, double p, double i, double d, double period) {
        super(name, p, i, d, period);
    }

    public PIDCommandBase(String name, double p, double i, double d) {
        super(name, p, i, d);
    }
}
