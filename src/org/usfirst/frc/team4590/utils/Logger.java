package org.usfirst.frc.team4590.utils;

import java.util.Date;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Logger {
	
	private String m_workspace;
	
	private Logger(String workspace){
		m_workspace = workspace;
	}
	
	public static Logger get(){
		StackTraceElement[] trace = Thread.currentThread().getStackTrace();
		String className = trace[1].getClassName();
		return new Logger(className);
	}
	
	public void log(String message){
		StringBuilder builder = new StringBuilder();
		builder.append("[").append(m_workspace).append("/").append(new Date().getTime()).append("] ");
		builder.append(message);
		System.out.println(builder.toString());
	}
	
	public void putDouble(String field, double value){
		SmartDashboard.putNumber(m_workspace + "::" + field, value);
	}
	
	public void putString(String field, String value){
		SmartDashboard.putString(m_workspace + "::" + field, value);
	}
	
	public void putPID(String field, PIDController controller){
		NetworkTable table = (NetworkTable) NetworkTable.getTable("SmartDashboard").getSubTable(field);
		putDouble(field + ".P", controller.getP());
		putDouble(field + ".I", controller.getI());
		putDouble(field + ".D", controller.getD());
		putDouble(field + ".Output", controller.get());
		putDouble(field + ".Error", controller.getError());
	}
	
}
