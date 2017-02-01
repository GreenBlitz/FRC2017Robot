package org.usfirst.frc.team4590.robot.subsystems;

import static org.usfirst.frc.team4590.robot.RobotMap.SHOOTER_ENCODER_A;
import static org.usfirst.frc.team4590.robot.RobotMap.SHOOTER_ENCODER_B;
import static org.usfirst.frc.team4590.robot.RobotMap.SHOOTER_TALON_A;
import static org.usfirst.frc.team4590.robot.RobotMap.SHOOTER_TALON_B;

import com.ctre.CANTalon;
import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class Shooter extends PIDSubsystem {
	
	private static final double kP = 0, kI = 0 ,kD = 0;
	
	private CANTalon talon1, talon2;
	private Encoder enc;
	private static Shooter instance;
    // Initialize your subsystem here
    private Shooter() {
    	super("Shooter",kP,kI,kD);
    	
    	talon1 = new CANTalon(SHOOTER_TALON_A);
    	talon2 = new CANTalon(SHOOTER_TALON_B);
    	
    	enc = new Encoder(SHOOTER_ENCODER_A, SHOOTER_ENCODER_B);
    	
    	talon1.changeControlMode(TalonControlMode.Speed);
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
	
	public void status(){
		SmartDashboard.putNumber("SHOOTER::Power", getSpeed());
		SmartDashboard.putNumber("SHOOTER::Distance", getDistance());
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
