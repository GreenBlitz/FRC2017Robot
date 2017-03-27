package org.usfirst.frc.team4590.robot.commands.auto;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByDistance;
import org.usfirst.frc.team4590.robot.commands.chassis.TurnDegrees;
import org.usfirst.frc.team4590.robot.commands.feeder.FeedToShooter;
import org.usfirst.frc.team4590.robot.commands.fuelCollector.CollectFuel;
import org.usfirst.frc.team4590.robot.commands.shooter.ShooterSetSpeed;
import org.usfirst.frc.team4590.utils.AllianceCommandGroup;

import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootAndCrossLine extends AllianceCommandGroup{
	
	private static final double ANGLE_DIFF = 0;
	
	public void onRed()
	{
		
		addParallel(new ShooterSetSpeed(2025), 7);
		addSequential(new WaitCommand(2));
		addParallel(new FeedToShooter(0.66), 5);
		addParallel(new CollectFuel(), 5);
		addSequential(new WaitCommand(5));
		addSequential(new TurnDegrees(-90 - ANGLE_DIFF, true), 3);
		addSequential(new DriveStraightByDistance(-1600), 3.5);
		addSequential(new TurnDegrees(-60), 1.5);
	}
	
	public void onBlue()
	{
		addParallel(new ShooterSetSpeed(2025), 7);
		addSequential(new WaitCommand(2));
		addParallel(new FeedToShooter(0.66), 5);
		addParallel(new CollectFuel(), 5);
		addSequential(new WaitCommand(5));
		addSequential(new TurnDegrees(90 - ANGLE_DIFF, true), 3);
		addSequential(new DriveStraightByDistance(-1600), 3.5);
		addSequential(new TurnDegrees(60), 1.5);
	}
}
