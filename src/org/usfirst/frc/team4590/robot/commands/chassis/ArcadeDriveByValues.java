package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.OI;
import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Drives in tank mode: Y axis of each joystick controls its parallel cim (left
 * joystick = left sim, right = right).
 */
public class ArcadeDriveByValues extends Command {
	
	private double m_leftValue;
	private double m_rightValue;
	private long m_timeout;
	private long m_startTime;
	
	public ArcadeDriveByValues(double left, double right) {
		this(left, right, -1);
	}
	
	public ArcadeDriveByValues(double left, double right, long timeout) {
		requires(Chassis.getInstance());
		m_leftValue = left;
		m_rightValue = right;
		m_timeout = timeout;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		m_startTime = System.currentTimeMillis();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("Test");
		Chassis.getInstance().arcadeDrive(m_leftValue, m_rightValue);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return m_timeout != -1 && System.currentTimeMillis() - m_startTime > m_timeout;
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
