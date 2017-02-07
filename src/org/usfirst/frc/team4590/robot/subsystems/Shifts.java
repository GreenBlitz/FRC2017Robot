package org.usfirst.frc.team4590.robot.subsystems;

import static org.usfirst.frc.team4590.robot.RobotMap.DOUBLE_SOL_1_FORE;
import static org.usfirst.frc.team4590.robot.RobotMap.DOUBLE_SOL_1_REV;
import static org.usfirst.frc.team4590.robot.RobotMap.DOUBLE_SOL_2_FORE;
import static org.usfirst.frc.team4590.robot.RobotMap.DOUBLE_SOL_2_REV;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This subsystem is responsible for the gears in the robot. WARNING: power will
 * hurt the robot's battery.
 */
public class Shifts extends Subsystem {

	private DoubleSolenoid doubleSol1, doubleSol2;
	private static Shifts instance;

	public static enum ShifterState {
		SPEED(DoubleSolenoid.Value.kForward), POWER(DoubleSolenoid.Value.kReverse);

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
		doubleSol1 = new DoubleSolenoid(DOUBLE_SOL_1_FORE, DOUBLE_SOL_1_REV);
		doubleSol2 = new DoubleSolenoid(DOUBLE_SOL_2_FORE, DOUBLE_SOL_2_REV);
		setState(ShifterState.SPEED);
	}

	public static final void init() {
		instance = new Shifts();

	}

	public static final Shifts getInstance() {
		return instance;
	}

	public void setState(ShifterState state) {
		doubleSol1.set(state.getValue());
		doubleSol2.set(state.getValue());
	}

	public void toggleState() {

		// doubleSol1.get().ordinal() => The current index in the enum of the
		// DoubleSolenoid.Value
		// (our indexes are the same) 1 - index = {index = 0: 1 - 0 = 0; index =
		// 1: 1 - 1 = 0; }

		setState(ShifterState.values()[1 - doubleSol1.get().ordinal()]);
	}

	public void status() {
		SmartDashboard.putString("SHIFTS::Gear",
				(doubleSol1.get() == DoubleSolenoid.Value.kForward) ? "SPEED" : "POWER");
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new AutoGearShifter()); untill we figer sruff
	}
}
