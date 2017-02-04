package org.usfirst.frc.team4590.robot.commands.shifter;

import org.usfirst.frc.team4590.robot.subsystems.Shifts;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Changes the shift to the opposite value. Example: SPEED -> POWER
 */
public class ValveSetState extends Command {

	public ValveSetState() {
		// Use requires() here to declare subsystem dependencies
		requires(Shifts.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Shifts.getInstance().toggleState();
		// already called in constuctor
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return true;
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
