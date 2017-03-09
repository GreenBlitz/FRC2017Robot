package org.usfirst.frc.team4590.robot.commands.shooter;

import org.usfirst.frc.team4590.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterSetSpeed extends Command implements PIDSource, PIDOutput {

	//private static final double TICKS_PER_RPM = 35.842 /**60.39*//*------*//**35.685**/;

	private double m_speed;
	
	private PIDController m_controller;
	
	private double m_lastPower = 0; //2000 rpm => 0.61 speed value (12.5V - 12.6V)
	private static final double RPM2000_POWER = -0.6225;

	public ShooterSetSpeed(double speed) {
		requires(Shooter.getInstance());
		m_speed = speed;
		m_controller = new PIDController(2, 0.003, 0, this, this, 0.025);
		//SmartDashboard.putNumber("Shooter::Speed ", speed);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		m_controller.reset();
		m_controller.setSetpoint(1);
		m_controller.enable();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("I am executing " + ShooterSetSpeed.class.getName());
		Shooter.getInstance().status();
		SmartDashboard.putNumber("Shooter::Speed ", Shooter.getInstance().getSpeed());
		
		
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		m_controller.disable();
		Shooter.getInstance().setPower(0);
	}

	@Override
	public void pidWrite(double output) {
		SmartDashboard.putNumber("Test PID Output", output);
		Shooter.getInstance().setPower(m_lastPower = RPM2000_POWER - output);
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		throw new UnsupportedOperationException("setPIDSourceType is not supported");
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		
		double ret = Shooter.getInstance().getSpeed() / Math.abs(m_speed);
		SmartDashboard.putNumber("PID Input Test", ret);
		return ret;
	}
}
