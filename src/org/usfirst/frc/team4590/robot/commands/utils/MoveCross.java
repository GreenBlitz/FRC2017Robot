package org.usfirst.frc.team4590.robot.commands.utils;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class MoveCross extends Command{
	
	private int x;
	private int y;
	
	public MoveCross(int x_, int y_){
		x = x_;
		y = y_;
	}
	
	public void execute(){
		int x_ = (int) NetworkTable.getTable("vision").getNumber("cross_x", 0);
		int y_ = (int) NetworkTable.getTable("vision").getNumber("cross_y", 0);
		int toX = Math.max(2, Math.min(x_ + x, 638));
		int toY = Math.max(2, Math.min(y_ + y, 478));
		NetworkTable.getTable("vision").putNumber("cross_x", toX);
		NetworkTable.getTable("vision").putNumber("cross_y", toY);
	}
	
	public boolean isFinished(){ return true; }
}
