/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    public static Drivetrain drivetrain = new Drivetrain();
    public static OI oi;
    public static AHRS ahrs;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        oi = new OI();
        ahrs = new AHRS(SerialPort.Port.kUSB);
    }

    /**
     * This function is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want ran during disabled,
     * autonomous, teleoperated and test.
     * 
     * <p>This runs after the mode specific periodic functions, but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        updateDashboard();
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        /*
        * String autoSelected = SmartDashboard.getString("Auto Selector",
        * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
        * = new MyAutoCommand(); break; case "Default Auto": default:
        * autonomousCommand = new ExampleCommand(); break; }
        */
        // schedule the autonomous command (example)
    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }

    private void updateDashboard() {
        SmartDashboard.putNumber("Forward-Left Turn Analog Encoder", drivetrain.fletcherTurn.getSensorCollection().getAnalogIn());
        SmartDashboard.putNumber("Forward-Right Turn Analog Encoder", drivetrain.frederickTurn.getSensorCollection().getAnalogIn());
        SmartDashboard.putNumber("Backward-Left Turn Analog Encoder", drivetrain.blakeTurn.getSensorCollection().getAnalogIn());
        SmartDashboard.putNumber("Backward-Right Turn Analog Encoder", drivetrain.brianTurn.getSensorCollection().getAnalogIn());

        SmartDashboard.putNumber("Forward-Left Turn Quadrature Encoder", drivetrain.fletcherTurn.getSensorCollection().getQuadraturePosition());
        SmartDashboard.putNumber("Forward-Right Turn Quadrature Encoder", drivetrain.frederickTurn.getSensorCollection().getQuadraturePosition());
        SmartDashboard.putNumber("Backward-Left Turn Quadrature Encoder", drivetrain.blakeTurn.getSensorCollection().getQuadraturePosition());
        SmartDashboard.putNumber("Backward-Right Turn Quadrature Encoder", drivetrain.brianTurn.getSensorCollection().getQuadraturePosition());
    
        SmartDashboard.putNumber("Forward-Left Turn Current Sensor Value", drivetrain.fletcherTurn.getSelectedSensorPosition());
        SmartDashboard.putNumber("Forward-Right Turn Current Sensor Value", drivetrain.frederickTurn.getSelectedSensorPosition());
        SmartDashboard.putNumber("Backward-Left Turn Current Sensor Value", drivetrain.blakeTurn.getSelectedSensorPosition());
        SmartDashboard.putNumber("Backward-Right Turn Current Sensor Value", drivetrain.brianTurn.getSelectedSensorPosition());

        SmartDashboard.putString("Forward-Left Turn Control Mode", drivetrain.fletcherTurn.getControlMode().toString());
        SmartDashboard.putString("Forward-Right Turn Control Mode", drivetrain.frederickTurn.getControlMode().toString());
        SmartDashboard.putString("Backward-Left Turn Control Mode", drivetrain.blakeTurn.getControlMode().toString());
        SmartDashboard.putString("Backward-Right Turn Control Mode", drivetrain.brianTurn.getControlMode().toString());

        SmartDashboard.putNumber("Forward-Left Turn Applied Voltage", drivetrain.fletcherTurn.getMotorOutputVoltage());
        SmartDashboard.putNumber("Forward-Right Turn Applied Voltage", drivetrain.frederickTurn.getMotorOutputVoltage());
        SmartDashboard.putNumber("Backward-Left Turn Applied Voltage", drivetrain.blakeTurn.getMotorOutputVoltage());
        SmartDashboard.putNumber("Backward-Right Turn Applied Voltage", drivetrain.brianTurn.getMotorOutputVoltage());

        SmartDashboard.putNumber("Forward-Left Turn Error", drivetrain.fletcherTurn.getClosedLoopError());
        SmartDashboard.putNumber("Forward-Right Turn Error", drivetrain.frederickTurn.getClosedLoopError());
        SmartDashboard.putNumber("Backward-Left Turn Error", drivetrain.blakeTurn.getClosedLoopError());
        SmartDashboard.putNumber("Backward-Right Turn Error", drivetrain.brianTurn.getClosedLoopError());
    }
}
