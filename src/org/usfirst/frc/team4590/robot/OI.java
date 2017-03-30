package org.usfirst.frc.team4590.robot;

import static org.usfirst.frc.team4590.robot.RobotMap.JOYSTICK_MAIN;
import static org.usfirst.frc.team4590.robot.RobotMap.JOYSTICK_SECOND;

import org.usfirst.frc.team4590.robot.commands.auto.RunAutoCommand;
import org.usfirst.frc.team4590.robot.commands.auto.TurnToGoalByVision;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByVision;
import org.usfirst.frc.team4590.robot.commands.chassis.SetDriveMultiplier;
import org.usfirst.frc.team4590.robot.commands.chassis.ToggleSpeedControl;
import org.usfirst.frc.team4590.robot.commands.feeder.FeedToShooter;
import org.usfirst.frc.team4590.robot.commands.fuelCollector.CollectFuel;
import org.usfirst.frc.team4590.robot.commands.fuelCollector.FreeCollector;
import org.usfirst.frc.team4590.robot.commands.gearsPlacer.ClosePlacer;
import org.usfirst.frc.team4590.robot.commands.gearsPlacer.OpenPlacer2;
import org.usfirst.frc.team4590.robot.commands.shifter.ValveToggleState;
import org.usfirst.frc.team4590.robot.commands.shooter.ShooterSetSpeedCopy;
import org.usfirst.frc.team4590.robot.commands.utils.MoveCross;
import org.usfirst.frc.team4590.utils.SmartJoystick;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickAxis;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickBinding;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

import org.usfirst.frc.team4590.utils.SwitchCamera;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	
	private SmartJoystick mainJS;
	private SmartJoystick subJS;
	private static OI instance;

	private static final double JOYSTICK_DEADZONE = 0.165;//0.145;
	
	/*
	 * private JoystickButton mainA = mainJS.getButton(JoystickBinding.A);
	 * private JoystickButton mainB = mainJS.getButton(JoystickBinding.B);
	 * private JoystickButton mainX = mainJS.getButton(JoystickBinding.X);
	 */
	/*
	 * TODO Shooter , A = Activate Climber B = Collect Fuel X = Shooter Y =
	 */
	private OI() {
		mainJS = new SmartJoystick(JOYSTICK_MAIN);
		subJS = new SmartJoystick(JOYSTICK_SECOND);

		mainJS.setAxis(JoystickAxis.LEFT_X, 0);
		mainJS.setAxis(JoystickAxis.LEFT_Y, 1);
		mainJS.setAxis(JoystickAxis.RIGHT_X, 4);
		mainJS.setAxis(JoystickAxis.RIGHT_Y, 5);
		mainJS.setAxisInverted(JoystickAxis.LEFT_Y, true);
		mainJS.setAxisInverted(JoystickAxis.RIGHT_Y, true);
		mainJS.getButton(JoystickBinding.A).whenPressed(new OpenPlacer2());
		mainJS.getButton(JoystickBinding.B).whenPressed(new ClosePlacer());
		mainJS.getButton(JoystickBinding.L1).whileHeld(new DriveStraightByVision());
		mainJS.getButton(JoystickBinding.Y).whenPressed(new ValveToggleState());
		mainJS.getButton(JoystickBinding.R1).whileHeld(new CollectFuel());
		mainJS.getButton(JoystickBinding.START).whenPressed(new SetDriveMultiplier(0.5, false));
		mainJS.getButton(JoystickBinding.BACK).whenPressed(new ToggleSpeedControl());
		mainJS.getButton(JoystickBinding.X).whenPressed(new SwitchCamera());
		
		subJS.getButton(JoystickBinding.L1).whileHeld(new CollectFuel());
		subJS.getButton(JoystickBinding.START).whileHeld(new FeedToShooter());
		subJS.getButton(JoystickBinding.R1).whileHeld(new ShooterSetSpeedCopy(2020));
		subJS.getButton(JoystickBinding.A).whenPressed(new MoveCross(0, 3));
		subJS.getButton(JoystickBinding.Y).whenPressed(new MoveCross(0, -3));
		subJS.getButton(JoystickBinding.B).whenPressed(new MoveCross(-4, 0));
		subJS.getButton(JoystickBinding.X).whenPressed(new MoveCross(4, 0));
		subJS.getButton(JoystickBinding.BACK).whileHeld(new FreeCollector());
		subJS.getButton(JoystickBinding.L3).whileHeld(new TurnToGoalByVision());
		subJS.getButton(JoystickBinding.R3).whenPressed(new RunAutoCommand());
		subJS.getButton(JoystickBinding.R3).whenReleased(new Command() {
			
			@Override
			protected boolean isFinished() {
				// TODO Auto-generated method stub
				return true;
			}
			
			protected void end(){
				Scheduler.getInstance().removeAll();
			}
		});
	}
	
	public boolean getSubPOVDown(){
		int a = subJS.getRawJoystick().getPOV();
		return a >= 135 && a <= 225;
	}

	public static final void init() {
		instance = new OI();
	}

	public static final OI getInstance() {
		return instance;
	}

	public double getMainLeftY() {
		return Math.abs(mainJS.getAxisValue(JoystickAxis.LEFT_Y)) < JOYSTICK_DEADZONE ? 0 : mainJS.getAxisValue(JoystickAxis.LEFT_Y);
	}

	public double getMainLeftX() {
		return Math.abs(mainJS.getAxisValue(JoystickAxis.LEFT_X)) < JOYSTICK_DEADZONE ? 0 : mainJS.getAxisValue(JoystickAxis.LEFT_X);
	}

	public double getMainRightY() {
		return Math.abs(mainJS.getAxisValue(JoystickAxis.RIGHT_Y)) < JOYSTICK_DEADZONE ? 0 : mainJS.getAxisValue(JoystickAxis.RIGHT_Y);
	}

	public double getMainRightX() {
		return Math.abs(mainJS.getAxisValue(JoystickAxis.RIGHT_X)) < JOYSTICK_DEADZONE ? 0 : mainJS.getAxisValue(JoystickAxis.RIGHT_X);
	}
	
	public double getMainNormalLeftY() {
		double y = getMainLeftY();
		double x = getMainLeftX();
		
		if (x == 0) return y;
		
		if (Math.abs(x) > Math.abs(y)){
			double vec = Math.sqrt(x * x + y * y);
			
			double vecForAngle = vec / Math.abs(x);
			
			return vecForAngle * y;
		} else {
			double vec = Math.sqrt(x * x + y * y);
			
			double vecForAngle = vec / Math.abs(y);
			
			return vecForAngle * y;
		}
		
	}
	
	public double getMainNormalRightX() {
		double y = getMainRightY();
		double x = getMainRightX();
		
		if (y == 0) return x;
		
		if (Math.abs(y) > Math.abs(x)){
			double vec = Math.sqrt(x * x + y * y);
			
			double vecForAngle = vec / Math.abs(y);
			
			return vecForAngle * x;
		} else {
			double vec = Math.sqrt(x * x + y * y);
			
			double vecForAngle = vec / Math.abs(x);
			
			return vecForAngle * x;
		}
		
	}

	public double getMainTriggerL() {
		return mainJS.getRawAxis(2);
	}

	public double getMainTriggerR() {
		return mainJS.getRawAxis(3);
	}

}
