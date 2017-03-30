package org.usfirst.frc.team4590.robot.commands.auto;

import org.usfirst.frc.team4590.robot.commands.shooter.ShooterSetSpeedCopy;
import org.usfirst.frc.team4590.robot.commands.shooter.ShooterSetValue;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class BeNiceToShooter extends CommandGroup {
	public BeNiceToShooter(double rpm){
		addSequential(new ShooterSetValue(0.5), 0.4);
		addSequential(new ShooterSetSpeedCopy(rpm));
	}
}
