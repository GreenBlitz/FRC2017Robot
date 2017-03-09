package org.usfirst.frc.team4590.robot;

import static org.usfirst.frc.team4590.robot.RobotMap.JOYSTICK_MAIN;
import static org.usfirst.frc.team4590.robot.RobotMap.JOYSTICK_SECOND;

import org.usfirst.frc.team4590.robot.commands.chassis.DriveByGoalVision;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByGoalVision;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByVision;
import org.usfirst.frc.team4590.robot.commands.chassis.GearsAutoJoel;
import org.usfirst.frc.team4590.robot.commands.chassis.SetDriveMultiplier;
import org.usfirst.frc.team4590.robot.commands.chassis.TurnByVision;
import org.usfirst.frc.team4590.robot.commands.feeder.FeedToShooter;
import org.usfirst.frc.team4590.robot.commands.fuelCollector.CollectFuel;
import org.usfirst.frc.team4590.robot.commands.gearsPlacer.ClosePlacer;
import org.usfirst.frc.team4590.robot.commands.gearsPlacer.OpenPlacer2;
import org.usfirst.frc.team4590.robot.commands.shifter.ValveSetState;
import org.usfirst.frc.team4590.robot.commands.shooter.FreeShooter;
import org.usfirst.frc.team4590.robot.commands.shooter.ShooterSetSpeed;
import org.usfirst.frc.team4590.robot.commands.shooter.ShooterSetValue;
import org.usfirst.frc.team4590.utils.SmartJoystick;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickAxis;
import org.usfirst.frc.team4590.utils.SmartJoystick.JoystickBinding;

import edu.wpi.first.wpilibj.command.Command;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

	
	private SmartJoystick mainJS;
	private SmartJoystick subJS;
	private static OI instance;

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
		mainJS.getButton(JoystickBinding.Y).whenPressed(new ValveSetState());
		mainJS.getButton(JoystickBinding.R1).whileHeld(new CollectFuel());
		mainJS.getButton(JoystickBinding.START).whenPressed(new SetDriveMultiplier(0.5, false));
		subJS.getButton(JoystickBinding.L1).whileHeld(new CollectFuel());
		subJS.getButton(JoystickBinding.X).whileHeld(new FeedToShooter());
		subJS.getButton(JoystickBinding.R1).whileHeld(new ShooterSetSpeed(2150));
		subJS.getButton(JoystickBinding.A).whileHeld(new TurnByVision());
		subJS.getButton(JoystickBinding.Y).whileHeld(new DriveStraightByGoalVision(1300));
		subJS.getButton(JoystickBinding.B).whileHeld(new DriveByGoalVision(1300));
		subJS.getButton(JoystickBinding.BACK).whileHeld(new Command(){
			public boolean isFinished(){ return true; }
			public void initialize(){ if (Robot.test_command != null) Robot.test_command.start(); }
		});
	}
	
	public boolean getSubPOVDown(){
		int a = subJS.getRawJoystick().getPOV();
		return a >= 135 && a <= 225;
	}

	private class MainJoystickInit extends Command{

		private SmartJoystick m_joystick;

		protected MainJoystickInit(SmartJoystick stick){
			m_joystick = stick;
		}

		@Override
		protected boolean isFinished() {
			return true;
		}

		protected void execute(){
			m_joystick.getButton(SmartJoystick.JoystickBinding.A).whenPressed(new OpenPlacer2());
			m_joystick.getButton(SmartJoystick.JoystickBinding.B).whenPressed(new ClosePlacer());
			m_joystick.getButton(SmartJoystick.JoystickBinding.L1).whileHeld(new DriveStraightByVision());
			m_joystick.getButton(SmartJoystick.JoystickBinding.Y).whenPressed(new ValveSetState());
			m_joystick.getButton(SmartJoystick.JoystickBinding.R1).whileHeld(new CollectFuel());
			m_joystick.getButton(SmartJoystick.JoystickBinding.X).whileHeld(new FeedToShooter());
			mainJS = m_joystick;
		}

		
	}

	private class SubJoystickInit extends Command{

		private SmartJoystick m_joystick;

		protected SubJoystickInit(SmartJoystick stick){
			m_joystick = stick;
		}

		@Override
		protected boolean isFinished() {
			return true;
		}

		protected void execute(){
			
			m_joystick.getButton(SmartJoystick.JoystickBinding.A).whenPressed(new GearsAutoJoel(true, 0.6, 0.5));
			//m_joystick.getButton(SmartJoystick.JoystickBinding.B).whileHeld(new FeedToShooter());
			m_joystick.getButton(SmartJoystick.JoystickBinding.B).whileHeld(new ShooterSetValue(0));
			m_joystick.getButton(SmartJoystick.JoystickBinding.X).whenPressed(new TurnByVision());
			m_joystick.getButton(SmartJoystick.JoystickBinding.Y).whileHeld(new GearsAutoJoel(false, 0.6, 0.5));
			m_joystick.getButton(SmartJoystick.JoystickBinding.R1).whileHeld(new CollectFuel());
			m_joystick.getButton(SmartJoystick.JoystickBinding.L1).whenPressed(new ShooterSetSpeed(558));
			m_joystick.getButton(SmartJoystick.JoystickBinding.L3).whenPressed(new FreeShooter());
			mainJS = m_joystick;
		}

	}

	public static final void init() {
		instance = new OI();
	}

	public static final OI getInstance() {
		return instance;
	}

	public double getMainLeftY() {
		return mainJS.getAxisValue(JoystickAxis.LEFT_Y);
	}

	public double getMainLeftX() {
		return mainJS.getAxisValue(JoystickAxis.LEFT_X);
	}

	public double getMainRightY() {
		return mainJS.getAxisValue(JoystickAxis.RIGHT_Y);
	}

	public double getMainRightX() {
		return mainJS.getAxisValue(JoystickAxis.RIGHT_X);
	}
	
	public double getMainNormalLeftY() {
		double y = getMainLeftY();
		double x = getMainLeftX();
		
		return y / Math.sqrt(x * x + y * y);	
	}
	
	public double getMainNormalRightX() {
		double y = getMainRightY();
		double x = getMainRightX();
		
		return x / Math.sqrt(x * x + y * y);	
	}

	public double getMainTriggerL() {
		return mainJS.getRawAxis(2);
	}

	public double getMainTriggerR() {
		return mainJS.getRawAxis(3);
	}

}
