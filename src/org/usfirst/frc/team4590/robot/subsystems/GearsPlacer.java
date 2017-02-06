package org.usfirst.frc.team4590.robot.subsystems;

import static org.usfirst.frc.team4590.robot.RobotMap.GEARS_SWITCH_A;
import static org.usfirst.frc.team4590.robot.RobotMap.GEARS_SWITCH_B;
import static org.usfirst.frc.team4590.robot.RobotMap.GEARS_TALON;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearsPlacer extends Subsystem {

	private CANTalon talon;
	private DigitalInput switchIsClosed, switchIsOpen;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private GearsPlacer() {
		talon = new CANTalon(GEARS_TALON);
		switchIsClosed = new DigitalInput(GEARS_SWITCH_A);
		switchIsOpen = new DigitalInput(GEARS_SWITCH_B);
	}

	public static final void init() {
		instance = new GearsPlacer();
	}

	public static final GearsPlacer getInstance() {
		return instance;
	}

	private static GearsPlacer instance;

	// getters for sensors
	public boolean getSwitchIsCloseState() {
		return switchIsClosed.get();
	}

	public boolean getSwitchIsOpenState() {
		return switchIsOpen.get();
	}

	// motor methods
	public void setPower(double speed) {
		talon.set(speed);
	}

	public void status() {
		SmartDashboard.putBoolean("GEARS PLACER::Switch1 state", switchIsClosed.get());
		SmartDashboard.putBoolean("GEARS PLACER::Switch2 state", switchIsOpen.get());
		SmartDashboard.putNumber("GEARS PLACER::Power", talon.get());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
