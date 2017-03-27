package org.usfirst.frc.team4590.robot.commands.shifter;

import org.usfirst.frc.team4590.robot.subsystems.Shifts;
import org.usfirst.frc.team4590.robot.subsystems.Shifts.ShifterState;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Changes the shift to the opposite value. Example: SPEED -> POWER
 */
public class ValveSetState extends Command {

	private ShifterState m_state;
	
	public ValveSetState(ShifterState state) {
		requires(Shifts.getInstance());
		m_state = state;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Shifts.getInstance().setState(m_state);
		SmartDashboard.putBoolean("gears_state", Shifts.getInstance().isOpened());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

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
