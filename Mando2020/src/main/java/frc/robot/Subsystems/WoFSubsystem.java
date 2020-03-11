/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class WoFSubsystem extends Subsystem {
  

  // motor and solenoids
  public WPI_TalonSRX WoFmotor = new WPI_TalonSRX(41);

  public DoubleSolenoid WoFSolenoid = new DoubleSolenoid(4,0);


  
  // controls the wheel of fortune wheel's pneumatics and the wheel itself
  public void teleopWheelOfFortune() {
    
    // switches the right stick to the WoF controls and extends the WoF 
    if(Robot.stick.getPOV() == 270){
      WheelofFortunePneumatics(true);
      wheelOfFortune(Robot.m_Xbox.rightStick.getY());
      Robot.m_cs.hookOrWheel = false;
    }else{
      WheelofFortunePneumatics(false);
      wheelOfFortune(0);
      Robot.m_cs.hookOrWheel = true;
    }

  }
  
  public void wheelOfFortune(double power){
    
    WoFmotor.set(power);
    
  }

  public void WheelofFortunePneumatics(boolean power){
    // if (power){
    //   one.set(Value.kReverse);
    //   two.set(Value.kForward);
    // }
    // else{
    //   one.set(Value.kForward);
    //   two.set(Value.kReverse);
    // }

    //Competition bot
    // move intake in and out
    if(power){
      WoFSolenoid.set(Value.kForward);
    }else{
      WoFSolenoid.set(Value.kReverse);
    }

    
  }






  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
