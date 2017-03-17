package org.usfirst.frc.team4590.utils;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class SmartJoystick {
	private int m_leftAxisY;
	private int m_leftAxisX;
	private int m_rightAxisY;
	private int m_rightAxisX;
	
	private boolean m_leftInvertedY;
	private boolean m_leftInvertedX;
	private boolean m_rightInvertedY;
	private boolean m_rightInvertedX;
	
	private Joystick m_joystick;
	
	public static enum JoystickBinding{
		A,
		B,
		X,
		Y,
		L1,
		R1,
		BACK,
		START,
		L3,
		R3;
	}
	
	public static enum JoystickAxis{
		LEFT_X,
		LEFT_Y,
		RIGHT_X,
		RIGHT_Y;
	}
	
	public SmartJoystick(int joystick_port){
		this(new Joystick(joystick_port));
	}
	
	public SmartJoystick(){}
	
	public SmartJoystick(Joystick stick){
		m_joystick = stick;
	}
	
	public JoystickButton getButton(JoystickBinding binding){
		return new JoystickButton(m_joystick, binding.ordinal() + 1);
	}
	
	public void setAxis(JoystickAxis axis, int raw_axis_id){
		switch(axis){
		case LEFT_Y:
			m_leftAxisY = raw_axis_id;
			break;
		case LEFT_X:
			m_leftAxisX = raw_axis_id;
			break;
		case RIGHT_Y:
			m_rightAxisY = raw_axis_id;
			break;
		case RIGHT_X:
			m_rightAxisX = raw_axis_id;
			break;
		}
	}
	
	public void setAxisInverted(JoystickAxis axis, boolean inverted){
		switch(axis){
		case LEFT_Y:
			m_leftInvertedY = inverted;
			break;
		case LEFT_X:
			m_leftInvertedX = inverted;
			break;
		case RIGHT_Y:
			m_rightInvertedY = inverted;
			break;
		case RIGHT_X:
			m_rightInvertedX = inverted;
			break;
		}
	}
	
	public double getAxisValue(JoystickAxis axis){
		if (m_joystick == null) return 0;
		switch(axis){
		case LEFT_Y:
			return(m_leftInvertedY ? -1 : 1) * m_joystick.getRawAxis(m_leftAxisY);
		case LEFT_X:
			return(m_leftInvertedX ? -1 : 1) * m_joystick.getRawAxis(m_leftAxisX);
		case RIGHT_Y:
			return(m_rightInvertedY ? -1 : 1) * m_joystick.getRawAxis(m_rightAxisY);
		case RIGHT_X:
			return(m_rightInvertedX ? -1 : 1) * m_joystick.getRawAxis(m_rightAxisX);
		}
		
		
		System.out.println("[SmartJoystick.getAxisValue()]Something went wrong");
		return -1;
	}
	
	public void bind(Joystick stick){
		m_joystick = stick;
	}
	
	public void bind(int port){
		bind(new Joystick(port));
	}
	
	public double getRawAxis(int raw_axis){
		if (m_joystick == null) return 0;
		return m_joystick.getRawAxis(raw_axis);
	}

	public Joystick getRawJoystick() {
		return m_joystick;
	}
}
