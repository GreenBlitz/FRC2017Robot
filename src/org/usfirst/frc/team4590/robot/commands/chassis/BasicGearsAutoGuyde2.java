package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.commands.gearsPlacer.ClosePlacer;
import org.usfirst.frc.team4590.robot.commands.gearsPlacer.OpenPlacer2;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class BasicGearsAutoGuyde2 extends CommandGroup {

	public BasicGearsAutoGuyde2(boolean dir) {
		addSequential(new DriveStraightByVisionTimed(5000));
		addSequential(new OpenPlacer2());
		addSequential(new WaitCommand(0.7));
		addSequential(new SmartArcadeDriveByValues(-0.46, 0, 1250));
		
		addSequential(new ClosePlacer());
		
		//addSequential(new ArcadeDriveByValues(0.60, 0, 700));
		
		//addSequential(new ArcadeDriveByValues(-0.75, 0, 800));
		
		//addSequential(new ArcadeDriveByValues(0.4590, dir ? 0.45 : -0.45, 900));

		addSequential(new TurnDegrees(dir ? -32 : 32, 1250));
		
		addSequential(new SmartArcadeDriveByValues(0.55, 0, 6000));
		// addSequential(new Command2());
		// these will run in order.

		// To run multiple commands at the same time,
		// use addParallel()
		// e.g. addParallel(new Command1());
		// addSequential(new Command2());
		// Command1 and Command2 will run in parallel.

		// A command group will require all of the subsystems that each member
		// would require.
		// e.g. if Command1 requires chassis, and Command2 requires arm,
		// a CommandGroup containing them would require both the chassis and the
		// arm.
	}
}
