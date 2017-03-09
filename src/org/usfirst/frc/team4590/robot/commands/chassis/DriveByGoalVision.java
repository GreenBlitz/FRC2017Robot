package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.utils.CameraIndex;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DriveByGoalVision extends Command implements PIDSource, PIDOutput{
	
	private double m_targetHeight;
	private double m_currentHeight = -2;
	private double m_multiplier = DEFAULT_MULTIPLIER;
	private static final double DEFAULT_MULTIPLIER = 1;
	private int m_count;
	private int m_targetCount;
	private PIDController m_controller;
	private PIDSlave m_slave;
	private double m_sideValue = 0;
	private double m_forwardValue = 0;
	
	private double kP = 1, kI = 0.025, kD = 0;
	
	private class PIDSlave implements PIDSource, PIDOutput{
		private PIDController m_controller;
		
		private double kP = 0.7, kI = 0 , kD = 0;
		
		public PIDSlave(){
			m_controller = new PIDController(PIDSlave.this.kP, PIDSlave.this.kI, PIDSlave.this.kD, this, this);
		}
		
		@Override
		public void setPIDSourceType(PIDSourceType pidSource) {
			throw new UnsupportedOperationException("setPIDSourceType() not supported for type " + getClass().getName());
		}

		@Override
		public PIDSourceType getPIDSourceType() {
			return PIDSourceType.kDisplacement;
		}

		@Override
		public double pidGet() {
			double num = NetworkTable.getTable("vision").getNumber("goalX", -2);
			
			return num == -2 ? (m_count++) - m_count : num;
		}
		
		public void enable(){
			PIDSlave.this.m_controller.enable();
		}
		
		public void disable(){
			PIDSlave.this.m_controller.disable();
		}
		
		public void reset(){
			PIDSlave.this.m_controller.reset();
		}

		public void setSetpoint(double setpoint){
			PIDSlave.this.m_controller.setSetpoint(setpoint);
		}
		
		public void setAbsoluteTolerance(double tol){
			m_controller.setAbsoluteTolerance(tol);
		}
		
		@Override
		public void pidWrite(double output) {
			if (output < 0.31 && output > 0) output = 0.31;
			if (output > -0.31 && output < 0) output = -0.31;

			if (output > 0.4) output = 0.4;
			if (output < -0.4) output = -0.4;
			m_sideValue = output;
		}
		
	}
	
	public DriveByGoalVision(double targetHeight){
		requires(Chassis.getInstance());
		m_controller = new PIDController(kP, kI, kD, this, this);
		m_controller.setAbsoluteTolerance(0.02);
		m_controller.setSetpoint(0);
		m_slave = new PIDSlave();
		m_slave.setAbsoluteTolerance(0.02);
		m_slave.setSetpoint(0);
		
		m_targetHeight = targetHeight;
	}
	
	protected void initialize(){
		m_controller.reset();
		m_slave.reset();
		CameraIndex.GOAL.set();
		m_controller.enable();
		m_slave.enable();
		m_count = 0;
		m_targetCount = 0;
		m_forwardValue = 0;
		m_sideValue = 0;
	}
	
	protected void execute(){
		m_currentHeight = NetworkTable.getTable("vision").getNumber("goalHeight", -2);
		//m_multiplier = SmartDashboard.getNumber("m_multiplier", m_multiplier);
		Chassis.getInstance().arcadeDrive(m_forwardValue, m_sideValue);
	}
	
	protected boolean isFinished(){
		m_count = m_currentHeight == -2 ? m_count + 1 : 0;
		m_targetCount += (m_controller.onTarget() ? 1 : -m_targetCount);
		return m_targetCount > 2 || m_count > 5;
	}
	
	protected void end(){
		m_controller.disable();
		m_slave.disable();
		Chassis.getInstance().arcadeDrive(0, 0);
	}

	@Override
	public void pidWrite(double output) {
		//Chassis.getInstance().arcadeDrive(output, 0);
		m_forwardValue = output;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		throw new UnsupportedOperationException("Operation setPIDSourceType() is not supported");
	}

	@Override   
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		if (m_currentHeight == -2) return 0;
		return -((double)(m_targetHeight - m_currentHeight)) / m_targetHeight;
	}
}
