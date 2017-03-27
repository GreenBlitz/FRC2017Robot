package org.usfirst.frc.team4590.robot.commands.feeder;

import org.usfirst.frc.team4590.robot.OI;
import org.usfirst.frc.team4590.robot.subsystems.Feeder;
import org.usfirst.frc.team4590.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FeedToShooter extends Command {

	private double m_multi;
	
	public FeedToShooter(double multi) {
		// Use requires() here to declare subsystem dependencies
		requires(Feeder.getInstance());
		
		m_multi = multi;
	}
	
	public FeedToShooter(){
		this(1);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		//if (Shooter.getInstance().onTarget()) {
			Feeder.getInstance().setPower(OI.getInstance().getSubPOVDown() ? -m_multi : m_multi);
		//}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Feeder.getInstance().setPower(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
