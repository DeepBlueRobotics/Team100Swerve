/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;

public class HomeAbsolute extends Command {

  boolean frZero = false;
  boolean flZero = false;
  boolean blZero = false;
  boolean brZero = false;

  double margin;
  double speed;

  public HomeAbsolute() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.drivetrain.frederickTurn.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 20);
    Robot.drivetrain.fletcherTurn.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 20);
    Robot.drivetrain.blakeTurn.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 20);
    Robot.drivetrain.brianTurn.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 20);

    Robot.drivetrain.fletcherTurn.setSensorPhase(false);
    Robot.drivetrain.blakeTurn.setSensorPhase(false);
    Robot.drivetrain.frederickTurn.setSensorPhase(false);
    Robot.drivetrain.brianTurn.setSensorPhase(false);

    SmartDashboard.putNumber("HomeAbsolute margin", 1.0);
    SmartDashboard.putNumber("HomeAbsolute speed", 0.05);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Get the new margin and speed values.
    margin = SmartDashboard.getNumber("HomeAbsolute margin", 1.0);
    speed = SmartDashboard.getNumber("HomeAbsolute speed", 0.05);

    if (!frZero) Robot.drivetrain.frederickTurn.set(ControlMode.PercentOutput, speed);
    if (!flZero) Robot.drivetrain.fletcherTurn.set(ControlMode.PercentOutput, speed);
    if (!blZero) Robot.drivetrain.blakeTurn.set(ControlMode.PercentOutput, speed);
    if (!brZero) Robot.drivetrain.brianTurn.set(ControlMode.PercentOutput, speed);
  }

  // Checks if each motor controller has turned until it is close to some known setpoint.
  @Override
  protected boolean isFinished() {
    // Check if a motor controller is within a specified margin of the target value.
    flZero = (Math.abs(Robot.drivetrain.frederickTurn.getSelectedSensorPosition() - Constants.FR_TURN_ZERO) <= margin);
    flZero = (Math.abs(Robot.drivetrain.fletcherTurn.getSelectedSensorPosition() - Constants.FL_TURN_ZERO) <= margin);
    blZero = (Math.abs(Robot.drivetrain.blakeTurn.getSelectedSensorPosition() - Constants.BL_TURN_ZERO) <= margin);
    brZero = (Math.abs(Robot.drivetrain.brianTurn.getSelectedSensorPosition() - Constants.BR_TURN_ZERO) <= margin);

    // If a motor controller is in the right position, stop moving it.
    if (frZero) Robot.drivetrain.frederickTurn.set(ControlMode.PercentOutput, 0.0);
    if (flZero) Robot.drivetrain.fletcherTurn.set(ControlMode.PercentOutput, 0.0);
    if (blZero) Robot.drivetrain.blakeTurn.set(ControlMode.PercentOutput, 0.0);
    if (brZero) Robot.drivetrain.brianTurn.set(ControlMode.PercentOutput, 0.0);

    return flZero && frZero && blZero && brZero;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.frederickTurn.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    Robot.drivetrain.fletcherTurn.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    Robot.drivetrain.blakeTurn.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    Robot.drivetrain.brianTurn.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    
    Robot.drivetrain.fletcherTurn.setSensorPhase(true);
    Robot.drivetrain.blakeTurn.setSensorPhase(true);
    Robot.drivetrain.frederickTurn.setSensorPhase(true);
    Robot.drivetrain.brianTurn.setSensorPhase(true);

    Robot.drivetrain.fletcherTurn.setSelectedSensorPosition(0);
    Robot.drivetrain.blakeTurn.setSelectedSensorPosition(0);
    Robot.drivetrain.frederickTurn.setSelectedSensorPosition(0);
    Robot.drivetrain.brianTurn.setSelectedSensorPosition(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
