package org.usfirst.frc.team4590.robot.commands.fuelCollector;

import org.usfirst.frc.team4590.robot.OI;
import org.usfirst.frc.team4590.robot.subsystems.FuelCollector;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CollectFuel extends Command {

	public CollectFuel() {
		// Use requires() here to declare subsystem dependencies
		requires(FuelCollector.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize() {

		SmartDashboard.putBoolean("waka waka", true);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		FuelCollector.getInstance().setPower(OI.getInstance().getSubPOVDown() ? 1 : -1);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		//FuelCollector.getInstance().setPower(0);
		SmartDashboard.putBoolean("waka waka", false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
