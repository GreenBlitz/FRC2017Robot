package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTillVision extends Command {

	private double visionInput;

	public DriveTillVision() {
		// Use requires() here to declare subsystem dependencies
		requires(Chassis.getInstance());

	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		visionInput = NetworkTable.getTable("vision").getNumber("elevatorX", 0.0);
		SmartDashboard.putNumber("DRIVE TILL VISION:: vision ", visionInput);

		Chassis.getInstance().arcadeDrive(0.4, 0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return visionInput != -2;
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
