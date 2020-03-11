/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class ClimbingSubsystem extends Subsystem {
  


  public boolean hookOrWheel = true;     // true is the hook and false is the WoF
  public boolean endClimb = false;       // SHOULD ONLY BE DECLARED TRUE AT THE END OF THE MATCH.  When made true, the robot will pull itself up


  
  // motor and solenoids
  public WPI_TalonSRX hookMotor = new WPI_TalonSRX(44);

  public WPI_TalonSRX leftClimb = new WPI_TalonSRX(40);
  public WPI_TalonSRX rightClimb = new WPI_TalonSRX(56);

  public SpeedControllerGroup climbers = new SpeedControllerGroup(leftClimb, rightClimb);


  private double hold = -0.11;
  private double fall = 0.05;
  
  // will use rightStick's value to extend/de-extend the claw
  public void teleopClaw() {
  
    // makes sure driver is not using the WoF and is pushing a direction on the right stick.  
    // if both are true then the right stick will control the claw raise motor
    if(hookOrWheel && Robot.m_Xbox.rightStick.getY() != 0){
      hookControl(-Robot.m_Xbox.rightStick.getY());
    }else{
      hookControl(0);
    }

  }
public void hookControl(double speed){

    if(speed < -0.2){
      hookMotor.set(speed * 0.5);
      //hookMotor.set(speed);
      hold = -0.15;
    }else if(speed > 0.2){
      if(hookMotor.getSelectedSensorVelocity() < 400 * 5) {
        fall = fall + 0.001;
      }else if (hookMotor.getSelectedSensorVelocity() > 600 * 4) {
        fall = fall - 0.00001;
      }
      hookMotor.set(fall);
    }else if (hookMotor.getSelectedSensorVelocity() < -1000 * 2) {
      hold = hold - 0.005;
      hookMotor.set(hold);
    }else if(hookMotor.getSelectedSensorVelocity() > 1000 * 2){
      hold = hold + 0.005;
      hookMotor.set(hold);
    }
    
  }




  // pulls the strings attatched to the claw and lifts the robot
  public void teleopClimb() {

    // while X is pressed the robot will be puled up to the hook 
    if(Robot.m_Xbox.x.get()){
      endClimb = true;
    }else{
      endClimb = false;
    }

    endClimb(endClimb);
  }
  
  
  public void endClimb(boolean power){
    if(power){
      climbers.set(1);
    }else{
      climbers.set(0);
    }
  }

  public ClimbingSubsystem (){
    leftClimb.setNeutralMode(NeutralMode.Brake);
    rightClimb.setNeutralMode(NeutralMode.Brake);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
