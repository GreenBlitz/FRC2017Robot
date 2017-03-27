package org.usfirst.frc.team4590.robot.commands.auto;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightAndTurn;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByDistance;
import org.usfirst.frc.team4590.robot.commands.chassis.TurnDegrees;
import org.usfirst.frc.team4590.robot.commands.feeder.FeedToShooter;
import org.usfirst.frc.team4590.robot.commands.fuelCollector.CollectFuel;
import org.usfirst.frc.team4590.robot.commands.shifter.ValveSetState;
import org.usfirst.frc.team4590.robot.commands.shooter.ShooterSetSpeed;
import org.usfirst.frc.team4590.robot.commands.shooter.ShooterSetSpeedCopy;
import org.usfirst.frc.team4590.robot.subsystems.Shifts.ShifterState;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class HopperAutoB2 extends CommandGroup{
	public HopperAutoB2(){
		addParallel(new ValveSetState(ShifterState.POWER));
		addParallel(new ShooterSetSpeedCopy(1980), 15.5);
		//Angle to drive at 90 - 72.1811111
		addSequential(new DriveStraightAndTurn(1500, -85, true), 2.55);
		addSequential(new WaitCommand(1.5));
		addSequential(new DriveStraightByDistance(500), 0.65);
		addParallel(new CollectFuel(), 10.8);
		addParallel(new TurnDegrees(-63, true), 0.8);
		//addParallel(new TurnToGoalByVision(), 0.45);
		addSequential(new WaitCommand(0.5));
		addSequential(new FeedToShooter(), 10.3);
	}	

}
