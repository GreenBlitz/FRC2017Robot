package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.commands.gearsPlacer.ClosePlacer;
import org.usfirst.frc.team4590.robot.commands.gearsPlacer.OpenPlacer2;
import org.usfirst.frc.team4590.utils.AllianceCommandGroup;

import edu.wpi.first.wpilibj.command.WaitCommand;

/**
 *
 */
public class BasicGearsAutoGuyada extends AllianceCommandGroup {

	
	private boolean dir;
	public BasicGearsAutoGuyada(boolean dir) {
		this.dir = dir;
	}
	
	public void onBlue(){
		addSequential(new DriveStraightByDistance(1600), 2.5);
		addSequential(new WaitCommand(0.2));
		addSequential(new TurnDegrees(dir ? -60 : 60, 1000), 1);
		addSequential(new DriveStraightByVisionTimed(5000), 5);
		addSequential(new OpenPlacer2(), 1);
		addSequential(new WaitCommand(0.4));
		addSequential(new SmartArcadeDriveByValues(0.46, 0, 200));
		addSequential(new WaitCommand(0.4));
		addSequential(new StupidDriveStraightByDistance(750), 0.8);
		addParallel(new ClosePlacer(), 1);
		addSequential(new DriveStraightByDistance(-750, dir ? 60 : -60, false), 0.8);
		
		addSequential(new DriveStraightByDistance(750, dir ? 90 : -90, false), 0.8);
		
		addSequential(new StupidDriveStraightByDistance(1000, dir ? 60 : -60), 1);
		
		addSequential(new DriveStraightByDistance(dir ? 7000 : 7500, dir ? 60 : -17.3, false), 7.5);
		
	}
	
	public void onRed(){
		addSequential(new DriveStraightByDistance(1600), 2.5);
		addSequential(new WaitCommand(0.2));
		addSequential(new TurnDegrees(dir ? -60 : 60, 1000), 1);
		addSequential(new DriveStraightByVisionTimed(5000), 5);
		addSequential(new OpenPlacer2(), 1);
		addSequential(new WaitCommand(0.4));
		addSequential(new SmartArcadeDriveByValues(0.46, 0, 200));
		addSequential(new WaitCommand(0.4));
		addSequential(new StupidDriveStraightByDistance(750), 1);
		addParallel(new ClosePlacer(), 1);
		addSequential(new DriveStraightByDistance(-750, dir ? 60 : -60, false), 0.8);
		
		addSequential(new DriveStraightByDistance(750, dir ? 90 : -90, false), 0.8);
		
		addSequential(new StupidDriveStraightByDistance(1000, dir ? 60 : -60), 1);
		
		addSequential(new DriveStraightByDistance(dir ? 7000 : 7500, dir ? 17.3 : -60, false), 7.5);
		
	}
}
