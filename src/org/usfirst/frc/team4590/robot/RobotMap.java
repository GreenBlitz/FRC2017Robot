package org.usfirst.frc.team4590.robot;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

	/**
	 *Solenoid Ports; for more ports see {@link RobotMap} 
	 */
    public static final int //solenoids
    						BS_DOUBLE_SOL_FORE 	= 5,
    						BS_DOUBLE_SOL_REV 	= 4;
    /**
     * Encoders Ports; for more ports see {@link RobotMap}
     */
    public static final int // encoders
    						CHASSIS_ENCODER_LEFT_A = 2,
    						CHASSIS_ENCODER_LEFT_B = 3,
    						
    						CHASSIS_ENCODER_RIGHT_A = 0,
    						CHASSIS_ENCODER_RIGHT_B = 1,
    						
    						SHOOTER_ENCODER_A = 0,
    						SHOOTER_ENCODER_B = 0,
    						
    						FEEDER_ENCODER_A = 0,
    						FEEDER_ENCODER_B = 0;
    
    /**
     * Motor Ports; for more ports see {@link RobotMap}
     */
    public static final int // motors
    						CHASSIS_TALON_RIGHT_REAR = 1,
    						CHASSIS_TALON_RIGHT_FRONT = 2,
    						CHASSIS_TALON_RIGHT_MIDLE = 3,
    						CHASSIS_TALON_LEFT_REAR = 4,
    						CHASSIS_TALON_LEFT_FRONT = 5,
    						CHASSIS_TALON_LEFT_MIDLE = 6,
    						
    						GEARS_TALON = 9,
    
    						SHOOTER_TALON_A = 11,
    						SHOOTER_TALON_B = 12,
    						
    						FUEL_COLLECTOR_TALON = 8,
    						
    						CLIMBER_TALON = 7,
    						
    						FEEDER_TALON = 10,
    						HELPER_TALON = 13;
    
    /**						
     * Gyros Ports; for more ports see {@link RobotMap}
     */
    public static final int // gyros
    						CHASSIS_GYRO = 0;
    
    /**
     * Micro Switches Ports; for more ports see {@link RobotMap}
     */
    public static final int // switches
    						GEARS_SWITCH_A = 4,
    	    				GEARS_SWITCH_B = 5;
    /**						
     * Joystick Ports; for more ports see {@link RobotMap}
     */
    public static final int
    						JOYSTICK_MAIN = 0,
    						JOYSTICK_SECOND = 2;
}
