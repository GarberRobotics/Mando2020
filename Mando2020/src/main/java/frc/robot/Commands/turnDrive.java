// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

/*

This command is the exact same as tankDrive, but instead of an inputting a
distance, you have to input a degree.

*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class turnDrive extends Command {
  
  double leftSpeed; // the speed for the left motors
  double rightSpeed; // the speed for the right motors
  double goalDegree; // goal degree is how much the robot needs to turn
  double currentDegree; // current degree is the robot's current rotation

  public turnDrive(double m_leftSpeed, double m_rightSpeed, double m_goalDegree) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_ds);
    // takes the given inputs and transfers them into the command
    leftSpeed = m_leftSpeed;
    rightSpeed = -m_rightSpeed;
    goalDegree = m_goalDegree;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // Robot.m_ds.frontLeft.getEncoder().setPosition(0);
     // Robot.m_ds.frontLeft.getEncoder().setPosition(0);
      Robot.m_ds.resetMotorEncoders();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_ds.tankdrive(leftSpeed, rightSpeed);
    SmartDashboard.putNumber("Left Speed", leftSpeed);
    SmartDashboard.putNumber("Right Speed", rightSpeed);
    SmartDashboard.putNumber("Goal Degree", goalDegree);
    SmartDashboard.putNumber("Current Degree", currentDegree);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    // Checks the distance the robot has traveled.

    currentDegree = Robot.m_ds.gyro.getAngle();
    if (leftSpeed > 0 && rightSpeed < 0){
        if(goalDegree > 0){
            if(goalDegree <= currentDegree){
              Robot.m_ds.resetMotorEncoders();
              Robot.m_ds.gyro.reset();
              return true;
            }
    }
    if (leftSpeed < 0 && rightSpeed > 0){
        if(goalDegree >= currentDegree){  
            Robot.m_ds.resetMotorEncoders();
            Robot.m_ds.gyro.reset();
            return true;
          }
    }
    }
    return false;
}


  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_ds.resetMotorEncoders();
    Robot.m_ds.gyro.reset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
