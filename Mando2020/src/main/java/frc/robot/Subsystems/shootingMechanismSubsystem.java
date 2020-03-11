/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * Add your docs here.
 */
public class shootingMechanismSubsystem extends Subsystem {


  //Variables for the teleop code

  public boolean intakePower = false;    // decides if the intake is on or off
  public boolean intakeSolenoidExtend = false; // moves the solenoids for the intake
  
  public double shooterPower = 0.5;      // how much power the shooter gets

  public boolean freeTheBallTimerReset = true;  // resets the timer on the freeTheBallCommand
  public double timer = Timer.getFPGATimestamp();  // Let's you compare current time to other times
  public boolean turnOnFTB = false;  // When true the shooter will activate the FreeTheBallCommand
  public double startTime = 0;  // the time when freeTheBall was initiated

  public boolean shootClose = false;
  // public boolean shootFar = false;

  public boolean solenoidExtend = false;
  public boolean timerFor5Reset = false;
  public double timerfor5;

  public boolean timerFor1Reset = false;
  public double timerFor1;

  public boolean timerForShooterReset = false;
  public double timerForShooter;

  // public boolean shootClose = false;     // activates the code that makes the shooter shoot from next to the goal
  // public boolean shootFar = false;       // activates the code that makes the shooter shoot from a robot sized distance from the goal

  

  
  // shooter and conveyor
  public WPI_TalonSRX shooterLeft  = new WPI_TalonSRX(55);
  public WPI_TalonSRX shooterRight = new WPI_TalonSRX(48);
  public WPI_TalonSRX conveyorLeft = new WPI_TalonSRX(53);
  public WPI_TalonSRX conveyorRight = new WPI_TalonSRX(54);

  // intake
  public WPI_TalonSRX intakeMotor = new WPI_TalonSRX(42);

  // controlgroups
  public SpeedControllerGroup conveyorBeltControllerGroup = new SpeedControllerGroup(conveyorLeft, conveyorRight);

  // solenoids
  public DoubleSolenoid intakeSolenoid = new DoubleSolenoid(6,2);
  public DoubleSolenoid conveyorSolenoid = new DoubleSolenoid(5,1);
  


  //  intakePower
  //  inatkeSolenoidExtend


  // contains the code necesary for the drive to control the intake during teleop
  public void teleopIntake() {

    // whenever y is pressed the intake's power will switch on/off
    if(Robot.stick.getYButtonPressed()){
      intakePower = !intakePower;
    }

    // activates the spinner code
    
    if(!shootClose){
      intakeSpinner(intakePower);
      timerForShooterReset = true;

    }else{
      
      if(timerForShooterReset){
        timerForShooter = timer + 0.4;
        timerForShooterReset = false;
      }

      if(timer < timerForShooter){
        intakeMotor.set(ControlMode.PercentOutput,-0.25);
      }


    }

    // intake solenoid extend out of robot
    if(Robot.stick.getTriggerAxis(Hand.kLeft) > 0.4){
      intakeSolenoidExtend = true;
    }

    // intake solenoid comes back in
    if(Robot.stick.getTriggerAxis(Hand.kRight) > 0.4){
      intakeSolenoidExtend = false;
    }

    // if(Robot.m_Xbox.x.get()){
    //   conveyorSolenoid.set(Value.kForward);
    // }else{
    //   conveyorSolenoid.set(Value.kReverse);
    // }
    
    // activates the code for intake pnumatics
    intakePneumatics(intakeSolenoidExtend);

  }



  public void intakeSpinner(boolean power){
    
    double speed = 0;
    
    
    if(power){
      speed = 0.8;
    }

    conveyor(power);

    intakeMotor.set(ControlMode.PercentOutput,speed);

  }


  //shooter gets to speed.  Conveyor solinoid.  Conveyor +power & -intake.  quarter second later, intake stops.  Half a second before intake goes in.
  // public void freeTheBall(boolean timerReset, boolean activate){

  //   if(activate){
      
  //     double timeCheck1 = timer - 0.25;
  //     double timeCheck2 = timer - 0.75;

  //     if(timerReset == true){
  //       startTime = timer;
  //       freeTheBallTimerReset = false;
  //     }
    
  //     conveyorSolenoid.set(Value.kReverse);

  //     conveyorLeft.set(-0.6);
  //     conveyorRight.set(0.6);
      
  //     if(timeCheck1 < startTime){
  //       intakeMotor.set(ControlMode.PercentOutput, -0.8);
  //     }else if(timeCheck2 < startTime){
  //       intakeMotor.set(ControlMode.PercentOutput, 0);
  //     }else{
  //       intakeMotor.set(ControlMode.PercentOutput, 0.8);
  //     }
  //   }else{
  //     conveyorBeltControllerGroup.set(0);
  //     intakeMotor.set(ControlMode.PercentOutput, 0);
  //     conveyorSolenoid.set(Value.kForward);
  //   }
  // }

  
  public void intakePneumatics(boolean power){
    if(power){
      intakeSolenoid.set(Value.kForward);
    }else if(!power){
      intakeSolenoid.set(Value.kReverse);
    }
  }

  

  
  // This is all of that crazy code needed to shoot a ball from the robot.  It's here
  public void teleopShooter() {
    
    shootClose = false;
    // shootFar = false;

    // when A is held the robot will shoot balls from an intake distance of the goal
    if(Robot.m_Xbox.a.get()){
      shootClose = true;
    }

    // when B is held the robot will launch balls from a robot distance of the goal
    // if(Robot.m_Xbox.b.get()){
    //   shootFar = true;
    // }

    shooterAlgorithm(shootClose);
  }
  










    
  // The shooting specifics are in here. AUTONOMOUS SHOULD CALL THIS!
  public void shooterAlgorithm(boolean shootClose){

    
    double vel = Math.abs(shooterRight.getSelectedSensorVelocity() / 1024 * 600 / 4);

    timer = Timer.getFPGATimestamp();

    // if (turnOnFTB){
    //   freeTheBall(freeTheBallTimerReset, true);
    // }
    
    SmartDashboard.putBoolean("Close shot variable", shootClose);

    
    shooter(shooterPower);


    
      // turn off shooter
      if(shootClose == false){
        // intakeSpinner(false);
        // conveyor(false);
        solenoidExtend = false;
        conveyorSolenoid.set(Value.kReverse);
        shooterPower = 0;
      //   freeTheBallTimerReset = true;
      }

      
      if(solenoidExtend){
        conveyorSolenoid.set(Value.kForward);
        shooterIntake(true);
        shooterConveyor(true);
      }else{
        conveyorSolenoid.set(Value.kReverse);
        shooterIntake(false);
        shooterConveyor(false);
      }



    
    // Will correct the speed the shooting wheel spins until ti will launch a ball from intake distance

    if(shootClose){

      if(vel < 13000){
        shooterPower = shooterPower + 0.001;
      }else if(vel > 13200){
        shooterPower = shooterPower - 0.001;
      }

      if(vel < 11700){
        shooterPower = shooterPower + 0.01;
      }else if(vel > 14000){
        shooterPower = shooterPower - 0.01;
      }

      if(vel > 13000 && vel < 13200){
    
        // intakeSpinner(true);
        // conveyor(true);
        solenoidExtend = true;
      }

    }



    // if(shootFar){
  
    //     if(vel < 15600){
    //       shooterPower = shooterPower + 0.001;
    //     }else if(vel > 16000){
    //       shooterPower = shooterPower - 0.001;
    //     }
  
    //     if(vel < 14800){
    //       shooterPower = shooterPower + 0.01;
    //     }else if(vel > 16800){
    //       shooterPower = shooterPower - 0.01;
    //     }


        // // Uses the conveyor to bring balls up to be shot WHEN the shooting wheel is spinning at the right speed
        // if(vel > 15500 && vel < 15800){

        //   // intakeSpinner(true);
        //   conveyor(true);
        //   solenoidExtend = true;

        //   // freeTheBall(freeTheBallTimerReset);
        //   // freeTheBallTimerReset = false;

        // }
        
      // }

      
  }


  // controls the shooter motors
  public void shooter(double speed){

    
    shooterLeft.set(ControlMode.PercentOutput, speed);
    shooterRight.set(ControlMode.PercentOutput, -speed);

    
  }    

  // The shooters version of the intake command
  public void shooterIntake(boolean active){

    if(active){
      
      if(timerFor5Reset){
        timerfor5 = timer + 1;
      }

      if(timer > timerfor5){
        intakeSpinner(true);
      }

      timerFor5Reset = false;

    }else if(!active){
      intakeSpinner(false);
      timerFor5Reset = true;
    }
  }

  public void shooterConveyor(boolean active){

    if(active){
      
      if(timerFor1Reset){
        timerFor1 = timer + 0.2;
      }

      if(timer > timerFor1){
        conveyor(true);
      }

      timerFor1Reset = false;

    }else if(!active){
      conveyor(false);
      timerFor1Reset = true;
    }

  }


  // controls the conveyor loader motors
  public void conveyor(Boolean power){
    if(power){
      conveyorLeft.set(-0.30);
      conveyorRight.set(0.30);
    }else{
      conveyorLeft.set(0);
      conveyorRight.set(0);
    }
    
  }

  //18000rpm 
  //starting rate = ?
  //Correction rate +/- = ?

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
