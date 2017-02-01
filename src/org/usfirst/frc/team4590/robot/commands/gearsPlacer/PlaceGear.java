package org.usfirst.frc.team4590.robot.commands.gearsPlacer;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class PlaceGear extends CommandGroup {
    
    public  PlaceGear() {
        addSequential(new OpenPlacer());
        //TODO Move back or wait???
        addSequential(new ClosePlacer());
    }
}
