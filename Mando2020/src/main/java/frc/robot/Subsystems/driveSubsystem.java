/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.analog.adis16448.frc.ADIS16448_IMU;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Xbox;
import frc.robot.Robot;


public class driveSubsystem extends Subsystem {

  //Variables for the teleop code




  //Motor controllers and gyro


  // Test Board
  // public WPI_TalonSRX frontLeft = new WPI_TalonSRX(43);
  // public WPI_TalonSRX backLeft = new WPI_TalonSRX(44);
  // public WPI_TalonSRX frontRight = new WPI_TalonSRX(40);
  // public WPI_TalonSRX backRight = new WPI_TalonSRX(46);

  // Pratice bot

  // public CANSparkMax frontLeft = new CANSparkMax(10, MotorType.kBrushless);
  // public CANSparkMax backLeft = new CANSparkMax(20, MotorType.kBrushless);
  // public CANSparkMax frontRight = new CANSparkMax(1, MotorType.kBrushless);
  // public CANSparkMax backRight = new CANSparkMax(5, MotorType.kBrushless);

  // public WPI_TalonSRX wheelLeft = new WPI_TalonSRX(55);
  // public WPI_TalonSRX wheelRight = new WPI_TalonSRX(56);

 // Real Bot

 
    public boolean gearBoxPower = false;  // Moves the solinoid for the gearbox when changed
    public boolean Slowdown = false;  // Slows the gears for the chassis by half
    public boolean fullSpeed = false;
    public boolean Forward = true;  // When true the robot's front is the intake, when false the robot's front is the opposite

    public boolean motorModeReset = true;
 
 // Treads
    public WPI_TalonFX frontLeft = new WPI_TalonFX(27);
    public WPI_TalonFX backLeft = new WPI_TalonFX(28);

    public WPI_TalonFX frontRight = new WPI_TalonFX(31);
    public WPI_TalonFX backRight = new WPI_TalonFX(26);

  
    
    public SpeedControllerGroup leftSpeedControllerGroup = new SpeedControllerGroup(frontLeft, backLeft);
    public SpeedControllerGroup rightSpeedControllerGroup = new SpeedControllerGroup(frontRight, backRight);


    public DifferentialDrive drive = new DifferentialDrive(leftSpeedControllerGroup, rightSpeedControllerGroup);
 
    // public ADXRS450_Gyro gyro = new ADXRS450_Gyro();

    public ADIS16448_IMU gyro = new ADIS16448_IMU();

    

  // compressor
  public Compressor compressor = new Compressor(); 
  // public Solenoid pistonOne = new Solenoid(0);
  // public Solenoid pistonTwo = new Solenoid(1);
  // public Solenoid three = new Solenoid(2);
  // public Solenoid four = new Solenoid(3);
  //  public DoubleSolenoid one = new DoubleSolenoid(0,1);
  //  public DoubleSolenoid two = new DoubleSolenoid(2,3);

  public DoubleSolenoid gearBoxSolenoid = new DoubleSolenoid(7,3);




  // // makes the gearboxes shift gears and turns the wheels
  public void teleopDrive() {


    if(motorModeReset){
      frontLeft.setNeutralMode(NeutralMode.Brake);
      frontRight.setNeutralMode(NeutralMode.Brake);
      backLeft.setNeutralMode(NeutralMode.Brake);
      backRight.setNeutralMode(NeutralMode.Brake);
      
      motorModeReset = false;
    }

    if(Robot.m_Xbox.lb.get()){
      gearBoxPower = false;
    }else{
      gearBoxPower = true;
    }

    teleopGearboxes(gearBoxPower);
    
    
    arcadeDrive(Robot.m_Xbox.leftStick.getY(), Robot.m_Xbox.leftStick.getX());
  }


  // tankdrive
  public void tankdrive(double leftSpeed, double rightSpeed){
    
    drive.tankDrive(leftSpeed, rightSpeed);
    
  }

   public void arcadeDrive(double xSpeed, double zRotation){


    // direction is decided by this
    if(Robot.stick.getBackButtonPressed() == true){
      Forward = !Forward;
    }

    // if(Robot.stick.getPOV() == 180){
    //   Forward = false;
    // }

    if(Forward == false){
      xSpeed = -xSpeed;
      zRotation = -zRotation;
    }
 

    // half speed setup
    if(Robot.m_Xbox.b.get()){
      Slowdown = true;
    }else{
      Slowdown = false;
    }

    if(Robot.m_Xbox.rb.get()){
      fullSpeed = true;
    }else{
      fullSpeed = false;
    }

    if(Slowdown){

      xSpeed = xSpeed * 0.5;
      zRotation = zRotation * 0.5;

    }else if(!fullSpeed){

      xSpeed = xSpeed * 0.75;
      zRotation = zRotation * 0.75;

    }

    drive.arcadeDrive(xSpeed, zRotation);
     
  }



  // refreshes all chassis motors
  public void resetMotorEncoders(){

    frontLeft.setSelectedSensorPosition(0);
    backLeft.setSelectedSensorPosition(0);
    frontRight.setSelectedSensorPosition(0);
    backRight.setSelectedSensorPosition(0);

  }




  // let's the driver switch gear ratios
  public void teleopGearboxes(boolean power) {
    if(power){
      gearBoxSolenoid.set(Value.kForward);
    }else{
      gearBoxSolenoid.set(Value.kReverse);
    }
  }


  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    //setDefaultCommand(new Teleop());
  }


}
