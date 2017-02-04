package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.OI;
import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives in tank mode: Y axis of each joystick controls its parallel cim (left
 * joystick = left sim, right = right).
 */
public class TankDriveByJoystick extends Command {

	public TankDriveByJoystick() {
		// Use requires() here to declare subsystem dependencies
		requires(Chassis.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Chassis.getInstance().tankDrive(OI.getInstance().getMainLeftY(), OI.getInstance().getMainRightY());
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
