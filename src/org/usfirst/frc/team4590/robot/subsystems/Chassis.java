
package org.usfirst.frc.team4590.robot.subsystems;

import static org.usfirst.frc.team4590.robot.RobotMap.*;

import org.usfirst.frc.team4590.utils.ThreeCimShifter;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import com.kauailabs.navx.frc.AHRS;

public class Chassis extends Subsystem {
	
	private Encoder encLeft, encRight;
	private AHRS ahrs;
	private ThreeCimShifter drive;
	private static Chassis instance;
	private Chassis(){
		encLeft 	= new Encoder(CHASSIS_ENCODER_LEFT_A, CHASSIS_ENCODER_LEFT_B);
		encRight 	= new Encoder(CHASSIS_ENCODER_RIGHT_A, CHASSIS_ENCODER_RIGHT_B);
		ahrs = new AHRS(SPI.Port.kMXP);
		drive = new ThreeCimShifter();
	}
	
	public static final void init(){
		instance = new Chassis();
	}
	
	public static final Chassis getInstance(){
		return instance;
	}
	

	
	//getters for sensors
	public double getDistance(){
		return (encLeft.getDistance() + encRight.getDistance())/2;
	}
	
	public double getSpeed(){
		return (encLeft.getRate() + encRight.getRate())/2;
	}
	
	public double getAngle(){
		return ahrs.getAngle();
	}
	
	public double getAccel(char axis){
		switch (axis){
		case 'x':
			return ahrs.getWorldLinearAccelX();
		case 'y':
			return ahrs.getWorldLinearAccelY();
		case 'z':
			return ahrs.getWorldLinearAccelZ();
		}
		System.out.println("Chassis::getAccel - RIP");
		return -1;
	}
	
	//reset methods for sensors
	public void resetEncoders(){
		encLeft.reset();
		encRight.reset();
	}
	
	public void resetAHRS(){
		ahrs.reset();
	}
	
	//drives
	public void tankDrive(double left, double right) {
		drive.tankDrive(left, right);
	}

	public void arcadeDrive(double forward, double side) {
		drive.arcadeDrive(forward, side);
	}
	
	@Override
	protected void initDefaultCommand() {
		
	}
    
}

