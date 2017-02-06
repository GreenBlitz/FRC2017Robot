package org.usfirst.frc.team4590.robot.commands.gearsPlacer;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveByDistance;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceGear extends CommandGroup {

	public PlaceGear() {
		addSequential(new OpenPlacer());
		addSequential(new DriveByDistance(-10.0));// TODO check which value is
													// needed
		addSequential(new ClosePlacer());
	}
}
