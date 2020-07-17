/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.InstantCommand;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;

public class HomeAbsolute extends InstantCommand {
  double flQuadPos;
  double frQuadPos;
  double blQuadPos;
  double brQuadPos;

  public HomeAbsolute() {
    // Use requires() here to declare subsystem dependencies
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

    // Change the current quadrature encoder position to the difference between the zeroed position and the current position, as measured by the analog encoder.
    // Difference is in analog encoder degrees which must be converted to quadrature encoder ticks.
    // Max value of the analog encoder is 1023, min value is 0.
    flQuadPos = (Math.abs(Constants.FL_GEAR_RATIO) / 1024.0) * (Robot.drivetrain.fletcherTurn.getSensorCollection().getAnalogIn() - Constants.FL_TURN_ZERO);
    frQuadPos = (Math.abs(Constants.FR_GEAR_RATIO) / 1024.0) * (Robot.drivetrain.frederickTurn.getSensorCollection().getAnalogIn() - Constants.FR_TURN_ZERO);
    blQuadPos = (Math.abs(Constants.BL_GEAR_RATIO) / 1024.0) * (Robot.drivetrain.blakeTurn.getSensorCollection().getAnalogIn() - Constants.BL_TURN_ZERO);
    brQuadPos = (Math.abs(Constants.BR_GEAR_RATIO) / 1024.0) * (Robot.drivetrain.brianTurn.getSensorCollection().getAnalogIn() - Constants.BR_TURN_ZERO);
    
    SmartDashboard.putNumber("Expected FL QuadPos", flQuadPos);
    SmartDashboard.putNumber("Expected FR QuadPos", frQuadPos);
    SmartDashboard.putNumber("Expected BL QuadPos", blQuadPos);
    SmartDashboard.putNumber("Expected BR QuadPos", brQuadPos);
    System.out.println("Expected FL QuadPos: " + flQuadPos);
    System.out.println("Expected FR QuadPos: " + frQuadPos);
    System.out.println("Expected BL QuadPos: " + blQuadPos);
    System.out.println("Expected BR QuadPos: " + brQuadPos);

    ErrorCode e;
    e = Robot.drivetrain.fletcherTurn.setSelectedSensorPosition((int) flQuadPos);
    if (e != ErrorCode.OK) System.out.println("Received error code #" + e.value + " when setting selected sensor position for FL.");
    e = Robot.drivetrain.frederickTurn.setSelectedSensorPosition((int) frQuadPos);
    if (e != ErrorCode.OK) System.out.println("Received error code #" + e.value + " when setting selected sensor position for FR.");
    e = Robot.drivetrain.blakeTurn.setSelectedSensorPosition((int) blQuadPos);
    if (e != ErrorCode.OK) System.out.println("Received error code #" + e.value + " when setting selected sensor position for BL.");
    e = Robot.drivetrain.brianTurn.setSelectedSensorPosition((int) brQuadPos);
    if (e != ErrorCode.OK) System.out.println("Received error code #" + e.value + " when setting selected sensor position for BR.");
  }
}
