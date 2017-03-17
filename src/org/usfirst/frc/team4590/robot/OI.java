package org.usfirst.frc.team4590.robot;

import static org.usfirst.frc.team4590.robot.RobotMap.JOYSTICK_MAIN;

import org.usfirst.frc.team4590.robot.commands.chassis.ArcadeDriveByJoystick;
import org.usfirst.frc.team4590.robot.commands.chassis.ArcadeDriveToggle;
import org.usfirst.frc.team4590.robot.commands.chassis.GearsAutoJoel;
import org.usfirst.frc.team4590.robot.commands.chassis.TankDriveByJoystick;
import org.usfirst.frc.team4590.robot.commands.chassis.TurnByVision;
import org.usfirst.frc.team4590.robot.commands.climber.ClimbByTrigger;
import org.usfirst.frc.team4590.robot.commands.feeder.FeedToShooter;
import org.usfirst.frc.team4590.robot.commands.fuelCollector.CollectFuel;
import org.usfirst.frc.team4590.robot.commands.gearsPlacer.ClosePlacer;
import org.usfirst.frc.team4590.robot.commands.gearsPlacer.OpenPlacer2;
import org.usfirst.frc.team4590.robot.commands.gearsPlacer.PlaceGear;
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
		mainJS = new SmartJoystick();
		subJS = new SmartJoystick();
		SmartJoystick stick1 = new SmartJoystick(JOYSTICK_MAIN);
		stick1.getButton(JoystickBinding.START).whenPressed(new MainJoystickInit(stick1));
		stick1.getButton(JoystickBinding.BACK).whenPressed(new SubJoystickInit(stick1));
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
			m_joystick.setAxis(JoystickAxis.LEFT_X, 0);
			m_joystick.setAxis(JoystickAxis.LEFT_Y, 1);
			m_joystick.setAxis(JoystickAxis.RIGHT_X, 4);
			m_joystick.setAxis(JoystickAxis.RIGHT_Y, 5);
			m_joystick.setAxisInverted(JoystickAxis.LEFT_Y, true);
			m_joystick.setAxisInverted(JoystickAxis.RIGHT_Y, true);
			m_joystick.getButton(SmartJoystick.JoystickBinding.A).whenPressed(new ArcadeDriveByJoystick());
			//m_joystick.getButton(SmartJoystick.JoystickBinding.B).whenPressed(new PlaceGear());
			//m_joystick.getButton(SmartJoystick.JoystickBinding.B).whenPressed(new ArcadeDriveToggle());
			m_joystick.getButton(SmartJoystick.JoystickBinding.X).whenPressed(new TankDriveByJoystick());
			m_joystick.getButton(SmartJoystick.JoystickBinding.Y).whenPressed(new ValveSetState());
			m_joystick.getButton(SmartJoystick.JoystickBinding.L1).whenPressed(new CollectFuel());
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
			
			m_joystick.setAxis(JoystickAxis.LEFT_X, 0);
			m_joystick.setAxis(JoystickAxis.LEFT_Y, 1);
			m_joystick.setAxis(JoystickAxis.RIGHT_X, 4);
			m_joystick.setAxis(JoystickAxis.RIGHT_Y, 5);
			m_joystick.setAxisInverted(JoystickAxis.LEFT_Y, true);
			m_joystick.setAxisInverted(JoystickAxis.RIGHT_Y, true);
			m_joystick.getButton(SmartJoystick.JoystickBinding.A).whenPressed(new GearsAutoJoel(true, 0.6, 0.5));
			m_joystick.getButton(SmartJoystick.JoystickBinding.X).whileHeld(new FeedToShooter());
			m_joystick.getButton(SmartJoystick.JoystickBinding.B).whileHeld(new ClimbByTrigger());
			//m_joystick.getButton(SmartJoystick.JoystickBinding.X).whenPressed(new TurnByVision());
			m_joystick.getButton(SmartJoystick.JoystickBinding.Y).whenPressed(new GearsAutoJoel(false, 0.6, 0.5));
			m_joystick.getButton(SmartJoystick.JoystickBinding.R1).whileHeld(new CollectFuel());
			m_joystick.getButton(SmartJoystick.JoystickBinding.L1).whenPressed(new ShooterSetSpeed(2100)); //2100 - 200
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

	public boolean getMainR1() {
		return mainJS.getButton(JoystickBinding.R1).get();
	}

	public boolean getMainL1() {
		return mainJS.getButton(JoystickBinding.L1).get();
	}

	public double getMainTriggerL() {
		return mainJS.getRawAxis(2);
	}

	public double getMainTriggerR() {
		return mainJS.getRawAxis(3);
	}

}
