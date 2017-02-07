package org.usfirst.frc.team4590.robot.subsystems;


import static org.usfirst.frc.team4590.robot.RobotMap.CLIMBER_TALON;

import org.usfirst.frc.team4590.robot.commands.climber.FreeClimber;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/**
 *
 */
public class Climber extends Subsystem {
	CANTalon talon1;
	private static Climber instance;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Climber(){
		talon1 = new CANTalon(CLIMBER_TALON);
	}
	public static final void init(){
		instance = new Climber();
	}
	
	public static final Climber getInstance(){
		return instance;
	}
	public void setPower(double power){
		talon1.set(power);
	}
	public void status(){
		SmartDashboard.putNumber("CLIMBER::Power", talon1.get());
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new FreeClimber());
    }
}

