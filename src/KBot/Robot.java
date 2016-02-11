
package KBot;

import KBot.commands.AutonomousCommand;
import KBot.commands.DriveController;
import KBot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	private final int collapse_time = 115;
	private final boolean collapse_on = false;
	
	public static OI oi;
	public static DriveTrain drivetrain;
	private static Timer countdown;

    Command autonomousCommand, teleopCommand;
    
	static private boolean autonomousEnabled = false;
	private static boolean overrideSet = false;
	private static boolean collapsed = false;
	static public boolean isTeleop(){
		return !autonomousEnabled;
	}

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
    
		drivetrain = new DriveTrain();
			
		oi = new OI();
		
        // instantiate the command used for the teleop period
		teleopCommand = new DriveController();
		countdown = new Timer();
		
		autonomousCommand = new AutonomousCommand();
    }
    
    private void setAutonomousMode() {
		
    }
    
    public void autonomousInit() {
        autonomousEnabled = isAutonomous();
		setAutonomousMode();
		
        if (autonomousCommand != null)
        {
        	System.out.println("starting autonomous command");
        	autonomousCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        autonomousEnabled = isAutonomous();
        Scheduler.getInstance().run();
        //oi.operator.tron();
    }

    public void teleopInit() {
        autonomousEnabled = isAutonomous();
		
        if (autonomousCommand != null) autonomousCommand.cancel();
        
        countdown.reset();
        countdown.start();
        collapsed = false;
        
        
   
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        autonomousEnabled = isAutonomous();
        
        Scheduler.getInstance().run();
        	
        if (countdown.get() > collapse_time && !collapsed && collapse_on)        
        {
        	collapsed = true;
        }
        //System.out.println("Wrist Pot: " + (RobotMap.wristPot.get()/3.674-384) + " | Operator Pot: " + oi.operator.getPotAngle());
    }
    
    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
        autonomousEnabled = isAutonomous();
    	if (teleopCommand != null) teleopCommand.cancel();
    	if (autonomousCommand != null) autonomousCommand.cancel();
    	//countdown.reset();
    	collapsed = false;
    }
    
	int count=0;
	public void disabledPeriodic() {
        autonomousEnabled = isAutonomous();

	}

    /**
     * This function is called once before test mode
     */
    public void testInit() {
        autonomousEnabled = isAutonomous();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        autonomousEnabled = isAutonomous();
        LiveWindow.run();

    }
}
