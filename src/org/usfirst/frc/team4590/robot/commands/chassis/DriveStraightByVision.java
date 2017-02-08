package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.utils.CameraIndex;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveStraightByVision extends Command implements PIDOutput,PIDSource {
	private static final double Kp = 0.7, Ki = 0 , Kd = 0;
	
	
	private PIDController turnPID = new PIDController(Kp, Ki, Kd, this, this);
	private int times_on_target = 0;

    public DriveStraightByVision() {
       
        
    	requires(Chassis.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	turnPID.setAbsoluteTolerance(0.051);
    	turnPID.setSetpoint(0);
    	turnPID.enable();
    	CameraIndex.LIFT.set();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("NETWORK TABLES", NetworkTable.getTable("vision").getNumber("elevatorX", -2.0));
    	
    	

    	
    	turnPID.setPID(	SmartDashboard.getNumber("VisionTurn PID P", 0.0),
    					SmartDashboard.getNumber("VisionTurn PID I", 0.0),
    					SmartDashboard.getNumber("VisionTurn PID D", 0.0));
    	
    	SmartDashboard.putNumber("VisionTurn PID P", turnPID.getP());
    	SmartDashboard.putNumber("VisionTurn PID I", turnPID.getI());
    	SmartDashboard.putNumber("VisionTurn PID D", turnPID.getD());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	SmartDashboard.putNumber("x accel", Chassis.getInstance().getAccel('x'));
    	SmartDashboard.putNumber("y accel", Chassis.getInstance().getAccel('y'));
    	SmartDashboard.putNumber("z accel", Chassis.getInstance().getAccel('z'));
    	return Chassis.getInstance().getAccel('x') > 2;// (times_on_target += turnPID.onTarget() ? 1 : - times_on_target) >= 20;
    	//return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	turnPID.reset();
    	turnPID.disable();
    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }

	@Override
	public void pidWrite(double output) {
		if(output > 0.355) output = 0.355;
		if(output < -0.355) output = -0.355;
		if(output > 0 && output < 0.31) output = 0.31;
		if(output < 0 && output > -0.31) output = -0.31;
		
		SmartDashboard.putNumber("TURN BY VISION: output", output);
		
		Chassis.getInstance().arcadeDrive(0.445900, output);
		
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
	}
	
	@Override
	public PIDSourceType getPIDSourceType() {
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		if (NetworkTable.getTable("vision").getNumber("elevatorX", -2.0) == -2) return 0;
		return NetworkTable.getTable("vision").getNumber("elevatorX", -2.0);
	}
	
	

}
