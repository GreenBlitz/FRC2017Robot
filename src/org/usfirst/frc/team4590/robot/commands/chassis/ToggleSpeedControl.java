package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;

public class ToggleSpeedControl extends Command {

	@Override
	protected boolean isFinished() {
		return true;
	}
	
	protected void execute(){
		Chassis.getInstance().toggleSpeedControl();
	}

}
