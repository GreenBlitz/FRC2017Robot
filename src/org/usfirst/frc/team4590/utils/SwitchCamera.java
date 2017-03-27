package org.usfirst.frc.team4590.utils;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.networktables.NetworkTable;

public class SwitchCamera extends Command{
	public boolean isFinished(){ return true; }
	public void initialize(){ CameraIndex.values()[2 - (int)NetworkTable.getTable("vision").getNumber("camera_num", 0)].set(); }
}
