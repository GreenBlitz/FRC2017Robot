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
 * @param m_distance
 *            the distance to drive
 */
public class DriveStraightByDistance extends Command implements PIDOutput, PIDSource {

	private int timesOnTarget;

	private double m_distance;
	
	private double m_angle;
	
	private boolean m_reset;
	
	private PIDController drivePID;
	private double Kp = 0.7, Ki = 0, Kd = 0;
	private double P2 = 2.0 / 90.0;
	/**
	 * Drives a given in straight line.
	 * 
	 * @param distance
	 *            distance to drive.
	 */
	
	public DriveStraightByDistance(double distance, double angle, boolean reset){
		requires(Chassis.getInstance());
		m_distance = distance;
		m_angle = angle;
		m_reset = reset;
		drivePID = new PIDController(Kp, Ki, Kd, this, this);

	}
	
	public DriveStraightByDistance(double distance, double angle){
		this(distance, angle, false);
	}
	public DriveStraightByDistance(double distance) {
		this(distance, 0, true);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if (m_reset) Chassis.getInstance().resetAHRS();
		Chassis.getInstance().resetEncoders();
		
		drivePID.reset();
		drivePID.setAbsoluteTolerance(0.15);
		drivePID.setSetpoint(-m_distance / 319.185);
		drivePID.setOutputRange(-0.6, 0.6);
		drivePID.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		SmartDashboard.putNumber("DriveStraight PID P", drivePID.getP());
		SmartDashboard.putNumber("DriveStraight PID I", drivePID.getI());
		SmartDashboard.putNumber("DriveStraight PID D", drivePID.getD());

		drivePID.setPID(SmartDashboard.getNumber("DriveStraight PID P", 0.0),
				SmartDashboard.getNumber("DriveStraight PID I", 0.0),
				SmartDashboard.getNumber("DriveStraight PID D", 0.0));

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (drivePID.onTarget()) {
			timesOnTarget++;
		} else {
			timesOnTarget = 0;
		}
		return timesOnTarget > 2;
	}

	// Called once after isFinished returns true
	protected void end() {
		drivePID.disable();
		System.out.println("ended");
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
		
		Chassis.getInstance().arcadeAccDrive(-output, P2 * (m_angle - Chassis.getInstance().getAngle()));
	}
}
