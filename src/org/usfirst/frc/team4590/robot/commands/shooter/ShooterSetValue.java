package org.usfirst.frc.team4590.robot.commands.shooter;

import org.usfirst.frc.team4590.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterSetValue extends Command {

	private double speed;

	public ShooterSetValue(double speed) {
		// Use requires() here to declare subsystem dependencies
		requires(Shooter.getInstance());
		this.speed = -speed;
		SmartDashboard.putNumber("Shooter::SetSpeed",0);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Shooter.getInstance().setPower(SmartDashboard.getNumber("Shooter::SetSpeed",0));
		Shooter.getInstance().status();
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Shooter.getInstance().getPIDController().disable();
		Shooter.getInstance().getPIDController().reset();

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
