package org.usfirst.frc.team4590.robot.subsystems;

import static org.usfirst.frc.team4590.robot.RobotMap.FUEL_COLLECTOR_TALON;

import org.usfirst.frc.team4590.robot.commands.fuelCollector.FreeCollector;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Collects fuel
 */
public class FuelCollector extends Subsystem {

	private CANTalon talon1;

	private static FuelCollector instance;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private FuelCollector() {
		talon1 = new CANTalon(FUEL_COLLECTOR_TALON);
	}

	public static final void init() {
		instance = new FuelCollector();
	}

	public static final FuelCollector getInstance() {
		return instance;
	}

	public void setPower(double power) {
		talon1.set(power);
	}

	public void status() {
		SmartDashboard.putNumber("FUEL COLLECTOR::Power", talon1.get());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new FreeCollector());
	}
}
