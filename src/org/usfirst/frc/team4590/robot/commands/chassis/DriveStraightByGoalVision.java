package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.utils.CameraIndex;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class DriveStraightByGoalVision extends Command implements PIDSource, PIDOutput{
	
	private double m_targetHeight;
	private double m_currentHeight = -2;
	private double m_multiplier = DEFAULT_MULTIPLIER;
	private static final double DEFAULT_MULTIPLIER = 1;
	private int m_count;
	private PIDController m_controller;
	private double kP = 1, kI = 0.025, kD = 0;
	
	public DriveStraightByGoalVision(double targetHeight){
		requires(Chassis.getInstance());
		m_controller = new PIDController(kP, kI, kD, this, this);
		m_controller.setAbsoluteTolerance(0.02);
		m_controller.setSetpoint(0);
		m_targetHeight = targetHeight;
	}
	
	protected void initialize(){
		m_controller.reset();
		CameraIndex.GOAL.set();
		m_controller.enable();
		m_count = 0;
	}
	
	protected void execute(){
		m_currentHeight = NetworkTable.getTable("vision").getNumber("goalHeight", -2);
		//m_multiplier = SmartDashboard.getNumber("m_multiplier", m_multiplier);
		
	}
	
	protected boolean isFinished(){
		m_count = m_currentHeight == -2 ? m_count + 1 : 0;
		return m_controller.onTarget() || m_count > 5;
	}
	
	protected void end(){
		m_controller.disable();
		Chassis.getInstance().arcadeDrive(0, 0);
	}

	@Override
	public void pidWrite(double output) {
		Chassis.getInstance().arcadeDrive(output, 0);
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
