package org.usfirst.frc.team4590.utils;

import static org.usfirst.frc.team4590.robot.RobotMap.CHASSIS_TALON_LEFT_FRONT;
import static org.usfirst.frc.team4590.robot.RobotMap.CHASSIS_TALON_LEFT_MIDLE;
import static org.usfirst.frc.team4590.robot.RobotMap.CHASSIS_TALON_LEFT_REAR;
import static org.usfirst.frc.team4590.robot.RobotMap.CHASSIS_TALON_RIGHT_FRONT;
import static org.usfirst.frc.team4590.robot.RobotMap.CHASSIS_TALON_RIGHT_MIDLE;
import static org.usfirst.frc.team4590.robot.RobotMap.CHASSIS_TALON_RIGHT_REAR;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.robot.subsystems.Shifts;
import org.usfirst.frc.team4590.robot.subsystems.Shifts.ShifterState;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.SpeedController;
public class ThreeCIMShifter {

	
	private final SpeedController m_leftGetter;
	private final SpeedController m_rightGetter;

	private final SpeedRobotDrive m_robotDrive;
	private final SpeedRobotDrive m_slaveRobotDrive;

	private PIDGetter m_leftEnc;
	private PIDGetter m_rightEnc;
	
	
	private static final boolean LEFT_INVERTED = false;
	private static final boolean RIGHT_INVERTED = false;

	
	public CANTalon m_leftFront, 
					m_leftRear, 
					m_rightFront, 
					m_rightRear, 
					m_leftMiddle, 
					m_rightMiddle;
	
	private class SpeedRobotDrive extends RobotDrive{

		private boolean m_speedMode;
		private PIDSlave m_leftSlave;
		private PIDSlave m_rightSlave;
		
		private double kP = 0.05, kI = 0.01, kD = 0;
		
		
		
		public SpeedRobotDrive(SpeedController frontLeftMotor, SpeedController rearLeftMotor,
				SpeedController frontRightMotor, SpeedController rearRightMotor) {
			super(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
			initSlaves();
		}
		
		public SpeedRobotDrive(SpeedController leftMotor, SpeedController rightMotor){
			super(leftMotor, rightMotor);
			initSlaves();
		}
		
		private void initSlaves(){
			m_leftSlave = new PIDSlave(kP, kI, kD, m_leftEnc);
			m_rightSlave = new PIDSlave(kP, kI, kD, m_rightEnc);
			
			m_leftSlave.setSetpoint(0);
			m_rightSlave.setSetpoint(0);
			
			m_leftSlave.setPIDSourceType(PIDSourceType.kDisplacement);
			m_rightSlave.setPIDSourceType(PIDSourceType.kDisplacement);
			
			m_leftSlave.setOutputRange(-5330, 5330);
			m_leftSlave.setInputRange(-5330, 5330);

			m_rightSlave.setOutputRange(-5330, 5330);
			m_rightSlave.setInputRange(-5330, 5330);
						
		}
		
		public void setControlMode(boolean isSpeed){
			m_speedMode = isSpeed;
			if (isSpeed){
				m_leftSlave.reset();
				m_rightSlave.reset();
	
				Chassis.getInstance().resetEncoders();
				
				m_leftSlave.enable();
				m_rightSlave.enable();
			} else {
				m_leftSlave.disable();
				m_rightSlave.disable();
			}
		}
	
		public boolean getControlMode(){ return m_speedMode; }
		
		public void setLeftRightMotorOutputs(double left, double right){
			if (m_speedMode){
				double maxRpm = 5330.0 / (Shifts.getInstance().isOpened() ? 4.17 : 11.03);
			
				
				m_leftSlave.setSetpoint(maxRpm * left);
				
				m_rightSlave.setSetpoint(maxRpm * right);
			
				super.setLeftRightMotorOutputs((m_leftSlave.getValue() / maxRpm) + left,
						(m_rightSlave.getValue() / maxRpm) + right);
			
			} else {
				super.setLeftRightMotorOutputs(left, right);
			}
		}
		
		
	}
	
	public ThreeCIMShifter(PIDGetter leftEncoder, PIDGetter rightEncoder) {
		m_leftFront = new CANTalon(CHASSIS_TALON_LEFT_FRONT);
		m_leftRear = new CANTalon(CHASSIS_TALON_LEFT_REAR);
		m_leftMiddle = new CANTalon(CHASSIS_TALON_LEFT_MIDLE);
		
		m_rightFront = new CANTalon(CHASSIS_TALON_RIGHT_FRONT);
		m_rightRear = new CANTalon(CHASSIS_TALON_RIGHT_REAR);
		m_rightMiddle = new CANTalon(CHASSIS_TALON_RIGHT_MIDLE);
		
		m_leftEnc = leftEncoder;
		m_rightEnc = rightEncoder;
		
		m_robotDrive = new SpeedRobotDrive(m_leftFront, m_leftGetter = m_leftRear, m_rightFront, m_rightGetter = m_rightRear);

		m_slaveRobotDrive = new SpeedRobotDrive(m_leftMiddle, m_rightMiddle);

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
		m_robotDrive.tankDrive(left, right, false);
		m_slaveRobotDrive.tankDrive(left, right, false);

	}

	public void arcadeDrive(double forward, double side) {
		m_robotDrive.arcadeDrive(forward, side, false);
		m_slaveRobotDrive.arcadeDrive(forward, side, false);
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

	public void setControlMode(boolean isSpeed) {
		m_robotDrive.setControlMode(isSpeed);
		m_slaveRobotDrive.setControlMode(isSpeed);
	}

	public boolean getControlMode() {
		return m_robotDrive.getControlMode();
	}
}
