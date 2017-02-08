package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.utils.CameraIndex;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnUntilVision extends Command {

	private double visionInput = -2.0;

	private boolean clockwise;
	
	
	private boolean netUpdated;

	private double power;
	public TurnUntilVision(boolean clockwise, double power) {
		requires(Chassis.getInstance());
		this.clockwise = clockwise;
		this.power = power;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		CameraIndex.LIFT.set();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (!(netUpdated = NetworkTable.getTable("vision").getBoolean("camera_updated", false))) return;
		visionInput = NetworkTable.getTable("vision").getNumber("elevatorX", -2.0);
		SmartDashboard.putNumber("TURN UNTIL VISION: vision ", visionInput);
		Chassis.getInstance().arcadeDrive(0, (clockwise ? -1 : 1) * power);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		
		return (netUpdated && visionInput != -2);
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
