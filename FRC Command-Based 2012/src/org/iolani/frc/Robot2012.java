/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.iolani.frc;


import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import org.iolani.frc.commands.CommandBase;
import org.iolani.frc.commands.AutoThrowTwoBalls;
import org.iolani.frc.commands.RobotStartup;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot2012 extends IterativeRobot {

    Command _autoCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        // instantiate the command used for the autonomous period
        _autoCommand = new AutoThrowTwoBalls();
        // Initialize all subsystems
        CommandBase.init();
    }

    public void autonomousInit() {
        if(CommandBase.loader.hasBall()) {
            _autoCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if(_autoCommand.isRunning()) {
            _autoCommand.cancel();
        }
        // run the startup command //
        new RobotStartup().start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void disabledInit() {
        // start the camera //
        //CommandBase.getCamera().startCamera();
    }
    
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }
    
    //public void disabledContinuous() {
    //}
}
