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

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.Commands.DriveStraightCommand;
import frc.robot.Commands.shootBallCommand;
import frc.robot.Commands.turnDrive;

public class AutonVer2LEFT extends CommandGroup {
  /**
   * Add your docs here.
   */
  public AutonVer2LEFT() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.
    // Robot.m_ds.frontLeft.getEncoder().setPosition(0);
    // Robot.m_ds.frontRight.getEncoder().setPosition(0);
    // Robot.m_ds.backLeft.getEncoder().setPosition(0);
    // Robot.m_ds.backRight.getEncoder().setPosition(0);
    
    Robot.m_ds.resetMotorEncoders();

    // addSequential(new DriveStraightCommand(0.5,0.5,6));

    // addSequential(new shootBallCommand(3));

    addSequential(new DriveStraightCommand(0.5, 0.5, 5.5));

    addSequential(new turnDrive(0.5, 0.5, 90));

    addSequential(new DriveStraightCommand(0.5, 0.5, 1));
    // addSequential(new DriveStraightCommand(0.5, 0.5, 0.5));

   // addSequential(new turnDrive());

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
