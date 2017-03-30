package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.commands.gearsPlacer.ClosePlacer;
import org.usfirst.frc.team4590.robot.commands.gearsPlacer.OpenPlacer2;
import org.usfirst.frc.team4590.robot.commands.shifter.ValveSetState;
import org.usfirst.frc.team4590.robot.subsystems.Shifts;
import org.usfirst.frc.team4590.robot.subsystems.Shifts.ShifterState;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class BasicGearsAutoGuyada2 extends CommandGroup {

	public BasicGearsAutoGuyada2(boolean dir) {

		addSequential(new DriveStraightByDistance(1600), 2.5);
		addSequential(new WaitCommand(0.2));
		addSequential(new TurnDegrees(dir ? -60 : 60, 1000), 1);
		addSequential(new DriveStraightByVisionTimed(5000), 5);
		addSequential(new OpenPlacer2(), 1);
		addSequential(new WaitCommand(0.7));
		addSequential(new SmartArcadeDriveByValues(0.46, 0, 500));
		addSequential(new WaitCommand(0.7));
		addSequential(new StupidDriveStraightByDistance(750, 60 ,false), 1.2);
		
		//addSequential(new ArcadeDriveByValues(0.60, 0, 700));
		
		//addSequential(new ArcadeDriveByValues(-0.75, 0, 800));
		
		//addSequential(new ArcadeDriveByValues(0.4590, dir ? 0.45 : -0.45, 900));

		//addSequential(new TurnByAngle(dir ? -32 : 32, 1250));
		addParallel(new ClosePlacer(), 1);
		addSequential(new DriveStraightByDistance(-750, dir ? 60 : -60, false), 1.2);
		
		addSequential(new DriveStraightByDistance(750, dir ? 90 : -90, false), 1.2);
		
		addSequential(new DriveStraightByDistance(5000, dir ? 60 : -60, false), 6);
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
