/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*
*
*If this code doesn't work, please yell at Cody.
*
*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.Commands.DriveStraightCommand;
import frc.robot.Commands.shootBallCommand;

public class AutonVer3LEFT extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutonVer3LEFT() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    //FACE THE ROBOT FORWARD WHEN YOU SET THIS UP

        
    Robot.m_ds.resetMotorEncoders();
    Robot.m_sms.intakeSolenoid.set(Value.kForward);


    addSequential(new DriveStraightCommand(0.5, 0.5, 9));
    addSequential(new shootBallCommand(3));

    

    //Code for going through the rendezvous if needed.
    // addSequential(new turnDrive(0.5, 0.5, 60));
    // addSequential(new tankDrive(0.5, 0.5, 7));
    // addSequential(new turnDrive(-0.5, -0.5, -60));
    // addSequential(new tankDrive(0.5, 0.5, 9.5));
    // addSequential(new turnDrive(-0.5, -0.5, -60));
    // addSequential(new tankDrive(0.5, 0.5, 3));

  }
}
