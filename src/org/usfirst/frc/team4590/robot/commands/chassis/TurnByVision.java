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

public class TurnByVision extends Command implements PIDOutput,PIDSource {
	private static final double Kp = -0.5, Ki = -0.01 , Kd = 0;
	
	private static final double DEFAULT_LAST_VALUE = 0.5;
	
	private double m_lastValue = DEFAULT_LAST_VALUE;
	
	private PIDController turnPID = new PIDController(Kp, Ki, Kd, this, this);

	private static final int TIMES_REQUIRED = 20;
	
	private int m_targetTimes;
	
	private boolean detected;
	
    public TurnByVision() {
       
        
    	requires(Chassis.getInstance());
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	turnPID.setAbsoluteTolerance(0.051);
    	turnPID.setSetpoint(0);
    	turnPID.enable();
    	CameraIndex.GOAL.set();
    	SmartDashboard.putNumber("VisionTurn PID P", turnPID.getP());
    	SmartDashboard.putNumber("VisionTurn PID I", turnPID.getI());
    	SmartDashboard.putNumber("VisionTurn PID D", turnPID.getD());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("NETWORK TABLES", NetworkTable.getTable("vision").getNumber("goalX", -2.0));
    	


    	
    	turnPID.setPID(	SmartDashboard.getNumber("VisionTurn PID P", 0.0),
    					SmartDashboard.getNumber("VisionTurn PID I", 0.0),
    					SmartDashboard.getNumber("VisionTurn PID D", 0.0));
    	
    	
    	if (NetworkTable.getTable("vision").getNumber("goalX", -2.0) == -2 && m_lastValue == DEFAULT_LAST_VALUE){
    		turnPID.setPID(turnPID.getP(), 0, turnPID.getD());
    	} else if (turnPID.getI() == 0){
    		turnPID.setPID(turnPID.getP(), Ki, turnPID.getD());
    	}
    	SmartDashboard.putNumber("VisionTurn PID P", turnPID.getP());
    	SmartDashboard.putNumber("VisionTurn PID I", turnPID.getI());
    	SmartDashboard.putNumber("VisionTurn PID D", turnPID.getD());
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	m_targetTimes = (Math.abs(pidGet()) < 0.05) ? m_targetTimes + 1 : 0;
    	return m_targetTimes >= TIMES_REQUIRED;
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
		boolean bool = NetworkTable.getTable("vision").getNumber("goalX", -2.0) == -2;
		
		System.out.println("Current PID Value: " + output);
		
		double power = (output > 0) ? Math.max(output, 0.3) : Math.min(output, -0.3);
		SmartDashboard.putNumber("TURN BY VISION: output", power);

		Chassis.getInstance().tankDrive(power, - power);
		
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
		double ret = pidGet_();
		System.out.println(ret);
		return -ret;
	}
	
	private double pidGet_() {
		if (NetworkTable.getTable("vision").getNumber("goalX", -2.0) == -2) return m_lastValue;
		return m_lastValue = NetworkTable.getTable("vision").getNumber("goalX", -2.0);
	}
	
	

}
