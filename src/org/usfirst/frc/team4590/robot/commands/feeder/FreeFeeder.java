package org.usfirst.frc.team4590.robot.commands.feeder;

import org.usfirst.frc.team4590.robot.subsystems.Feeder;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class FreeFeeder extends Command {

    public FreeFeeder() {
        // Use requires() here to declare subsystem dependencies
        requires(Feeder.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Feeder.getInstance().setPower(0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
