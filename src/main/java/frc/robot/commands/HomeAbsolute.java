/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.first.wpilibj.command.InstantCommand;
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

    Robot.drivetrain.fletcherTurn.setSensorPhase(true);
    Robot.drivetrain.blakeTurn.setSensorPhase(true);
    Robot.drivetrain.frederickTurn.setSensorPhase(true);
    Robot.drivetrain.brianTurn.setSensorPhase(true);

    // Change the current quadrature encoder position to the difference between the zeroed position and the current position, as measured by the analog encoder.
    // Difference is in analog encoder degrees which must be converted to quadrature encoder ticks.
    // Max value of the analog encoder is 1023, min value is 0.
    flQuadPos = (Constants.FL_GEAR_RATIO / 1024.0) * (Constants.FL_TURN_ZERO - Robot.drivetrain.fletcherTurn.getSensorCollection().getAnalogIn());
    frQuadPos = (Constants.FR_GEAR_RATIO / 1024.0) * (Constants.FR_TURN_ZERO - Robot.drivetrain.frederickTurn.getSensorCollection().getAnalogIn());
    blQuadPos = (Constants.BL_GEAR_RATIO / 1024.0) * (Constants.BL_TURN_ZERO - Robot.drivetrain.blakeTurn.getSensorCollection().getAnalogIn());
    brQuadPos = (Constants.BR_GEAR_RATIO / 1024.0) * (Constants.BR_TURN_ZERO - Robot.drivetrain.brianTurn.getSensorCollection().getAnalogIn());
    
    Robot.drivetrain.fletcherTurn.setSelectedSensorPosition((int) flQuadPos);
    Robot.drivetrain.frederickTurn.setSelectedSensorPosition((int) frQuadPos);
    Robot.drivetrain.blakeTurn.setSelectedSensorPosition((int) blQuadPos);
    Robot.drivetrain.brianTurn.setSelectedSensorPosition((int) brQuadPos);
  }
}
