package org.usfirst.frc.team4590.robot.commands.shifter;

import static org.usfirst.frc.team4590.robot.subsystems.Shifts.ShifterState.SPEED;
import static org.usfirst.frc.team4590.robot.subsystems.Shifts.ShifterState.POWER;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.robot.subsystems.Shifts;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Shifts gears automatically by the speed of the robot.
 */
public class AutoGearShifter extends Command {

	private final double MAX_THRESHOLD = 700.0, MIN_THRESHOLD = 500.0;

	private double velocity;

	public AutoGearShifter() {
		// Use requires() here to declare subsystem dependencies
		requires(Shifts.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize() {

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		velocity = Chassis.getInstance().getSpeed();

		if (velocity > MAX_THRESHOLD) {
			Shifts.getInstance().setState(SPEED);
		} else if (velocity < MIN_THRESHOLD) {
			Shifts.getInstance().setState(POWER);
		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
