package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Makes the robot move forward forever while adjusting itself a light reflector
 * the camera picks.
 */
public class DriveStraightByVision extends Command implements PIDSource, PIDOutput {

	private NetworkTable visionTable;
	private PIDController visionPID;
	private static final double kP = 0, kI = 0, kD = 0;

	public DriveStraightByVision() {
		// Use requires() here to declare subsystem dependencies
		requires(Chassis.getInstance());
		visionPID = new PIDController(kP, kI, kD, this, this);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		visionTable = NetworkTable.getTable("vision");
		visionPID.setAbsoluteTolerance(0.05);
		visionPID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		SmartDashboard.putNumber("Drive by vision:: Table", visionTable.getNumber("elevatorX", 0.0));
		SmartDashboard.putData("DriveByVisionPID", visionPID);

		SmartDashboard.putNumber("Drive by vision:: error", visionPID.getError());

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}

	@Override
	public void pidWrite(double output) {

		SmartDashboard.putNumber("Drive by vision:: output", output);
		Chassis.getInstance().arcadeDrive(1, output);
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {

	}

	@Override
	public PIDSourceType getPIDSourceType() {

		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {

		return NetworkTable.getTable("vision").getNumber("elevatorX", 0.0);
	}
}
