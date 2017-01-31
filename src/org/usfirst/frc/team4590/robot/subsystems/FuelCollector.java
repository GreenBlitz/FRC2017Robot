package org.usfirst.frc.team4590.robot.subsystems;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import static org.usfirst.frc.team4590.robot.RobotMap.*;
/**
 *
 */
public class FuelCollector extends Subsystem {
    private TalonSRX talon1;

    private static FuelCollector instance;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public FuelCollector(){
		talon1 = new TalonSRX(FUEL_COLLECTOR_TALON);
	}
	public static final void init(){
		instance = new FuelCollector();
	}
	
	public static final FuelCollector getInstance(){
		return instance;
	}
	public void setPower(double power){
		talon1.set(power);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

