package org.usfirst.frc.team4590.robot.commands.gearsPlacer;

import org.usfirst.frc.team4590.robot.subsystems.GearsPlacer;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenPlacer extends Command {

	public OpenPlacer() {
		// Use requires() here to declare subsystem dependencies
		requires(GearsPlacer.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		GearsPlacer.getInstance().setPower(1);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return GearsPlacer.getInstance().getSwitchIsOpenState();
	}

	// Called once after isFinished returns true
	protected void end() {
		GearsPlacer.getInstance().setPower(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
