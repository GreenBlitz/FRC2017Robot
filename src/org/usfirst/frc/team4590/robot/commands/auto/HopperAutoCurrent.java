package org.usfirst.frc.team4590.robot.commands.auto;

import org.usfirst.frc.team4590.robot.commands.chassis.ArcadeDriveByValues;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightAndTurn;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByDistance;
import org.usfirst.frc.team4590.robot.commands.chassis.TurnDegrees;
import org.usfirst.frc.team4590.robot.commands.feeder.FeedToShooter;
import org.usfirst.frc.team4590.robot.commands.fuelCollector.CollectFuel;
import org.usfirst.frc.team4590.robot.commands.shifter.ValveSetState;
import org.usfirst.frc.team4590.robot.commands.shooter.ShooterSetSpeedCopy;
import org.usfirst.frc.team4590.robot.subsystems.Shifts.ShifterState;
import org.usfirst.frc.team4590.utils.AllianceCommandGroup;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class HopperAutoCurrent extends AllianceCommandGroup {
	
	//_____________________BLUE CONSTANTS_____________________//
	private static final double SHOOTER_RPM_BLUE = 2025;
	private static final double STRAIGHT_DISTANCE_BLUE = 1430;
	private static final double TOTAL_ANGLE_BLUE = -155;
	private static final double HOPPER_ANGLE_BLUE = -90;
	
	//____________________RED CONSTANTS____________________//
	private static final double SHOOTER_RPM_RED = 2025;
	private static final double STRAIGHT_DISTANCE_RED = 1430;
	private static final double TOTAL_ANGLE_RED = 168;
	private static final double HOPPER_ANGLE_RED = 90;
	
	
	
	
	public void onRed(){
		addParallel(new ValveSetState(ShifterState.POWER));
		addParallel(new BeNiceToShooter(SHOOTER_RPM_BLUE), 15);
		addSequential(new DriveStraightAndTurn(STRAIGHT_DISTANCE_BLUE, HOPPER_ANGLE_BLUE, true), 2.5);
		addSequential(new ArcadeDriveByValues(0.2, 0), 2.5);
		addSequential(new DriveStraightByDistance(500), 0.7);
		addParallel(new CollectFuel(), 9.3);
		addParallel(new TurnDegrees(TOTAL_ANGLE_BLUE - HOPPER_ANGLE_BLUE, false), 1);
		addSequential(new WaitCommand(0.25));
		addSequential(new FeedToShooter(), 9.05);
	}
	
	public void onBlue(){
		addParallel(new ValveSetState(ShifterState.POWER));
		addParallel(new BeNiceToShooter(SHOOTER_RPM_RED), 15);
		addSequential(new DriveStraightAndTurn(STRAIGHT_DISTANCE_RED, HOPPER_ANGLE_RED, true), 2.5); 
		addSequential(new ArcadeDriveByValues(0.2, 0), 2.5);
		addSequential(new DriveStraightByDistance(500), 0.7);
		addParallel(new CollectFuel(), 9.3);
		addParallel(new TurnDegrees(TOTAL_ANGLE_RED - HOPPER_ANGLE_RED, false), 1);
		addSequential(new WaitCommand(0.25));
		addSequential(new FeedToShooter(), 9.05);
	}
}
