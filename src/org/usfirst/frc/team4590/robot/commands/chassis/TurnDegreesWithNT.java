package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.subsystems.Chassis;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TurnDegreesWithNT extends Command implements PIDSource,PIDOutput{
	int PIDcounter = 0;
	//private static final double GyroP = 1, GyroI = -0.1, GyroD = 0.0;
	private static double GyroP = 2.5, GyroI = 0, GyroD = 0.0;
	private static final double PID_CONSTANT = 0;
	
	private PIDController gyroPID = new PIDController(GyroP,GyroI,GyroD,this,this);
	private double toAngle;
	private long m_delay;
	private long m_start;
	private PowerDistributionPanel pdp;
	int timesOnTarget = 0;
	private boolean m_reset;
    public TurnDegreesWithNT(double angle, long milliseconds, boolean reset) {
        // Use requires() here to declare subsystem dependencies
        requires(Chassis.getInstance());
    	toAngle = angle;
    	m_delay = milliseconds;
    	m_reset = reset;
    	pdp = new PowerDistributionPanel();
    }
    
    public TurnDegreesWithNT(double angle, long milliseconds) {
    	this(angle, milliseconds, true);
    }
    
    public TurnDegreesWithNT(double angle) {
    	this(angle, -1, true);
    }
    
    public TurnDegreesWithNT(double angle, boolean reset){
    	this(angle, -1, reset);
    }

    
    // Called just before this Command runs the first time
    protected void initialize() {
    	if (m_reset) Chassis.getInstance().resetAHRS();
    	gyroPID.enable();
    	
    	gyroPID.setAbsoluteTolerance(4.0/360.0);
    	gyroPID.setSetpoint(toAngle/90.0);
    	
    	gyroPID.setOutputRange(-0.6, 0.6);
    	m_start = System.currentTimeMillis();
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	SmartDashboard.putNumber("TurnByAngle - GYRO - getAngle", Chassis.getInstance().getAngle()); // PV
    	SmartDashboard.putNumber("TurnByAngle - PID - setPoint", gyroPID.getSetpoint()); // SP
    	SmartDashboard.putNumber("TurnByAngle - PID - Output", gyroPID.get()); // OP
    	/*
    	SmartDashboard.putNumber("Chassis PID P", gyroPID.getP());
    	SmartDashboard.putNumber("Chassis PID I", gyroPID.getI());
    	SmartDashboard.putNumber("Chassis PID D", gyroPID.getD());
    	
    	gyroPID.setPID(	SmartDashboard.getNumber("Chassis PID P", 0.0),
    					SmartDashboard.getNumber("Chassis PID I", 0.0),
    					SmartDashboard.getNumber("Chassis PID D", 0.0));
    	

    	SmartDashboard.putNumber("Chassis PID Counter", PIDcounter);
    	SmartDashboard.putData("PID Controller",gyroPID);
		*/
    	//pidWrite(gyroPID.get());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		PIDcounter ++;
    	if( gyroPID.onTarget()){
    		timesOnTarget++;
    	}else{
    		timesOnTarget = 0;
    	}
        return timesOnTarget > 5 || (m_delay >= 0 && System.currentTimeMillis() - m_start >= m_delay);
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	gyroPID.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }


	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public PIDSourceType getPIDSourceType() {
		// TODO Auto-generated method stub
		return PIDSourceType.kDisplacement;
	}

	@Override
	public double pidGet() {
		// TODO Auto-generated method stub
		PIDcounter++;
		double currentAngle = Chassis.getInstance().getAngle();
		//SmartDashboard.putNumber("TurnByAngle.pidGet=>currentAngle", currentAngle);
		while (currentAngle >= 180) currentAngle-=360;
		while (currentAngle < -180) currentAngle+=360;
		//SmartDashboard.putNumber("TurnByAngle.pidGet=>currentAngle2", currentAngle);
		//SmartDashboard.putNumber("error_a",gyroPID.getError());
		double ret = (currentAngle/90);
		//SmartDashboard.putNumber("TurnByAngle.pidGet=>return", ret = (currentAngle/90));
		return ret;
	}

	@Override
	public void pidWrite(double output) {
		//if (output != 0.0) SmartDashboard.putNumber("TurnByAngle- PID Write output",output);
		
		double newOutput =  output * 12.0 / Math.min(12.0, pdp.getVoltage());
		Chassis.getInstance().tankDrive(-newOutput, newOutput);

	}
}
