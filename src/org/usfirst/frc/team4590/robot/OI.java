package org.usfirst.frc.team4590.robot;

import org.usfirst.frc.team4590.utils.SmartJoystick;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickAxis;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickBinding;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

import static org.usfirst.frc.team4590.robot.RobotMap.*;

import org.usfirst.frc.team4590.robot.commands.climber.Climb;
import org.usfirst.frc.team4590.robot.commands.fuelCollector.CollectFuel;
/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private SmartJoystick mainJS;
	private static OI instance;
	/*
	private JoystickButton mainA = mainJS.getButton(JoystickBinding.A);
	private JoystickButton mainB = mainJS.getButton(JoystickBinding.B);
	private JoystickButton mainX = mainJS.getButton(JoystickBinding.X);
	*/
	/* TODO Shooter , 
	 * A = Activate Climber
	 * B = Collect Fuel
	 * X = Shooter
	 * Y = 
	 */
	private OI(){
		mainJS = new SmartJoystick(JOYSTICK_MAIN);
		/*
		mainA.whenPressed(new Climb());
		mainB.whenPressed(new CollectFuel());
		*/
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

