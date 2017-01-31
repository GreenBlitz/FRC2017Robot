
package org.usfirst.frc.team4590.robot.subsystems;

import static org.usfirst.frc.team4590.robot.RobotMap.CHASSIS_ENCODER_LEFT_A;
import static org.usfirst.frc.team4590.robot.RobotMap.CHASSIS_ENCODER_LEFT_B;
import static org.usfirst.frc.team4590.robot.RobotMap.CHASSIS_ENCODER_RIGHT_A;
import static org.usfirst.frc.team4590.robot.RobotMap.CHASSIS_ENCODER_RIGHT_B;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;


public class Chassis extends Subsystem {
	
	private Encoder encLeft, encRight;
	
	
	private Chassis(){
		encLeft 	= new Encoder(CHASSIS_ENCODER_LEFT_A, CHASSIS_ENCODER_LEFT_B);
		encRight 	= new Encoder(CHASSIS_ENCODER_RIGHT_A, CHASSIS_ENCODER_RIGHT_B);
	}
	
	public static final void init(){
		instance = new Chassis();
	}
	
	public static final Chassis getInstance(){
		return instance;
	}
	
	private static Chassis instance;
	
	//encoders
	public double getDistance(){
		return (encLeft.getDistance() + encRight.getDistance())/2;
	}
	
	public double getSpeed(){
		return (encLeft.getRate() + encRight.getRate())/2;
	}
	
	public void resetEncoders(){
		encLeft.reset();
		encRight.reset();
	}
	
	
	
	@Override
	protected void initDefaultCommand() {
		
	}
    
}

