package KBot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousCommand extends CommandGroup {
    
    public  AutonomousCommand() {

    	addSequential(new AutoDrive(0.5d,0.5d, 25, 25)); // DO THE LOW BAR
//    	addSequential(new AutoDrive()); // Smash into corner!
//    	addSequential(new AutoDrive()); // DO A TURN
//    	addSequential(new FireLaunch()); // Shoot thing.
//    	
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
