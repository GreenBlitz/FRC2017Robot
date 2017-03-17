package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;


public class ArcadeDriveToggle extends Command {

	public ArcadeDriveToggle(){
		requires(Chassis.getInstance());
	}
	
	protected void execute(){
		Chassis.getInstance().toggleArcade();
	}
	
	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}

}
