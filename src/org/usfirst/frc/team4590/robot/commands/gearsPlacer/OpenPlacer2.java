package org.usfirst.frc.team4590.robot.commands.gearsPlacer;

import org.usfirst.frc.team4590.robot.subsystems.GearsPlacer;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenPlacer2 extends Command {
	
	private boolean m_init = false;
	
	public OpenPlacer2() {
		requires(GearsPlacer.getInstance());
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (m_init) GearsPlacer.getInstance().setPower(0.45);
		else m_init = true; 
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return !GearsPlacer.getInstance().isOpen();
	}

	// Called once after isFinished returns true
	protected void end() {
		GearsPlacer.getInstance().setPower(0);
	}


}
