package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Turns by given angle (degrees).
 */
public class TurnByAngle extends Command implements PIDSource, PIDOutput {

	int PIDcounter = 0;
	// private static final double GyroP = 1, GyroI = -0.1, GyroD = 0.0;
	private static double GyroP = 1.3597, GyroI = -0.18, GyroD = 0.0;
	private static final double PID_CONSTANT = 0;

	private PIDController gyroPID = new PIDController(GyroP, GyroI, GyroD, this, this);
	private double toAngle;
	int timesOnTarget = 0;

	public TurnByAngle(double angle) {
		// Use requires() here to declare subsystem dependencies
		requires(Chassis.getInstance());
		toAngle = angle;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Chassis.getInstance().resetAHRS();
		gyroPID.enable();

		gyroPID.setAbsoluteTolerance(2.0 / 360.0);
		gyroPID.setSetpoint(toAngle / 90.0);

		gyroPID.setOutputRange(PID_CONSTANT - 1, 1 - PID_CONSTANT);

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		SmartDashboard.putNumber("TurnByAngle - GYRO - getAngle", Chassis.getInstance().getAngle()); // PV
		SmartDashboard.putNumber("TurnByAngle - PID - setPoint", gyroPID.getSetpoint()); // SP
		SmartDashboard.putNumber("TurnByAngle - PID - Output", gyroPID.get()); // OP
		/*
		 * SmartDashboard.putNumber("Chassis PID P", gyroPID.getP());
		 * SmartDashboard.putNumber("Chassis PID I", gyroPID.getI());
		 * SmartDashboard.putNumber("Chassis PID D", gyroPID.getD());
		 * 
		 * gyroPID.setPID( SmartDashboard.getNumber("Chassis PID P", 0.0),
		 * SmartDashboard.getNumber("Chassis PID I", 0.0),
		 * SmartDashboard.getNumber("Chassis PID D", 0.0));
		 * 
		 * 
		 * SmartDashboard.putNumber("Chassis PID Counter", PIDcounter);
		 * SmartDashboard.putData("PID Controller",gyroPID);
		 */
		// pidWrite(gyroPID.get());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		PIDcounter++;
		if (gyroPID.onTarget()) {
			timesOnTarget++;
		} else {
			timesOnTarget = 0;
		}
		return timesOnTarget > 30;
	}

	// Called once after isFinished returns true
	protected void end() {

		gyroPID.disable();
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
		PIDcounter++;
		double currentAngle = Chassis.getInstance().getAngle();
		// SmartDashboard.putNumber("TurnByAngle.pidGet=>currentAngle",
		// currentAngle);
		while (currentAngle >= 180)
			currentAngle -= 360;
		while (currentAngle < -180)
			currentAngle += 360;
		// SmartDashboard.putNumber("TurnByAngle.pidGet=>currentAngle2",
		// currentAngle);
		// SmartDashboard.putNumber("error_a",gyroPID.getError());
		double ret = (currentAngle / 90);
		// SmartDashboard.putNumber("TurnByAngle.pidGet=>return", ret =
		// (currentAngle/90));
		return ret;
	}

	@Override
	public void pidWrite(double output) {
		// if (output != 0.0) SmartDashboard.putNumber("TurnByAngle- PID Write
		// output",output);

		double newOutput = output > 0 ? output + PID_CONSTANT : output - PID_CONSTANT;
		Chassis.getInstance().tankDrive(newOutput, -newOutput);

	}
}
