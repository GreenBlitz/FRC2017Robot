package org.usfirst.frc.team4590.utils;

import edu.wpi.first.wpilibj.PIDController;

@FunctionalInterface
public interface PIDGetter {
	public double getInput(PIDController controller);
}
