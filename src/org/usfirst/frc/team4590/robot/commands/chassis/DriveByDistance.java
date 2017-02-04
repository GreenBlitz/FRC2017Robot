package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives a given distance.
 */
public class DriveByDistance extends Command {

	private double dis;
	int timesOnTarget = 0;

	public DriveByDistance(double dis) {
		// Use requires() here to declare subsystem dependencies
		// requires(Robot.chassis);
		this.dis = dis; // XD
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Chassis.getInstance().enable();
		Chassis.getInstance().resetEncoders();
		Chassis.getInstance().setAbsoluteTolerance(0.2);
		Chassis.getInstance().setSetpoint(dis);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Chassis.getInstance().onTarget()) {
			timesOnTarget++;
		} else {
			timesOnTarget = 0;
		}
		return timesOnTarget < 30;
	}

	// Called once after isFinished returns true
	protected void end() {
		Chassis.getInstance().disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
