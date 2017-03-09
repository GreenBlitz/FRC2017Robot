package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SetDriveMultiplier extends Command {
	
	private double m_multi;
	private boolean m_held;
	
	
	public SetDriveMultiplier(double multi, boolean held){
		m_multi = multi;
		m_held = held;
	}
	
	protected void initialize(){
		if (m_held)
			Chassis.getInstance().setDriveMultiplier(m_multi);
		else if (Chassis.getInstance().getDriveMultiplier() != 1){
			Chassis.getInstance().setDriveMultiplier(1);
		} else {
			Chassis.getInstance().setDriveMultiplier(m_multi);
		}
	}

	protected void execute(){
		m_multi = SmartDashboard.getNumber("Chassis Multiplier", m_multi);
	}
	
	@Override
	protected boolean isFinished() {
		return !m_held;
	}
	
	protected void end(){
		if (m_held){
			Chassis.getInstance().setDriveMultiplier(1);
		}
	}
}
