package org.usfirst.frc.team4590.robot.commands.gearsPlacer;

import org.usfirst.frc.team4590.robot.subsystems.GearsPlacer;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClosePlacer extends Command {

	private boolean m_init = false;
	
	private long m_start;
	
	public ClosePlacer() {
		// Use requires() here to declare subsystem dependencies
		requires(GearsPlacer.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		m_start = System.currentTimeMillis();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (m_init) GearsPlacer.getInstance().setPower(-0.45);
		else m_init = true; 
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !GearsPlacer.getInstance().isClosed() || System.currentTimeMillis() - m_start >= 1000;
	}

	// Called once after isFinished returns true
	protected void end() {
		GearsPlacer.getInstance().setPower(0);
		m_init = false;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
