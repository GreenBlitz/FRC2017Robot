package org.usfirst.frc.team4590.robot.commands.climber;

import org.usfirst.frc.team4590.robot.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class FreeClimber extends Command {

	public FreeClimber(){
		requires(Climber.getInstance());
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
		Climber.getInstance().setPower(0);
	}
	@Override
	protected boolean isFinished() {
				return false;
	}

	@Override
	protected void end() {
				
	}

	@Override
	protected void interrupted() {
				
	}

}
