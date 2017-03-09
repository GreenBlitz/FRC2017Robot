package org.usfirst.frc.team4590.robot.subsystems;

import static org.usfirst.frc.team4590.robot.RobotMap.GEARS_SWITCH_A;
import static org.usfirst.frc.team4590.robot.RobotMap.GEARS_SWITCH_B;
import static org.usfirst.frc.team4590.robot.RobotMap.GEARS_TALON;

import org.usfirst.frc.team4590.robot.commands.gearsPlacer.FreeGearsPlacer;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearsPlacer extends Subsystem {

	private CANTalon m_talon;
	private DigitalInput m_switchClose;
	private DigitalInput m_switchOpen;
	// private DigitalInput switchIsClosed;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private GearsPlacer() {
		m_talon = new CANTalon(GEARS_TALON);
		m_switchClose = new DigitalInput(GEARS_SWITCH_A);
		m_switchOpen = new DigitalInput(GEARS_SWITCH_B);
	}

	public static final void init() {
		instance = new GearsPlacer();
	}

	public static final GearsPlacer getInstance() {
		return instance;
	}

	private static GearsPlacer instance;

	// getters for sensors
	public boolean isClosed() {
		return m_switchClose.get();
	}
	
	public boolean isOpen() {
		return m_switchOpen.get();
	}

	// motor methods
	public void setPower(double speed) {
		m_talon.set(speed);
	}

	public void status() {
		SmartDashboard.putBoolean("GEARS PLACER::Switch1 state", m_switchOpen.get());
		SmartDashboard.putBoolean("GEARS PLACER::Switch2 state", m_switchClose.get());
		SmartDashboard.putNumber("GEARS PLACER::Power", m_talon.get());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new FreeGearsPlacer());
	}
}
