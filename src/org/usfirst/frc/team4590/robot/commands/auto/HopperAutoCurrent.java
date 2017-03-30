package org.usfirst.frc.team4590.robot.commands.auto;

import org.usfirst.frc.team4590.robot.commands.chassis.ArcadeDriveByValues;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightAndTurn;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByDistance;
import org.usfirst.frc.team4590.robot.commands.chassis.TurnDegrees;
import org.usfirst.frc.team4590.robot.commands.climber.Climb;
import org.usfirst.frc.team4590.robot.commands.feeder.FeedToShooter;
import org.usfirst.frc.team4590.robot.commands.fuelCollector.CollectFuel;
import org.usfirst.frc.team4590.robot.commands.shifter.ValveSetState;
import org.usfirst.frc.team4590.robot.subsystems.Shifts.ShifterState;
import org.usfirst.frc.team4590.utils.AllianceCommandGroup;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class HopperAutoCurrent extends AllianceCommandGroup {
	//RPM WAS CHANGED FROM 2025 TO 2050
	//_____________________BLUE CONSTANTS_____________________//
	//Last Speed: 2075 - was too short
	private static final double SHOOTER_RPM_BLUE = 2100;// 1st speed (we shot too short and left q3= 2025, 2nd 2060 still a little short.
	private static final double STRAIGHT_DISTANCE_BLUE = 1430;
	private static final double TOTAL_ANGLE_BLUE = -149.4;//value of last blue quals : -150.4 (too much to the left and too short by ~15cm)
	//next value was -146.9, still too much to the left by a bit
	
	//value of qual3 (too much to the left, and too short) -154;
	private static final double HOPPER_ANGLE_BLUE = -90;
	
	//____________________RED CONSTANTS____________________//
	private static final double SHOOTER_RPM_RED = 2090;
	private static final double STRAIGHT_DISTANCE_RED = 1500;
	/**
	 * 1st tested value: 168 Degrees - was not tested
	 * 2nd tested value: 166.3 Degrees - was tested, shot to the right, hit the ring but only 8 went in. (a little short too)
	 * 3rd value: 162.8 to be tested (15cm to the left than before)
	 * 
	 */
	private static final double TOTAL_ANGLE_RED = 162.8;// 168 - Red rpm value - was not tested;
	private static final double HOPPER_ANGLE_RED = 90;
	
	
	
	
	public void onBlue(){
		addParallel(new ValveSetState(ShifterState.POWER)); // 0 - 15
		addParallel(new BeNiceToShooter(SHOOTER_RPM_BLUE), 15); // 0 - 15
		addParallel(new DriveStraightAndTurn(STRAIGHT_DISTANCE_BLUE, HOPPER_ANGLE_BLUE, true), 2.95); // 0 - 2.95
		addSequential(new WaitCommand(1.4)); // 0 - 1.4
		addParallel(new CollectFuel(), 13.6); // 1.4 - 15
		addParallel(new FeedToShooter(-0.7), 3.25); // 1.4 - 4.65
		addSequential(new WaitCommand(1.55)); // 1.4 - 2.95
		addSequential(new ArcadeDriveByValues(-0.2, 0), 0.3); // 2.95 - 3.25 
		addSequential(new ArcadeDriveByValues(-0.75, 0), 0.3); // 3.25 - 3.55
		addSequential(new ArcadeDriveByValues(-0.2, 0), 0.3); // 3.55 - 3.85
		addSequential(new ArcadeDriveByValues(-0.75, 0), 0.3); // 3.85 - 4.15
		addSequential(new ArcadeDriveByValues(-0.2, 0), 0.3); // 4.15 - 4.45
		addSequential(new ArcadeDriveByValues(-0.75, 0), 0.3); // 4.45 - 4.75
		addSequential(new ArcadeDriveByValues(-0.2, 0), 0.3); // 4.75 - 5.05
		addSequential(new DriveStraightByDistance(500, HOPPER_ANGLE_BLUE, false), 0.8); // 5.05 - 5.85
		addParallel(new TurnDegrees(TOTAL_ANGLE_BLUE, false), 1.05); // 5.85 - 6.9
		addSequential(new WaitCommand(0.6)); // 5.85 - 6.45
		addSequential(new FeedToShooter(1), 8.55); // 6.45 - 15
	}
	
	public void onRed(){
		addParallel(new ValveSetState(ShifterState.POWER)); // 0 - 15
		addParallel(new BeNiceToShooter(SHOOTER_RPM_RED), 15); // 0 - 15
		addParallel(new DriveStraightAndTurn(STRAIGHT_DISTANCE_RED, HOPPER_ANGLE_RED, true), 2.95); // 0 - 2.95
		addSequential(new WaitCommand(1.4)); // 0 - 1.4
		addParallel(new CollectFuel(), 13.6); // 1.4 - 15
		addParallel(new FeedToShooter(-0.7), 3.25); // 1.4 - 4.65
		addSequential(new WaitCommand(1.55)); // 1.4 - 2.95
		addSequential(new ArcadeDriveByValues(-0.2, 0), 0.3); // 2.95 - 3.25 
		addSequential(new ArcadeDriveByValues(-0.75, 0), 0.3); // 3.25 - 3.55
		addSequential(new ArcadeDriveByValues(-0.2, 0), 0.3); // 3.55 - 3.85
		addSequential(new ArcadeDriveByValues(-0.75, 0), 0.3); // 3.85 - 4.15
		addSequential(new ArcadeDriveByValues(-0.2, 0), 0.3); // 4.15 - 4.45
		addSequential(new ArcadeDriveByValues(-0.75, 0), 0.3); // 4.45 - 4.75
		addSequential(new ArcadeDriveByValues(-0.2, 0), 0.3); // 4.75 - 5.05
		addSequential(new DriveStraightByDistance(500, HOPPER_ANGLE_RED, false), 0.8); // 5.05 - 5.85
		addParallel(new TurnDegrees(TOTAL_ANGLE_RED, false), 1.05); // 5.85 - 6.9
		addSequential(new WaitCommand(0.6)); // 5.85 - 6.45
		addSequential(new FeedToShooter(1), 8.55); // 6.45 - 15
	}
}
