package org.usfirst.frc.team4590.robot.commands.shooter;

import org.usfirst.frc.team4590.robot.commands.feeder.FeedToShooter;
import org.usfirst.frc.team4590.robot.subsystems.Feeder;
import org.usfirst.frc.team4590.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.hal.PDPJNI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShooterSetSpeedCopy extends Command implements PIDSource, PIDOutput {

	// private static final double TICKS_PER_RPM = 35.842
	// /**60.39*//*------*//**35.685**/;

	private double m_speed;

	private PIDController m_controller;

	private double m_lastPower = 0; // 2000 rpm => 0.61 speed value (12.5V -
									// 12.6V)
	private static final double RPM1925_POWER = -0.675; // -0.5663;// -0.6325;

	private long m_time;

	private PowerDistributionPanel m_pdp;

	public ShooterSetSpeedCopy(double speed) {
		requires(Shooter.getInstance());
		m_speed = speed;
		// m_controller = new PIDController(2.2, 0.008/*0.003*/, 0, this, this,
		// 0.015);
		// SmartDashboard.putNumber("Shooter::Speed ", speed);
		m_pdp = new PowerDistributionPanel();
		m_controller = new PIDController(9.5, 0/* 0.003 */, 0.00005, this, this, 0.015);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		m_time = System.currentTimeMillis();
		m_controller.reset();
		m_controller.setSetpoint(1);
		m_controller.enable();
		m_controller.setPID(m_controller.getP(), 0/* 0.003 */, 0);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println("I am executing " + ShooterSetSpeedCopy.class.getName());
		Shooter.getInstance().status();
		SmartDashboard.putNumber("Shooter::Speed ", Shooter.getInstance().getSpeed());
		if (System.currentTimeMillis() - m_time > 750) {
			m_controller.reset();
			m_controller.setPID(m_controller.getP(), 0.003, 0);
			m_controller.enable();
			m_time += 10000000;
		}
		if (Feeder.getInstance().getCurrentCommand() != null && Feeder.getInstance().getCurrentCommand() instanceof FeedToShooter){
			m_controller.setPID(m_controller.getP(), 0, 0);
		}
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

		double pdp_multi = Math.max(12 / m_pdp.getVoltage(), 1);
		SmartDashboard.putNumber("Power value to motors", pdp_multi * (RPM1925_POWER - output));
		Shooter.getInstance().setPower(pdp_multi * (m_lastPower = RPM1925_POWER - output));
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
		return ret; // return ret * ret * ret * Math.abs(ret);
	}
}
