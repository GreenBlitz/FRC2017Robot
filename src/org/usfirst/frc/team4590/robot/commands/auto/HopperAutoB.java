package org.usfirst.frc.team4590.robot.commands.auto;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightAndTurn;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByDistance;
import org.usfirst.frc.team4590.robot.commands.chassis.TurnDegrees;
import org.usfirst.frc.team4590.robot.commands.feeder.FeedToShooter;
import org.usfirst.frc.team4590.robot.commands.fuelCollector.CollectFuel;
import org.usfirst.frc.team4590.robot.commands.shifter.ValveSetState;
import org.usfirst.frc.team4590.robot.commands.shooter.ShooterSetSpeed;
import org.usfirst.frc.team4590.robot.subsystems.Shifts.ShifterState;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class HopperAutoB extends CommandGroup{
	public HopperAutoB(){
		addParallel(new ValveSetState(ShifterState.POWER));
		//Angle to drive at 90 - 72.1811111
		addSequential(new TurnDegrees(-12.5), 0.5);
		addSequential(new DriveStraightAndTurn(2300, -90, false), 2);
		addParallel(new ShooterSetSpeed(2125), 12.5);
		addSequential(new WaitCommand(1.5));
		addParallel(new CollectFuel(), 11);
		addSequential(new TurnDegrees(-92), 1.25);
		addParallel(new DriveStraightByDistance(-500), 0.5);
		addSequential(new WaitCommand(0.25));
		addSequential(new FeedToShooter(), 9.5);
	}
}
