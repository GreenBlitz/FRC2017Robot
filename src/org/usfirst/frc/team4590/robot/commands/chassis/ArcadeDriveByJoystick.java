package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.OI;
import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives in arcade mode: Y axis controls the power forward while the X axis
 * controls the rotation.
 */
public class ArcadeDriveByJoystick extends Command {

	public ArcadeDriveByJoystick() {
		// Use requires() here to declare subsystem dependencies
		requires(Chassis.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("yay");
		Chassis.getInstance().arcadeDrive(-OI.getInstance().getMainNormalLeftY(), OI.getInstance().getMainNormalRightX());
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
