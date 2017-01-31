package org.usfirst.frc.team4590.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import static org.usfirst.frc.team4590.robot.RobotMap.*;

import org.usfirst.frc.team4590.robot.RobotMap;
/**
 *
 */
public class Shooter extends PIDSubsystem {
	
	private static final double kP = 0, kI = 0 ,kD = 0;
	
	private TalonSRX talon1, talon2;
	private Encoder enc;
	private static Shooter instance;
    // Initialize your subsystem here
    public Shooter() {
    	super("Shooter",kP,kI,kD);
    	
    	talon1 = new TalonSRX(SHOOTER_TALON_A);
    	talon2 = new TalonSRX(SHOOTER_TALON_B);
    	
    	enc = new Encoder(SHOOTER_ENCODER_A, SHOOTER_ENCODER_B);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public static final void init(){
		instance = new Shooter();
	}
	
	public static final Shooter getInstance(){
		return instance;
	}
	

	
	//getters for sensors
	public double getSpeed(){
		return enc.getRate();
	}
	public double getDistance(){
		return enc.getDistance();
	}
	
	//motor methods
	public void setPower(double power){
		talon1.set(power);
		talon2.set(power);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return 0.0;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}