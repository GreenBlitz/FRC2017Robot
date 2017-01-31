package org.usfirst.frc.team4590.robot.commands.shifter;

import org.usfirst.frc.team4590.robot.subsystems.Shifts;
import static org.usfirst.frc.team4590.robot.subsystems.Shifts.ShifterState.*;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoGearShifter extends Command {
	
	private final double MAX_THRESHOLD = 700.0, 
						 MIN_THRESHOLD = 500.0;
	
	private double velocity;
	
	
	// TODO update everything after we do the chassis
	
    public AutoGearShifter() {
        // Use requires() here to declare subsystem dependencies
        requires(Shifts.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	velocity = Chassis.getInstance().getSpeed();
    	
    	if (velocity > MAX_THRESHOLD){
    		Shifts.getInstance().setState(POWER);
    	}else if (velocity < MIN_THRESHOLD){
    		Shifts.getInstance().setState(SPEED);
    	}
    	
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
    	end();
    }
}
