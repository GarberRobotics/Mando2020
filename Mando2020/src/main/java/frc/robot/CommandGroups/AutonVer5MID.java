/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/*
*
*If this code doesn't work, please yell at Maddie.
*
*/

package frc.robot.CommandGroups;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonVer5MID extends CommandGroup {
  /**
   * 
   * Specifics:
   * 
   * This is the first auton if the robot is positioned in the middle of the field.
   * The robot wil first drop all 3 balls in its possession, back up off the line, 
   * then it will move forward 7ft to compensate for the 2ft it moved backwards.
   * After that, it'll turn left 90 degrees, move 5ft forward past a possible robot, then
   * turn 135 degrees right and move 5ft towards the center of the field.
   * 
   * TL;DR: See AutonVer3LEFT for first part of auton. This is the same code, but 
   * for when the robot is oriented in the middle of the field. After it runs the 
   * first part of the code, it will move through the rendezvous, and then into the 
   * back part of the trench.  
   */
  public AutonVer5MID() {
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    //This auton does the same thing as auton 3 but from
    //the middle starting position and !!!!FACING FORWARD!!!



    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
