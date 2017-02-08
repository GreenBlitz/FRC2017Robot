package org.usfirst.frc.team4590.robot.commands.chassis;

import org.usfirst.frc.team4590.robot.commands.gearsPlacer.ClosePlacer;
import org.usfirst.frc.team4590.robot.commands.gearsPlacer.OpenPlacer;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearsAutoJoel extends CommandGroup {

    public GearsAutoJoel(boolean isLeft, double power) {
        // Add Commands here:
    	
    	addSequential(new DriveUntilVision(isLeft,power));
    	addSequential(new TurnUntilVision(!isLeft,power));
    	addSequential(new DriveStraightByVision());
    	addSequential(new OpenPlacer());
    	addSequential(new DriveStraightByDistance(-100));
    	addSequential(new ClosePlacer());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
