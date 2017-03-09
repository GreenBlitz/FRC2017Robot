package org.usfirst.frc.team4590.utils;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class PIDSlave implements PIDOutput, PIDSource{
	
	private PIDController m_controller;
	
	private double m_value;

	private PIDSourceType m_sourceType = PIDSourceType.kDisplacement;
	
	private PIDGetter m_getter;
	
	public PIDSlave(double kP, double kI, double kD, PIDGetter getter){
		m_controller = new PIDController(kP, kI, kD, this, this);
	}
	
	public void enable(){
		m_controller.enable();
	}
	
	public void disable(){
		m_controller.disable();
	}
	
	public void reset(){
		m_controller.reset();
	}
	
	public double getValue(){
		return m_value;
	}

	public void setSetpoint(double setpoint){
		m_controller.setSetpoint(setpoint);
	}
	
	public void setAbsoluteTolerance(double tol){
		m_controller.setAbsoluteTolerance(tol);
	}
	
	@Override
	public void pidWrite(double output) {
		m_value = output;
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		m_sourceType = pidSource;
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return m_sourceType;
	}

	@Override
	public double pidGet() {
		return m_getter.getInput(m_controller);
	}

	public void setOutputRange(double min, double max) {
		m_controller.setOutputRange(min, max);
	}

	public void setInputRange(double min, double max) {
		m_controller.setInputRange(min, max);
	}
	
}