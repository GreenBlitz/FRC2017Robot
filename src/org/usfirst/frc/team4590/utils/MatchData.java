package org.usfirst.frc.team4590.utils;

import org.usfirst.frc.team4590.robot.commands.auto.HopperAutoCurrent;
import org.usfirst.frc.team4590.robot.commands.auto.ShootAndCrossLine;
import org.usfirst.frc.team4590.robot.commands.chassis.BasicGearsAutoGuyada;
import org.usfirst.frc.team4590.robot.commands.chassis.BasicGearsAutoJoel;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MatchData {
	private static final MatchData instance;
	
	private Command m_autoCommand;
	private SendableChooser<Alliance> m_allianceChooser;
	private SendableChooser<Command> m_autoChooser;

	static {
		instance = new MatchData();
	}
	
	private MatchData(){
		
	}
	
	public static final MatchData getInstance(){ return instance; }
	
	public void robotInit(){
		m_allianceChooser = new SendableChooser<>();
		m_allianceChooser.addDefault("FMS Based", null);
		m_allianceChooser.addObject("Red", Alliance.RED);
		m_allianceChooser.addObject("Blue", Alliance.BLUE);
		
		
		SmartDashboard.putData("Match Alliance", m_allianceChooser);
	
		
		m_autoChooser = new SendableChooser<>();
		m_autoChooser.addDefault("Basic Gears Auto", new BasicGearsAutoJoel());
		m_autoChooser.addObject("Hopper Autonomous", new HopperAutoCurrent());
		m_autoChooser.addObject("Autonomus Gears Left", new BasicGearsAutoGuyada(false));
		m_autoChooser.addObject("Autonomus Gears Right", new BasicGearsAutoGuyada(true));
		m_autoChooser.addObject("Shoot and Cross Auto Line", new ShootAndCrossLine());

		SmartDashboard.putData("Autonomus Command", m_autoChooser);
	}
	
	public void autonomusInit(){
		m_autoCommand = m_autoChooser.getSelected();
		
		if (m_autoCommand != null){
			if (m_autoCommand instanceof AllianceDependable){
				( (AllianceDependable) m_autoCommand ).initAlliance();
			} 
			m_autoCommand.start();
			
		}
	}
	
	public Alliance getAlliance(){
		if (m_allianceChooser.getSelected() == null){
			edu.wpi.first.wpilibj.DriverStation.Alliance alliance = DriverStation.getInstance().getAlliance();
			return alliance == edu.wpi.first.wpilibj.DriverStation.Alliance.Red ? Alliance.RED : Alliance.BLUE;
		}
		
		return m_allianceChooser.getSelected();
	}

	public void teleopInit() {
		if (m_autoCommand != null){
			m_autoCommand.cancel();
		}
	}
}
