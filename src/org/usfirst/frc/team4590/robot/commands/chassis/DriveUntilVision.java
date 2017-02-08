package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.utils.CameraIndex;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

/**
 *
 */
public class DriveUntilVision extends Command {
	
	private boolean direction;
	
	private boolean netUpdated;
	
	private double visionInput = -2;

	private double power;
	
	public DriveUntilVision(boolean direction, double power) {
         requires(Chassis.getInstance());
         this.direction = direction;
         this.power = power;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	CameraIndex.SIDE.set();
    }

    protected void execute() {
    	if (!(netUpdated = NetworkTable.getTable("vision").getBoolean("camera_updated", false))) return;
    	visionInput = NetworkTable.getTable("vision").getNumber("elevatorX", -2.0);
    	Chassis.getInstance().arcadeDrive((direction ? 1 : -1) * power, 0);

    	
    	
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Net Updated: " + netUpdated + "    Vision Input: " + visionInput);
    	return netUpdated && visionInput != -2; 
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
