package org.usfirst.frc.team4590.robot.subsystems;

import java.util.Random;

import org.usfirst.frc.team4590.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This subsystem is responsible for the gears in the robot. WARNING: power will
 * hurt the robot's battery.
 */
public class Shifts extends Subsystem {

	private static Shifts instance;

	private DoubleSolenoid m_doubleSol;
	
	public static enum ShifterState {
		POWER(DoubleSolenoid.Value.kForward), SPEED(DoubleSolenoid.Value.kReverse);

		private DoubleSolenoid.Value value;

		private ShifterState(DoubleSolenoid.Value _value) {
			value = _value;
		}

		public DoubleSolenoid.Value getValue() {
			return value;
		}
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private Shifts() {
		m_doubleSol = new DoubleSolenoid(RobotMap.BS_DOUBLE_SOL_FORE,RobotMap.BS_DOUBLE_SOL_REV);
		//setState(ShifterState.SPEED);
	}

	public static final void init() {
		instance = new Shifts();

	}

	public static final Shifts getInstance() {
		return instance;
	}

	public void setState(ShifterState state) {
		m_doubleSol.set(state.getValue());

	}

	public void toggleState() {

		// doubleSol1.get().ordinal() => The current index in the enum of the
		// DoubleSolenoid.Value
		// (our indexes are the same) 2 - index = {index = 0: 1 - 0 = 0; index =
		// 1: 1 - 1 = 0; }
		if (m_doubleSol.get().ordinal()!=0)
		setState(ShifterState.values()[2 - m_doubleSol.get().ordinal()]);
		else
			setState(ShifterState.SPEED);
	}
	
	public boolean isOpened(){
		return m_doubleSol.get() == Value.kForward;
	}


	public void status() {
		SmartDashboard.putString("SHIFTS::Gear",
				(m_doubleSol.get() == DoubleSolenoid.Value.kForward) ? "SPEED" : "POWER");
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new AutoGearShifter()); untill we figer sruff
	}
}
