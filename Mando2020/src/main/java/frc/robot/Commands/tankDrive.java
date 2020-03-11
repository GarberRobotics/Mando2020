// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.Commands;


// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.Robot;

// public class tankDrive extends Command {

//   double leftSpeed;
//   double rightSpeed;
//   double goalDistance;
//   double currentDistance; //Current distance is the total distance the robot has travelled.

//   /**
//    * Creates a new tankDrive.
//    */
//   public tankDrive(double m_leftSpeed, double m_rightSpeed, double m_goalDistance) {
//     // Use addRequirements() here to declare subsystem dependencies.
//     requires(Robot.m_ds);
//     // takes the given inputs and transfers them into the command
//     leftSpeed = -m_leftSpeed; // make negative or not negative depending on motors
//     rightSpeed = -m_rightSpeed; // make negative or not negative depending on motors
//     goalDistance = m_goalDistance; // Goal distance is the distance from the goal
//   }

//   // Called when the command is initially scheduled.
//   @Override
//   public void initialize() {
//     //.m_ds.frontLeft.getEncoder().setPosition(0);
//     Robot.m_ds.frontRight.getEncoder().setPosition(0);
//     Robot.m_ds.backLeft.getEncoder().setPosition(0);
//     Robot.m_ds.backRight.getEncoder().setPosition(0);
//     Robot.m_ds.gyro.reset();
    
//   }

//   // Called every time the scheduler runs while the command is scheduled.
//   @Override
//   public void execute() {
//     Robot.m_ds.tankdrive(-leftSpeed, -rightSpeed);

//     //Adds variable values to SmartDashboard

//     SmartDashboard.putNumber("LeftSpeed", leftSpeed);
//     SmartDashboard.putNumber("RightSpeed", rightSpeed);
//     SmartDashboard.putNumber("GoalDistance", goalDistance);
//     SmartDashboard.putNumber("curentDistance", currentDistance);
//   }

//   // Called once the command ends or is interrupted.
//   // @Override
//   // public void end(boolean interrupted) {
//   // }

//   // Returns true when the command should end.
//   @Override
//   public boolean isFinished() {
//     // return true;
//     // Checks the distance the robot has traveled.

//     double rotations = Robot.m_ds.frontLeft.getEncoder().getPosition();
//     currentDistance = rotations / 5.26189813614;
//     if(goalDistance > 0){
//       if(goalDistance <= currentDistance){
//         Robot.m_ds.frontLeft.getEncoder().setPosition(0);
//         Robot.m_ds.frontRight.getEncoder().setPosition(0);
//         Robot.m_ds.backLeft.getEncoder().setPosition(0);
//         Robot.m_ds.backRight.getEncoder().setPosition(0);
//         Robot.m_ds.gyro.reset();
//         rotations = 0;
//         return true;
//       }
//     }else if(goalDistance >= currentDistance){
//       Robot.m_ds.frontLeft.getEncoder().setPosition(0);
//       Robot.m_ds.frontRight.getEncoder().setPosition(0);
//       Robot.m_ds.backLeft.getEncoder().setPosition(0);
//       Robot.m_ds.backRight.getEncoder().setPosition(0);
//       Robot.m_ds.gyro.reset();
//       rotations = 0;
//       return true;
//     }
//     return false;
//    }
// }
