package org.usfirst.frc.team4590.robot.commands.gearsPlacer;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceGear extends CommandGroup {

	public PlaceGear() {
		
		addSequential(new OpenPlacer2());
		addSequential(new DriveStraightByDistance(-10.0));// TODO change commad
		addSequential(new ClosePlacer());
	}
}
