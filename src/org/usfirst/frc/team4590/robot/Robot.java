
package org.usfirst.frc.team4590.robot;

import java.util.LinkedList;
import java.util.List;

import org.usfirst.frc.team4590.robot.commands.auto.HopperAutoA2;
import org.usfirst.frc.team4590.robot.commands.auto.HopperAutoB2;
import org.usfirst.frc.team4590.robot.commands.chassis.ArcadeDriveByValues;
import org.usfirst.frc.team4590.robot.commands.chassis.BasicGearsAutoGuyada;
import org.usfirst.frc.team4590.robot.commands.chassis.BasicGearsAutoGuyde;
import org.usfirst.frc.team4590.robot.commands.chassis.BasicGearsAutoJoel;
import org.usfirst.frc.team4590.robot.commands.chassis.DriveStraightByDistance;
import org.usfirst.frc.team4590.robot.commands.feeder.FeedToShooter;
import org.usfirst.frc.team4590.robot.commands.fuelCollector.CollectFuel;
import org.usfirst.frc.team4590.robot.commands.shooter.ShooterSetSpeed;
import org.usfirst.frc.team4590.robot.subsystems.Chassis;
import org.usfirst.frc.team4590.robot.subsystems.Climber;
import org.usfirst.frc.team4590.robot.subsystems.Feeder;
import org.usfirst.frc.team4590.robot.subsystems.FuelCollector;
import org.usfirst.frc.team4590.robot.subsystems.GearsPlacer;
import org.usfirst.frc.team4590.robot.subsystems.Shifts;
import org.usfirst.frc.team4590.robot.subsystems.Shifts.ShifterState;
import org.usfirst.frc.team4590.utils.MatchData;
import org.usfirst.frc.team4590.robot.subsystems.Shooter;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	private List<Command> perma_commands = new LinkedList<Command>();

	private static Robot instance;

	public static Command test_command;

	public static Robot getInstance() {
		return instance;
	}

	public void addPermaCommand(Command com) {
		perma_commands.add(com);
	}

	Command autonomousCommand;
	SendableChooser<Command> m_autoChooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		instance = this;
		Chassis.init();
		Climber.init();
		GearsPlacer.init();
		FuelCollector.init();
		Shifts.init();
		Feeder.init();
		Shooter.init();
		OI.init();
		UsbCamera cam = CameraServer.getInstance().startAutomaticCapture();
		cam.setResolution(320, 240);
		cam.setFPS(18);
		MatchData.getInstance().robotInit();
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		Shifts.getInstance().setState(ShifterState.POWER);
		MatchData.getInstance().autonomusInit();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		MatchData.getInstance().teleopInit();
		
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		Chassis.getInstance().status();
		// Climber.getInstance().status();
		// Feeder.getInstance().status();
		// FuelCollector.getInstance().status();
		GearsPlacer.getInstance().status();
		// Shifts.getInstance().status();
		Shooter.getInstance().status();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
