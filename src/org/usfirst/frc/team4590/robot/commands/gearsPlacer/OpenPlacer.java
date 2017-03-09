package org.usfirst.frc.team4590.robot.commands.gearsPlacer;

import org.usfirst.frc.team4590.robot.subsystems.GearsPlacer;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class OpenPlacer extends Command {
	
	private long m_timeStarted;
	private long m_waitTimeout;
	
	public OpenPlacer(){
		this(-1);
	}
	
	public OpenPlacer(long timeoutMili) {
		m_waitTimeout = timeoutMili;
		requires(GearsPlacer.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		m_timeStarted = System.currentTimeMillis();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//SmartDashboard.putBoolean("GEARS PLACER:: open ", true);
		GearsPlacer.getInstance().setPower(0.4586);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return m_waitTimeout != -1 && System.currentTimeMillis() - m_timeStarted >= m_waitTimeout;
		
	}

	// Called once after isFinished returns true
	protected void end() {
		//SmartDashboard.putBoolean("GEARS PLACER:: open ", false);
		GearsPlacer.getInstance().setPower(0);
		throw new RuntimeException("test2");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
