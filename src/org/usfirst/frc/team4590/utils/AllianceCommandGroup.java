package org.usfirst.frc.team4590.utils;

import java.util.Vector;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public abstract class AllianceCommandGroup extends CommandGroup implements AllianceDependable{
	
	private static final String ENTRY_CLASS = "edu.wpi.first.wpilibj.command.CommandGroup.Entry";
	private Vector m_commandsRef;
	
	public AllianceCommandGroup(){
		super();
		if (m_commandsRef == null)
			m_commandsRef = (Vector) ReflectionHelper.getPrivateField(this, CommandGroup.class, "m_commands");
	}
	
	public void initAlliance(){
		for (Object cur : m_commandsRef){
			Class entryClass = null;
			
				entryClass = CommandGroup.class.getDeclaredClasses()[0];
			System.out.println(entryClass.getName());
			Command comm = (Command) ReflectionHelper.getPrivateField(cur, entryClass, "m_command");
			ReflectionHelper.setPrivateField(comm, Command.class, null, "m_parent");
		}
		
		ReflectionHelper.setPrivateField(this, Command.class, false, "m_locked");
		m_commandsRef.clear();
		m_commandsRef = new Vector();
		ReflectionHelper.setPrivateField(this, CommandGroup.class, m_commandsRef, "m_commands");
		if (MatchData.getInstance().getAlliance() != null){
			switch (MatchData.getInstance().getAlliance()){
			case BLUE:
				onBlue();
				break;
			case RED:
				onRed();
				break;
			}
		}
		onAny();
	}

	@Override
	public void onAny() {	
	
	}
}
