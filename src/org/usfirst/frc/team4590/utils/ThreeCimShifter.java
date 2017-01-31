package org.usfirst.frc.team4590.utils;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.VictorSP;
import static org.usfirst.frc.team4590.robot.RobotMap.*;
public class ThreeCimShifter {

	
	private final SpeedController m_leftGetter;
	private final SpeedController m_rightGetter;

	private final RobotDrive m_robotDrive;
	private final RobotDrive m_slaveRobotDrive;

	private static final boolean LEFT_INVERTED = false;
	private static final boolean RIGHT_INVERTED = false;

	//GUY: DO NOT CHANGE
	public TalonSRX leftFront, leftRear, rightFront, rightRear, leftMiddle, rightMiddle;
	
	public ThreeCimShifter() {
		leftFront = new TalonSRX(CHASSIS_TALON_LEFT_FRONT);
		leftRear = new TalonSRX(CHASSIS_TALON_LEFT_REAR);
		leftMiddle = new TalonSRX(CHASSIS_TALON_LEFT_MIDLE);
		
		rightFront = new TalonSRX(CHASSIS_TALON_RIGHT_FRONT);
		rightRear = new TalonSRX(CHASSIS_TALON_RIGHT_REAR);
		rightMiddle = new TalonSRX(CHASSIS_TALON_RIGHT_MIDLE);
		
		m_robotDrive = new RobotDrive(leftFront, m_leftGetter = leftRear, rightFront, m_rightGetter = rightRear);

		m_slaveRobotDrive = new RobotDrive(leftMiddle, rightMiddle);

		m_robotDrive.setInvertedMotor(MotorType.kFrontLeft, LEFT_INVERTED);
		m_robotDrive.setInvertedMotor(MotorType.kRearLeft, LEFT_INVERTED);

		// m_slaveRobotDrive.setInvertedMotor(MotorType.kRearLeft,
		// LEFT_INVERTED);

		m_robotDrive.setInvertedMotor(MotorType.kFrontRight, RIGHT_INVERTED);
		m_robotDrive.setInvertedMotor(MotorType.kRearRight, RIGHT_INVERTED);

		// m_slaveRobotDrive.setInvertedMotor(MotorType.kRearRight,
		// RIGHT_INVERTED);
	}

	public void tankDrive(double left, double right) {
		m_robotDrive.tankDrive(left, right);
		m_slaveRobotDrive.tankDrive(left, right);

	}

	public void arcadeDrive(double forward, double side) {
		m_robotDrive.arcadeDrive(forward, side);
		m_slaveRobotDrive.arcadeDrive(forward, side);
	}

	public double getPowerLeft() {
		return m_leftGetter.get();
	}

	public double getPowerRight() {
		return m_rightGetter.get();
	}
	
	public double getPower(){
		return (getPowerRight() + getPowerLeft())/2;
	}
}
