package org.usfirst.frc.team4590.robot.commands.fuelCollector;

import org.usfirst.frc.team4590.robot.OI;
import org.usfirst.frc.team4590.robot.commands.chassis.ArcadeDriveByJoystick;
import org.usfirst.frc.team4590.robot.commands.chassis.TankDriveByJoystick;
import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.robot.subsystems.FuelCollector;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CollectFuelBySensor extends Command {

	private long m_lastFound;

	public CollectFuelBySensor() {
		// Use requires() here to declare subsystem dependencies
		requires(FuelCollector.getInstance());
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (FuelCollector.getInstance().foundBall()) m_lastFound = System.currentTimeMillis();
		if (System.currentTimeMillis() - m_lastFound < 100){
			FuelCollector.getInstance().setPower(-1);
			SmartDashboard.putBoolean("waka waka", true);
		} else {  
			FuelCollector.getInstance().setPower(0);
			SmartDashboard.putBoolean("waka waka", false);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		FuelCollector.getInstance().setPower(0);
		SmartDashboard.putBoolean("waka waka", false);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
