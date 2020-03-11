// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.Commands;

// import com.ctre.phoenix.motorcontrol.ControlMode;

// import edu.wpi.first.wpilibj.Timer;
// import edu.wpi.first.wpilibj.command.Command;
// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import frc.robot.Robot;

// public class shootBall extends Command {

//   double numberOfBalls; // number of balls decides how many balls are being shot.
//   double ballsShot; // how many balls have already been shot.
//   double shooterPower = 0.5; // default power.  Will be changed in the code.  Determines how fast the robot's shooter spins
//   double startTime = Timer.getFPGATimestamp(); // time when the command started
//   double currentTime = Timer.getFPGATimestamp(); // Is used along with ball amount to decide the amount of time neccessary to shoot all balls
//   boolean shootClose = false; // Will shoot close.  RIGHT NEXT TO GOAL
//   boolean shootFar = false; // Will shoot from farther away.  About a robot's length
//   boolean correctSpeed = false; // activates conveyor when the shooter has reached an apporpriate speed
  

//   /**
//    * 
//    * @param m_numberOfBalls how many balls are being shot
//    * @param m_goalType set as 1 if the robot is next to the goal and 2 if the robot is not next to the goal
//    * 
//    */
//   public shootBall(double m_numberOfBalls, double m_goalType) {
//     // Use requires() here to declare subsystem dependencies
//     // eg. requires(chassis);
//     requires(Robot.m_ds);
//     numberOfBalls = m_numberOfBalls;
//     if(m_goalType == 1){
//       shootClose = true;
//     }else if(m_goalType == 2){
//       shootFar = true;
//     }
//   }

//   // Called just before this Command runs the first time
//   @Override
//   protected void initialize() {
//     // reset motor values and timers here
//   }

//   // Called repeatedly when this Command is scheduled to run
//   @Override
//   protected void execute() {
//     currentTime = Timer.getFPGATimestamp();
    
//     double vel = Math.abs(Robot.m_sms.shooterRight.getSelectedSensorVelocity() / 1024 * 600 / 4);
    
//     SmartDashboard.putNumber("Shooter Velocity", vel);
//     SmartDashboard.putNumber("Shooter Out", Robot.m_sms.shooterRight.getMotorOutputPercent());
//     SmartDashboard.putNumber("shooterPower value", shooterPower);
//     SmartDashboard.putNumber("I has power", Robot.m_sms.shooterRight.getMotorOutputPercent());

//     if(Robot.m_Xbox.getRawButton(5)){
//       shootClose = true;
//       shootFar = false;
//     }else if(Robot.m_Xbox.getRawButton(6)){
//       shootClose = false;
//       shootFar = true;
//     }else if(Robot.m_Xbox.b.get()){
//       shootClose = false;
//       shootFar = false;
//     }

//     if(shootClose){
//       Robot.m_sms.shooter(shooterPower);

//       if(vel < 12800){
//         shooterPower = shooterPower + 0.001;
//       }else if(vel > 13000){
//         shooterPower = shooterPower - 0.001;
//       }else if(12800 < vel && vel < 13000){
//         correctSpeed = true;
//       }

//       if(vel < 9000){
//         shooterPower = shooterPower + 0.01;
//       }else if(vel > 14000){
//         shooterPower = shooterPower - 0.01;
//       }
//     }
    
//     if(shootFar){ 
//         Robot.m_sms.shooter(shooterPower);
  
//         if(vel < 20000){
//           shooterPower = shooterPower + 0.001;
//         }else if(vel > 20200){
//           shooterPower = shooterPower - 0.001;
//         }else if(20000 < vel && vel < 20200){
//           correctSpeed = true;
//         }
  
//         if(vel < 17000){
//           shooterPower = shooterPower + 0.01;
//         }else if(vel > 22000){
//           shooterPower = shooterPower - 0.01;
//         }
//       }

//       if(correctSpeed){
//         Robot.m_sms.conveyor(true, true);
//       }

//     // if(Robot.m_ds.shooterLeft.getSelectedSensorVelocity() > ){
//       // Robot.m_ds.conveyor(0.7);
//     // }

//     // add smart dashboard values here

//     SmartDashboard.putNumber("power cells being shot", numberOfBalls);
//     SmartDashboard.putNumber("Shooter Velocity", Robot.m_sms.shooterLeft.getSelectedSensorVelocity(0));


//   }

//   // Make this return true when this Command no longer needs to run execute()
//   @Override
//   protected boolean isFinished() {
//     currentTime = Timer.getFPGATimestamp() - startTime;
//     ballsShot = currentTime - 1;
    
//     if(ballsShot < numberOfBalls){
//       Robot.m_sms.shooter(0);
//       Robot.m_sms.conveyor(false, false);
//       return true;
//     }

    
//     return false;
//   }

//   // Called once after isFinished returns true
//   @Override
//   protected void end() {
//   }

//   // Called when another command which requires one or more of the same
//   // subsystems is scheduled to run
//   @Override
//   protected void interrupted() {
//     end();
//   }
// }
