/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.Subsystems.driveSubsystem;



public class DriveStraightCommand extends Command {

  private double leftSpeed;
  private double rightSpeed;
  private double goalDistance;
  private double initEncoderValue;

  

  /**
   * 
   * @param m_leftSpeed controls the left motor speeds
   * @param m_rightSpeed controls the right motor speeds
   * @param m_goalDistance how far you want the robot to move in feet
   */

  public DriveStraightCommand(double m_leftSpeed, double m_rightSpeed, double m_goalDistance) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_ds);

    Robot.m_ds.resetMotorEncoders();

    leftSpeed = m_leftSpeed;
    rightSpeed = m_rightSpeed;
    goalDistance = m_goalDistance;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

    initEncoderValue = Robot.m_ds.frontLeft.getSelectedSensorPosition();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    
    SmartDashboard.putNumber("Distance", Robot.m_ds.frontLeft.getSelectedSensorPosition());

    Robot.m_ds.tankdrive(leftSpeed, rightSpeed);

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {


    double distance = (Robot.m_ds.frontLeft.getSelectedSensorPosition() - initEncoderValue) / 11258 ;


    if(goalDistance > 0){
      if(distance >= goalDistance){

        
        distance = 0;

        return true;
      }
    }else if(distance < goalDistance){

      
      distance = 0;

      return true;
    }

    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {


  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
