package org.usfirst.frc.team4590.robot.subsystems;

import static org.usfirst.frc.team4590.robot.RobotMap.FEEDER_ENCODER_A;
import static org.usfirst.frc.team4590.robot.RobotMap.FEEDER_ENCODER_B;
import static org.usfirst.frc.team4590.robot.RobotMap.FEEDER_TALON;

import org.usfirst.frc.team4590.robot.commands.feeder.FeederDoNothing;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Feeder extends Subsystem {

	private CANTalon talon1;
	private Encoder enc;
	private static Feeder instance;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Feeder() {
		talon1 = new CANTalon(FEEDER_TALON);
		enc = new Encoder(FEEDER_ENCODER_A, FEEDER_ENCODER_B);
	}

	public static final void init() {
		instance = new Feeder();
	}

	public static final Feeder getInstance() {
		return instance;
	}

	public void setPower(double power) {
		talon1.set(power);
	}

	public void status() {
		SmartDashboard.putNumber("FEEDER::Power", talon1.get());
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new FeederDoNothing());
	}
}
