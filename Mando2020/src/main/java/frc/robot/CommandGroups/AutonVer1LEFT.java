/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*
*
*If this code doesn't work, please yell at Cody.
*Don't yell at anyone.  Calmly talk to anyone
*
*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.Commands.DriveStraightCommand;
import frc.robot.Commands.shootBallCommand;

public class AutonVer1LEFT extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutonVer1LEFT() {

    
    Robot.m_ds.resetMotorEncoders();
    Robot.m_sms.intakeSolenoid.set(Value.kForward);

    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order..

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.


    addSequential(new DriveStraightCommand(0.5,0.5,6));

    addSequential(new shootBallCommand(3));

    addSequential(new DriveStraightCommand(0, 0, 0));


    //move off the line into position
    // addSequential(new CorrectDrive(0.5,0.5,5));

  //  addSequential(new CorrectDrive(0.5,0.5,5));
    //shoot into score!
    // addSequential(new shootBall(3,2));

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
