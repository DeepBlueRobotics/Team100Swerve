/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

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
  double angle;

  public HomeAbsolute() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.drivetrain);
  }

  // Copy of the setAngle method defined in CanTalonSwerveEnclosure to avoid creating excess swerve enclosures.
  private void setAngle(WPI_TalonSRX steerMotor, double angle, double gearRatio) {
    steerMotor.set(ControlMode.Position,  angle * gearRatio);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // The quadrature encoders are for turning the steer motor.
    // The analog encoders are for checking if the motors are in the right position.
    Robot.drivetrain.fletcherTurn.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    Robot.drivetrain.frederickTurn.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    Robot.drivetrain.blakeTurn.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    Robot.drivetrain.brianTurn.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

    Robot.drivetrain.fletcherTurn.setSensorPhase(true);
    Robot.drivetrain.blakeTurn.setSensorPhase(true);
    Robot.drivetrain.frederickTurn.setSensorPhase(true);
    Robot.drivetrain.brianTurn.setSensorPhase(true);

    SmartDashboard.putNumber("HomeAbsolute margin", 1.0);
    SmartDashboard.putNumber("HomeAbsolute speed", 0.05);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Get the new margin and speed values.
    margin = SmartDashboard.getNumber("HomeAbsolute margin", 1.0);
    angle = SmartDashboard.getNumber("HomeAbsolute angle", 0.05);

    // Use copySign to make sure that the motor controller steers in the direction of the setpoint
    if (!frZero) {
      setAngle(Robot.drivetrain.frederickTurn, 
               Math.copySign(angle, Constants.FR_TURN_ZERO - Robot.drivetrain.frederickTurn.getSelectedSensorPosition()),
               Constants.FR_GEAR_RATIO);
    }
    if (!flZero)  {
      setAngle(Robot.drivetrain.fletcherTurn,
               Math.copySign(angle, Constants.FL_TURN_ZERO - Robot.drivetrain.fletcherTurn.getSelectedSensorPosition()),
               Constants.FL_GEAR_RATIO);
    }
    if (!blZero) {
      setAngle(Robot.drivetrain.blakeTurn,
               Math.copySign(angle, Constants.BL_TURN_ZERO - Robot.drivetrain.blakeTurn.getSelectedSensorPosition()),
               Constants.BL_GEAR_RATIO);
    }
    if (!brZero) {
      setAngle(Robot.drivetrain.brianTurn,
               Math.copySign(angle, Constants.BR_TURN_ZERO - Robot.drivetrain.brianTurn.getSelectedSensorPosition()),
               Constants.BR_GEAR_RATIO);
    }
  }

  // Checks if each motor controller has turned until it is close to some known setpoint.
  @Override
  protected boolean isFinished() {
    // Check if a motor controller is within a specified margin of the target value.
    flZero = (Math.abs(Robot.drivetrain.frederickTurn.getSensorCollection().getAnalogIn() - Constants.FR_TURN_ZERO) <= margin);
    flZero = (Math.abs(Robot.drivetrain.fletcherTurn.getSensorCollection().getAnalogIn() - Constants.FL_TURN_ZERO) <= margin);
    blZero = (Math.abs(Robot.drivetrain.blakeTurn.getSensorCollection().getAnalogIn() - Constants.BL_TURN_ZERO) <= margin);
    brZero = (Math.abs(Robot.drivetrain.brianTurn.getSensorCollection().getAnalogIn() - Constants.BR_TURN_ZERO) <= margin);

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
    // Zero the quadrature encoders.
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
