/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class shootBallCommand extends Command {

public double ballAmount;
public boolean timerActive = false;
public double shootStartTime = -100;

  public shootBallCommand(double m_ballAmount) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);

    ballAmount = m_ballAmount;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    
    Robot.m_ds.resetMotorEncoders();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    

    Robot.m_sms.shooterAlgorithm(true);

    

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {



    double conveyorSpeed = Robot.m_sms.conveyorRight.getSelectedSensorPosition();

    if(conveyorSpeed != 0){
      
      if(!timerActive && shootStartTime < 0){

        shootStartTime = 5 + Timer.getFPGATimestamp();
        timerActive = true;

      }else if(shootStartTime <  Timer.getFPGATimestamp()){

        Robot.m_sms.conveyorRight.getSelectedSensorPosition(0);

        // yes, this is necessary
        Robot.m_sms.shooterAlgorithm(false);
        Robot.m_sms.shooterAlgorithm(false);
        return true;

      }


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
