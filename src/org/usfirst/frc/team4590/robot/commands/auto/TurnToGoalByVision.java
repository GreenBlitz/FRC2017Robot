package org.usfirst.frc.team4590.robot.commands.auto;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.utils.CameraIndex;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class TurnToGoalByVision extends Command implements PIDSource, PIDOutput {
	
	private PIDController m_controller;

	private int m_timesOnTarget;
	
	private double m_lastValue = 0;
	
	private static double kP = 0.7,
						  kI = 0,
						  kD = 0;
	
	public TurnToGoalByVision(){
		requires(Chassis.getInstance());
		m_controller = new PIDController(kP, kI, kD, this, this);
		m_controller.setOutputRange(-0.5, 0.5);
	}
	
	public void initialize(){
		CameraIndex.GOAL.set();
		m_controller.setSetpoint(320 - (NetworkTable.getTable("vision").getNumber("cross_x", 320)) / 640.0);
		m_controller.setAbsoluteTolerance(0.1);
		m_controller.enable();
	}
	
	public void end(){
		m_controller.disable();
		m_controller.reset();
	}
	
	public void execute(){
		m_controller.setSetpoint((320.0 - NetworkTable.getTable("vision").getNumber("cross_x", 320)) / 640.0);
	}
	
	public boolean isFinished(){
		return m_controller.onTarget() ? m_timesOnTarget++ >= 1 : (m_timesOnTarget = 0) == -1;
	}

	@Override
	public void pidWrite(double output) {
		Chassis.getInstance().arcadeAccDrive(0, output);
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		double ret = NetworkTable.getTable("vision").getNumber("goalX", -2);
		if (ret == -2) return m_lastValue;
		return m_lastValue = ret;
	}
	
	
}
