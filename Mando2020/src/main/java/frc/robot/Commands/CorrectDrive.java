// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/




// package frc.robot.Commands;
// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.Robot;
// import frc.robot.Subsystems.driveSubsystem;


// public class CorrectDrive extends Command {


//   double lastWriteTime = Timer.getFPGATimestamp();
//   double leftVelocity;
//   double rightVelocity;
//   double distance;
//   double goalDistance;
//   double distanceTraveled;
//   double leftVelocityCheck;
//   double rightVelocityCheck;
//   double position;

// /**
//  * 
//  * @param m_leftVelocity Gives the left motors a power to run
//  * @param m_rightVelocity Gives the right motors a power to run
//  * @param distance How far you want the robot to go in Feet
//  */
//   public CorrectDrive(double m_leftVelocity, double m_rightVelocity, double m_distance) {
//     // Use requires() here to declare subsystem dependencies
//     // eg. requires(chassis);
//     requires(Robot.m_ds);

//     leftVelocity = m_leftVelocity;
//     rightVelocity = m_rightVelocity;
//     distance = m_distance;
//   }

//   // Called just before this Command runs the first time
//   @Override
//   protected void initialize() {
//     // Robot.m_ds.frontRight.getEncoder().setPosition(0);
//     // Robot.m_ds.backLeft.getEncoder().setPosition(0);
//     // Robot.m_ds.backRight.getEncoder().setPosition(0);
//     // Robot.m_ds.frontLeft.getEncoder().setPosition(0);
//     Robot.m_ds.gyro.reset();
//   }

//   // Called repeatedly when this Command is scheduled to run
//   @Override
//   protected void execute() {
//     lastWriteTime = Timer.getFPGATimestamp();

//     // Cody made a function in Drive Subsystem for this
    
//     // leftVelocityCheck  = Robot.m_ds.frontLeft.getEncoder().getVelocity();
//     // rightVelocityCheck = Robot.m_ds.frontRight.getEncoder().getVelocity();

//     SmartDashboard.putNumber("leftVelocity", leftVelocityCheck);
//     SmartDashboard.putNumber("rightVelocty", rightVelocityCheck);
//     SmartDashboard.putNumber("position", position);

//     //Robot.m_ds.tankdrive(leftVelocity, rightVelocity);
//   }

//   // Make this return true when this Command no longer needs to run execute()
//   @Override
//   protected boolean isFinished() {
//     // Checks the distance the robot has traveled.
//     // double velocity = Robot.m_ds.frontLeft.getEncoder().getVelocity() / 42 / 600;
//     // position = (-1) * Robot.m_ds.frontRight.getEncoder().getPosition() / 84 * 5.75 * Math.PI / 12 * 10.71;

//     //  ft * 12in/1ft * 1wheelRotation/18.84in * 6 * Math.PI motorRotation/1wheelRotation * 42pulses/1motorRotation = pulses  (getPosition = pulses)

//     goalDistance = distance * 12 / (5.8 * Math.PI) * (50/14 * 48/16) * 42;

//     distanceTraveled = position;
    
//     if(distance > 0){
//       if(distance <= distanceTraveled){
//         return true;
//       }
//     }else if(distance >= distanceTraveled){
//       return true;
//     }
//     return false;
//   }

//   // Called once after isFinished returns true
//   @Override
//   protected void end() {
//     // Robot.m_ds.frontLeft.getEncoder().setPosition(0);
//     // Robot.m_ds.frontRight.getEncoder().setPosition(0);
//     // Robot.m_ds.backLeft.getEncoder().setPosition(0);
//     // Robot.m_ds.backRight.getEncoder().setPosition(0);
//     Robot.m_ds.gyro.reset();
//   }

//   // Called when another command which requires one or more of the same
//   // subsystems is scheduled to run
//   @Override
//   protected void interrupted() {
//   }
// }
