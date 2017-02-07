package org.usfirst.frc.team4590.robot.subsystems;

import static org.usfirst.frc.team4590.robot.RobotMap.SHOOTER_TALON_A;
import static org.usfirst.frc.team4590.robot.RobotMap.SHOOTER_TALON_B;

import org.usfirst.frc.team4590.robot.commands.shooter.FreeShooter;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Shoots the fuel.
 */
public class Shooter extends PIDSubsystem {

	private static double kP = 0, kI = 0, kD = 0;

	private CANTalon talon1, talon2;
	private static Shooter instance;

	// Initialize your subsystem here
	private Shooter() {
		super("Shooter", kP, kI, kD);

		talon1 = new CANTalon(SHOOTER_TALON_A);
		talon2 = new CANTalon(SHOOTER_TALON_B);
		talon2.changeControlMode(TalonControlMode.Follower);
		talon2.set(SHOOTER_TALON_A);
		ResetEncoder();
		SmartDashboard.putNumber("Shooter__PID(P)", kP);
		SmartDashboard.putNumber("Shooter__PID(I)", kI);
		SmartDashboard.putNumber("Shooter__PID(D)", kD);
		// Use these to get going:
		// setSetpoint() - Sets where the PID controller should move the system
		// to
		// enable() - Enables the PID controller.
	}

	private void ResetEncoder() {
		// TODO Auto-generated method stub
		talon1.setPosition(0);

	}

	public static final void init() {
		instance = new Shooter();

	}

	public static final Shooter getInstance() {
		return instance;
	}

	// getters for sensors
	public double getSpeed() {
		return talon1.getSpeed();
	}

	public double getDistance() {
		return talon1.getPosition();
	}

	// motor methods
	public void setPower(double power) {
		if (power > 0.55)
			power = 0.55;
		if (power < -0.55)
			power = -0.55;

		talon1.set(power);
	}

	public void status() {
		SmartDashboard.putNumber("SHOOTER::Power", getSpeed());
		SmartDashboard.putNumber("SHOOTER::Distance", getDistance());
		kP = SmartDashboard.getNumber("Shooter__PID(P)", kP);
		kI = SmartDashboard.getNumber("Shooter__PID(I)", kI);
		kD = SmartDashboard.getNumber("Shooter__PID(D)", kD);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new FreeShooter());
	}

	protected double returnPIDInput() {
		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		return getSpeed();
	}

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		setPower(output);

	}
}
