package org.usfirst.frc.team4590.robot;

import org.usfirst.frc.team4590.utils.SmartJoystick;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickAxis;

import static org.usfirst.frc.team4590.robot.RobotMap.*;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private SmartJoystick mainJS;
	private static OI instance;
		
	private OI(){
		mainJS = new SmartJoystick(JOYSTICK_MAIN);
	}
	
	public static final void init(){
		instance = new OI();
	}
	
	public static final OI getInstance(){
		return instance;
	}
	
	public double getMainLeftY(){
		return mainJS.getAxisValue(JoystickAxis.LEFT_Y);
	}
	
	public double getMainLeftX(){
		return mainJS.getAxisValue(JoystickAxis.LEFT_X);
	}
	
	public double getMainRightY(){
		return mainJS.getAxisValue(JoystickAxis.RIGHT_Y);
	}
	
	public double getMainRightX(){
		return mainJS.getAxisValue(JoystickAxis.RIGHT_X);
	}

}

