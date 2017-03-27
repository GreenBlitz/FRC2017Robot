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

public class HopperAutoA extends CommandGroup{
	public HopperAutoA(){
		addParallel(new ValveSetState(ShifterState.SPEED));
		//Angle to drive at 90 - 72.1811111
		addSequential(new TurnDegrees(12.5), 0.5);
		addSequential(new DriveStraightAndTurn(2300, 90, false), 2.4);
		addParallel(new ShooterSetSpeed(2100), 12.1);
		addSequential(new WaitCommand(1.5));
		addParallel(new CollectFuel(), 11);
		addSequential(new TurnDegrees(86.5), 1.25);
		addParallel(new DriveStraightByDistance(-500), 0.5);
		addSequential(new WaitCommand(0.25));
		addSequential(new FeedToShooter(), 9.5);
	}
}
