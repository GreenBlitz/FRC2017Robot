package org.usfirst.frc.team4590.utils;

import edu.wpi.first.wpilibj.networktables.NetworkTable;

public enum CameraIndex {
	LIFT(0),
	SIDE(1),
	GOAL(2);
	
	private int index;
	
	private CameraIndex(int index_){
		index = index_;
	}
	
	public void set(){
		int cur = (int) NetworkTable.getTable("vision").getNumber("cur_camera", index);
		boolean updated = NetworkTable.getTable("vision").getBoolean("camera_updated", false);
		NetworkTable.getTable("vision").putBoolean("camera_updated", index == cur && updated  );
		NetworkTable.getTable("vision").putNumber("cur_camera", index);
	}
}
