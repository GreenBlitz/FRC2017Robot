package org.usfirst.frc.team4590.robot.commands.auto;

import org.usfirst.frc.team4590.utils.MatchData;

import edu.wpi.first.wpilibj.command.Command;

public class RunAutoCommand extends Command{

	protected void initialize(){
		MatchData.getInstance().autonomusInit();
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}

}
