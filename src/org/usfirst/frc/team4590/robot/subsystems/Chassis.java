
package org.usfirst.frc.team4590.robot.subsystems;

import static org.usfirst.frc.team4590.robot.RobotMap.CHASSIS_ENCODER_LEFT_A;
import static org.usfirst.frc.team4590.robot.RobotMap.CHASSIS_ENCODER_LEFT_B;
import static org.usfirst.frc.team4590.robot.RobotMap.CHASSIS_ENCODER_RIGHT_A;
import static org.usfirst.frc.team4590.robot.RobotMap.CHASSIS_ENCODER_RIGHT_B;

import org.usfirst.frc.team4590.robot.commands.chassis.ArcadeDriveByJoystick;
import org.usfirst.frc.team4590.utils.ThreeCIMShifter;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The main robot frame. Its in charge of the wheels, driving, AHRS and
 * encoders.
 *
 */
public class Chassis extends PIDSubsystem {
	

	private static double kP = 0, kI = 0, kD = 0;
	private Encoder encLeft, encRight;
	private AHRS ahrs;
	private ThreeCIMShifter drive;
	private boolean arcadeMode = true;
	private static Chassis instance;

	private Chassis() {
		super("Chassis", kP, kI, kD);
		encLeft = new Encoder(CHASSIS_ENCODER_LEFT_A, CHASSIS_ENCODER_LEFT_B);
		encRight = new Encoder(CHASSIS_ENCODER_RIGHT_A, CHASSIS_ENCODER_RIGHT_B);
		ahrs = new AHRS(SPI.Port.kMXP);
		drive = new ThreeCIMShifter();
		SmartDashboard.putNumber("Chassis__PID(P)", kP);
		SmartDashboard.putNumber("Chassis__PID(I)", kI);
		SmartDashboard.putNumber("Chassis__PID(D)", kD);
		LiveWindow.addSensor("Chassis", "Encoder left", encLeft);
		LiveWindow.addSensor("Chassis", "Encoder right", encRight);
		LiveWindow.addSensor("Chassis", "AHRS", ahrs);
	}

	public static final void init() {
		instance = new Chassis();
	}

	public static final Chassis getInstance() {
		return instance;
	}

	// getters for sensors
	public double getDistance() {
		return (encLeft.getDistance() - encRight.getDistance()) / 2;
	}

	public double getSpeed() {
		return (encLeft.getRate() - encRight.getRate()) / 2;
	}
	
	public double getSpeedL() {
		return - encRight.getRate();
	}
	
	public double getSpeedR() {
		return encLeft.getRate();
	}

	public double getAngle() {
		return ahrs.getAngle();
	}

	public double getAccel(char axis) {
		switch (axis) {
		case 'x':
			return ahrs.getWorldLinearAccelX();
		case 'y':
			return ahrs.getWorldLinearAccelY();
		case 'z':
			return ahrs.getWorldLinearAccelZ();
		}
		System.out.println("Chassis::getAccel - RIP");
		return -1;
	}

	// reset methods for sensors
	public void resetEncoders() {
		encLeft.reset();
		encRight.reset();
	}

	public void resetAHRS() {
		ahrs.reset();
	}

	// drives
	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}

	public void arcadeDrive(double forward, double side) {
		drive.arcadeDrive((arcadeMode ? 1 : -1) * forward, side);
	}

	// displays status
	public void status() {
		SmartDashboard.putNumber("CHASSIS::Power", drive.getPower());
		SmartDashboard.putNumber("CHASSIS::Speed", getSpeed());
		SmartDashboard.putNumber("CHASSIS::Angle", getAngle());
		SmartDashboard.putNumber("CHASSIS::Distance", getDistance());
		kP = SmartDashboard.getNumber("Chassis__PID(P)", kP);
		kI = SmartDashboard.getNumber("Chassis__PID(I)", kI);
		kD = SmartDashboard.getNumber("Chassis__PID(D)", kD);
	 	SmartDashboard.putNumber("x accel", Chassis.getInstance().getAccel('x'));
    	SmartDashboard.putNumber("y accel", Chassis.getInstance().getAccel('y'));
    	SmartDashboard.putNumber("z accel", Chassis.getInstance().getAccel('z'));
		getPIDController().setPID(kP, kI, kD);
		/*
		 * for debugging purposes
		 * 
		 * SmartDashboard.putNumber("CHASSIS::Left encoder",
		 * encLeft.getDistance()); SmartDashboard.putNumber(
		 * "CHASSIS::Right encoder", encRight.getDistance());
		 * SmartDashboard.putNumber("CHASSIS::Left CIM Power",
		 * drive.getPowerLeft()); SmartDashboard.putNumber(
		 * "CHASSIS::Right CIM Power", drive.getPowerRight());
		 */
	}

	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new ArcadeDriveByJoystick());
	}

	@Override
	protected double returnPIDInput() {
		return getAngle();
	}

	@Override
	protected void usePIDOutput(double output) {
		arcadeDrive(0, output);
	}

	public void toggleArcade() {
		arcadeMode = !arcadeMode;
	}

}
