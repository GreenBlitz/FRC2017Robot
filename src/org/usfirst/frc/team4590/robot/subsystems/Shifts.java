package org.usfirst.frc.team4590.robot.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import static org.usfirst.frc.team4590.robot.RobotMap.*;
/**
 *
 */
public class Shifts extends Subsystem {
	private DoubleSolenoid 	doubleSol1, 
							doubleSol2 ;
	private static Shifts instance;
	
	public static enum ShifterState{
		SPEED(DoubleSolenoid.Value.kReverse),
		POWER(DoubleSolenoid.Value.kForward);
		
		private DoubleSolenoid.Value value;
		
		private ShifterState(DoubleSolenoid.Value _value){
			value = _value;
		}
		
		public DoubleSolenoid.Value getValue(){
			return value;
		}
	}
	
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private Shifts(){
		doubleSol1 = new DoubleSolenoid(DOUBLE_SOL_1_FORE, DOUBLE_SOL_1_REV);
		doubleSol2 = new DoubleSolenoid(DOUBLE_SOL_2_FORE, DOUBLE_SOL_2_REV);
	}
	
	public static final void init(){
		instance = new Shifts();
	}
	
	public static final Shifts getInstance(){
		return instance;
	}
	

	
	public void setState(ShifterState state){
		doubleSol1.set(state.getValue());
		doubleSol2.set(state.getValue());
	}
	
	public void toggleState(){

		// doubleSol1.get().ordinal() => The current index in the enum of the DoubleSolenoid.Value
		// (our indexes are the same) 1 - index = {index = 0: 1 - 0 = 0; index = 1: 1 - 1 = 0; }
		
		setState(ShifterState.values()[1 - doubleSol1.get().ordinal()]);
	}
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
