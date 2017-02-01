package org.usfirst.frc.team4590.robot.commands.shooter;

import org.usfirst.frc.team4590.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterSetPower extends Command {
	
	private double speed, currentSpeed, accelCounter;
    public ShooterSetPower(double speed) {
        // Use requires() here to declare subsystem dependencies
        requires(Shooter.getInstance());
        this.speed = speed;
        this.currentSpeed = speed/10;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	accelCounter = 1;
    	Shooter.getInstance().setSetpoint(speed);
    	Shooter.getInstance().enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
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
