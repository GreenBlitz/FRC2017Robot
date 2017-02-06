package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Drives a given distance in a straight line.
 *
 * @param distance
 *            the distance to drive
 */
public class DriveStraight extends Command implements PIDOutput, PIDSource {

	private int timesOnTarget;

	private double distance;

	private PIDController drivePID;
	private double Kp = 0, Ki = 0, Kd = 0;

	public DriveStraight(double distance) {
		// Use requires() here to declare subsystem dependencies
		requires(Chassis.getInstance());
		this.distance = distance;

		drivePID = new PIDController(Kp, Ki, Kd, this, this);

	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Chassis.getInstance().resetAHRS();
		Chassis.getInstance().resetEncoders();

		drivePID.setAbsoluteTolerance(1);
		drivePID.setSetpoint(distance);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		SmartDashboard.putNumber("Chassis PID P", drivePID.getP());
		SmartDashboard.putNumber("Chassis PID I", drivePID.getI());
		SmartDashboard.putNumber("Chassis PID D", drivePID.getD());

		drivePID.setPID(SmartDashboard.getNumber("Chassis PID P", 0.0), SmartDashboard.getNumber("Chassis PID I", 0.0),
				SmartDashboard.getNumber("Chassis PID D", 0.0));

		SmartDashboard.putData("PID Controller", drivePID);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (drivePID.onTarget()) {
			timesOnTarget++;
		} else {
			timesOnTarget = 0;
		}
		return timesOnTarget > 30;
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
	public void setPIDSourceType(PIDSourceType pidSource) {

	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		return Chassis.getInstance().getDistance();
	}

	@Override
	public void pidWrite(double output) {
		Chassis.getInstance().arcadeDrive(output, 0);
	}
}
