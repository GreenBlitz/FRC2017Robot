package org.usfirst.frc.team4590.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import static org.usfirst.frc.team4590.robot.RobotMap.*;
/**
 *
 */
public class Feeder extends Subsystem {
    TalonSRX talon1;
    Encoder enc;
	private static Feeder instance;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
public Feeder(){
	talon1 = new TalonSRX(FEEDER_TALON);
	enc = new Encoder(FEEDER_ENCODER_A,FEEDER_ENCODER_B);
}
public static final void init(){
	instance = new Feeder();
}

public static final Feeder getInstance(){
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

