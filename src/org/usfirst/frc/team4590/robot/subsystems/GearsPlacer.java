package org.usfirst.frc.team4590.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static org.usfirst.frc.team4590.robot.RobotMap.*;
/**
 *
 */
public class GearsPlacer extends Subsystem {
    
	private TalonSRX talon;
	private DigitalInput switch1, switch2;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public GearsPlacer(){
		talon = new TalonSRX(GEARS_TALON);
		switch1 = new DigitalInput(GEARS_SWITCH_A);
		switch2 = new DigitalInput(GEARS_SWITCH_B);
	}
	
	public static final void init(){
		instance = new GearsPlacer();
	}
	
	public static final GearsPlacer getInstance(){
		return instance;
	}
	
	private static GearsPlacer instance;
	
	//getters for sensors
	public boolean getSwitch1State(){
		return switch1.get();
	}
	public boolean getSwitch2State(){
		return switch2.get();
	}
	
	//motor methods
	public void setPower(double speed){
		talon.set(speed);
	}
	
	public void status(){
		SmartDashboard.putBoolean("GEARS PLACER::Switch1 state", switch1.get());
		SmartDashboard.putBoolean("GEARS PLACER::Switch2 state", switch2.get());
		SmartDashboard.putNumber("GEARS PLACER::Power", talon.get());
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

