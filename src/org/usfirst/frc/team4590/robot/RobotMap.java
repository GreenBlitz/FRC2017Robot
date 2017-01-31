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
    						DOUBLE_SOL_1_FORE 	= 0,
    						DOUBLE_SOL_1_REV 	= 0,
    						DOUBLE_SOL_2_FORE 	= 0,
    						DOUBLE_SOL_2_REV 	= 0;
    /**
     * Encoders Ports; for more ports see {@link RobotMap}
     */
    public static final int // encoders
    						CHASSIS_ENCODER_LEFT_A = 0,
    						CHASSIS_ENCODER_LEFT_B = 0,
    						
    						CHASSIS_ENCODER_RIGHT_A = 0,
    						CHASSIS_ENCODER_RIGHT_B = 0,
    
    						CHASSIS_TALON_RIGHT_REAR = 0,
    						CHASSIS_TALON_RIGHT_FRONT = 0,
    						CHASSIS_TALON_RIGHT_MIDLE = 0,
    						CHASSIS_TALON_LEFT_REAR = 0,
    						CHASSIS_TALON_LEFT_FRONT = 0,
    						CHASSIS_TALON_LEFT_MIDLE = 0;
    /**						
     * Gyros Ports; for more ports see {@link RobotMap}
     */
    public static final int // gyros
    						CHASSIS_GYRO = 0;
}
