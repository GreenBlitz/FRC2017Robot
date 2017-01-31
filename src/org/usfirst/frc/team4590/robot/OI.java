package org.usfirst.frc.team4590.robot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
	private OI(){
		
	}
	
	public static final void init(){
		instance = new OI();
	}
	
	public static final OI getInstance(){
		return instance;
	}
	
	private static OI instance;
	

}

