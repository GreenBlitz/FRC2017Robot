package org.usfirst.frc.team4590.robot.subsystems;

import static org.usfirst.frc.team4590.robot.RobotMap.FEEDER_TALON;
import static org.usfirst.frc.team4590.robot.RobotMap.HELPER_TALON;

import org.usfirst.frc.team4590.robot.commands.feeder.FreeFeeder;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Feeder extends Subsystem {

	private static final double HELPER_MULTIPLIER = -1;
	private CANTalon talon1;
	private CANTalon m_helper;
	// private Encoder enc;
	private static Feeder instance;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Feeder() {
		talon1 = new CANTalon(FEEDER_TALON);
		m_helper = new CANTalon(HELPER_TALON);
		// enc = new Encoder(FEEDER_ENCODER_A, FEEDER_ENCODER_B);
	}

	public static final void init() {
		instance = new Feeder();
	}

	public static final Feeder getInstance() {
		return instance;
	}

	public void setPower(double power) {
		talon1.set(-power);
		m_helper.set(power * HELPER_MULTIPLIER);
	}

	public void status() {
		SmartDashboard.putNumber("FEEDER::Power", talon1.get());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new FreeFeeder());
	}
}
