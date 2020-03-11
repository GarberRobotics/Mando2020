/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.Subsystems.ClimbingSubsystem;
import frc.robot.Subsystems.WoFSubsystem;
import frc.robot.Subsystems.driveSubsystem;
import frc.robot.Subsystems.shootingMechanismSubsystem;
import frc.robot.Xbox;
import frc.robot.CommandGroups.AutonVer1LEFT;
import frc.robot.CommandGroups.AutonVer2LEFT;
import frc.robot.CommandGroups.AutonVer3LEFT;
import frc.robot.CommandGroups.AutonVer4MID;
import frc.robot.CommandGroups.AutonVer5MID;
import frc.robot.CommandGroups.AutonVer6MID;
import frc.robot.CommandGroups.AutonVer7RIGHT;
import frc.robot.CommandGroups.AutonVer8RIGHT;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.MjpegServer;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoMode.PixelFormat;

/**
 * This is a demo program showing the use of the RobotDrive class, specifically
 * it contains the code necessary to operate a robot with tank drive.
 */
public class Robot extends TimedRobot {
  public static ClimbingSubsystem m_cs = new ClimbingSubsystem();
  public static driveSubsystem m_ds = new driveSubsystem();
  public static shootingMechanismSubsystem m_sms = new shootingMechanismSubsystem();
  public static WoFSubsystem m_WoFs = new WoFSubsystem();
  public static Xbox m_Xbox = new Xbox(0);
  public static XboxController stick = new XboxController(0);
  public ADXRS450_Gyro gyro = new ADXRS450_Gyro();


	public static CameraServer Cam0;
  public static CameraServer Cam1;
  //camera stuff
    UsbCamera cam1 = CameraServer.getInstance().startAutomaticCapture(0);
    UsbCamera cam2 = CameraServer.getInstance().startAutomaticCapture(1);
  // public static OI m_oi;

  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // This is very big change...

  

  
  
  // public static XboxController m_XboxController = new 

  // Is this even needed?  ------------------------------------------------------------\
  //private DifferentialDrive m_myRobot;                                               |
                                                                                    // |
  // public Joystick m_leftStick;                                                      // |
  // public Joystick m_rightStick; 
  // |
            
  

  // |
  @Override                                                                         // |
  public void robotInit() {                                                         // |
    //Auton choosers for SmartDashboard             
    m_ds.compressor.start();
    cam1.setFPS(15);
    cam2.setFPS(15);
    cam1.setResolution(120, 160);
    cam2.setResolution(120, 160);
    
    m_cs.hookMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    m_sms.shooterLeft.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    m_sms.shooterRight.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    m_WoFs.WoFmotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);
    
    m_chooser.setDefaultOption("Auton 1 Left (Default)", new AutonVer1LEFT());      
    m_chooser.addOption("Auton 2 Left", new AutonVer2LEFT()); 
    m_chooser.addOption("Auton 3 Left", new AutonVer3LEFT());                       
    m_chooser.addOption("Auton 4 Middle", new AutonVer4MID());                      
    m_chooser.addOption("Auton 5 Middle", new AutonVer5MID());                      
    m_chooser.addOption("Auton 6 Middle", new AutonVer6MID());                      
    m_chooser.addOption("Auton 7 Right", new AutonVer7RIGHT());                     
    m_chooser.addOption("Auton 8 Right", new AutonVer8RIGHT());                     
                                                                                    
    //m_myRobot = new DifferentialDrive(new PWMVictorSPX(0), new PWMVictorSPX(1)); <---/
    SmartDashboard.putData(m_chooser);
    // m_leftStick = new Joystick(0);
    // m_rightStick = new Joystick(1);
  }

  @Override
  public void autonomousInit() {
    // todo Auto-generated method stub
    m_autonomousCommand = m_chooser.getSelected();
    if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }
  }


  // public void teleopPeriodic() {
  
  //   Scheduler.getInstance().run();
  // }
  
  @Override
  public void teleopPeriodic() {
    // CONTROLLS!

    // drive:                 leftStick.    press buttonStart = half.   press Dpad-up = forward / press Dpad-down = reverse.
    // gearboxes:             lBumper  = slow.  rBumper = fast
    // intake:                lTrigger = lower. rTriger = raise. buttonY = on/off
    // Control climbing claw: rightStick (control descent values)
    // pull Robot Up:         buttonX = up
    // Wheel of Fortune:      hold Dpad-left = raise WoF + (rightStick = control WoF)
    // shooting type:         hold buttonA = shortShot. hold buttonB = longShot


    double vel = Math.abs(m_sms.shooterRight.getSelectedSensorVelocity() / 1024 * 600 / 4);
    
    SmartDashboard.putNumber("Shooter Velocity", vel);
    SmartDashboard.putNumber("Shooter Out", m_sms.shooterRight.getMotorOutputPercent());
    SmartDashboard.putNumber("shooterPower value", m_sms.shooterPower);
    SmartDashboard.putNumber("I has power", m_sms.shooterRight.getMotorOutputPercent());
    SmartDashboard.putBoolean("Claw", m_cs.endClimb);
    SmartDashboard.putNumber("limit check", m_WoFs.WoFmotor.getSelectedSensorPosition());
    SmartDashboard.putBoolean("end climb", m_cs.endClimb);
    SmartDashboard.putNumber("L raw Shooter Velocity", m_sms.shooterLeft.getSelectedSensorVelocity());
    SmartDashboard.putNumber("R raw Shooter Velocity", m_sms.shooterRight.getSelectedSensorVelocity());

    SmartDashboard.putNumber("Conveyor velocity", m_sms.conveyorRight.getSelectedSensorVelocity());
    SmartDashboard.putNumber("Position", m_sms.shooterRight.getSelectedSensorPosition());

    SmartDashboard.putNumber("WOF!  ITS THE WOF!", m_WoFs.WoFmotor.getSelectedSensorVelocity());

    SmartDashboard.putNumber("Distance", m_ds.frontLeft.getSelectedSensorPosition());
      
    // drive
    m_ds.teleopDrive();

    // shooting
    m_sms.teleopShooter();

    // intake
    m_sms.teleopIntake();

    // Wheel of fortune
    m_WoFs.teleopWheelOfFortune();
    
    // // // setting the claw
    m_cs.teleopClaw();

    // // // pulling the robot up with the claw
    m_cs.teleopClimb();

  }

  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
   // SmartDashboard.putNumber("motor rotations", Robot.m_ds.frontLeft.getEncoder().getPosition());
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

}


