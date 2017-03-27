package org.usfirst.frc.team4590.robot.commands.auto;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightAndTurn;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByDistance;
import org.usfirst.frc.team4590.robot.commands.chassis.TurnDegrees;
import org.usfirst.frc.team4590.robot.commands.feeder.FeedToShooter;
import org.usfirst.frc.team4590.robot.commands.fuelCollector.CollectFuel;
import org.usfirst.frc.team4590.robot.commands.shifter.ValveSetState;
import org.usfirst.frc.team4590.robot.commands.shooter.ShooterSetSpeedCopy;
import org.usfirst.frc.team4590.robot.subsystems.Shifts.ShifterState;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class HopperAutoA2 extends CommandGroup{
	public HopperAutoA2(){
		addParallel(new ValveSetState(ShifterState.POWER));
		addParallel(new ShooterSetSpeedCopy(2025), 15.5);
		//Angle to drive at 90 - 72.1811111
		addSequential(new DriveStraightAndTurn(1430, -90, true), 2.65); // guy don't touch this I will kill you - shachar
		addSequential(new WaitCommand(2.5));
		addSequential(new DriveStraightByDistance(500), 0.75);
		addParallel(new CollectFuel(), 11.75);
		addParallel(new TurnDegrees(-65, true), 1);
		
		//addParallel(new TurnToGoalByVision(), 0.45);
		addSequential(new WaitCommand(0.25));
		addSequential(new FeedToShooter(), 9.9);
	}
}
