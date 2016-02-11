package KBot.subsystems;

import KBot.RobotMap;
import KBot.commands.DriveController;
//import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    private final double DEADBAND = 0.01;
    private final boolean INVERT = true;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new DriveController());
    }
    
    public void drive(double left, double right, boolean leftTrigger, boolean rightTrigger)
    {
    	
    	System.out.println(RobotMap.driveEncoderLeft.get() + "\t" + RobotMap.driveEncoderRight.get());
    	
    	if (Math.abs(left) < DEADBAND)
    		left = 0;
    	if (Math.abs(right) < DEADBAND)
    		right = 0;
    	
    	if(leftTrigger)
    		left = 0;
    	if(rightTrigger)
    		right = 0;
    	
    	if(INVERT)
    		left = -left; right = -right;
    		
    	RobotMap.drive.tankDrive(left, right, true);


    	//System.out.println("Encoder: " + RobotMap.driveEncoderLeft.get() + " | EncoderRight: " + RobotMap.driveEncoderRight.get());
    	if (left>0.2)
    	{
    		RobotMap.red.set(Value.kReverse);
    	} else {
    		RobotMap.red.set(Value.kForward);
    	}
    	if (right>0.2)
    	{
    		RobotMap.blue.set(Value.kReverse);
    	} else {
    		RobotMap.blue.set(Value.kForward);
    	}
    	if (left<0 || right<0)
    	{
    		RobotMap.green.set(Value.kReverse);
    	} else {
    		RobotMap.green.set(Value.kForward);    		
    	}
    }
    
    public void driveCurve(double speed, double curve) {
    	if (speed>0.2)
    	{
    		RobotMap.red.set(Value.kReverse);
    	} else {
    		RobotMap.red.set(Value.kForward);
    	}
    	if (curve>0.2 || curve<-0.2)
    	{
    		RobotMap.blue.set(Value.kReverse);
    	} else {
    		RobotMap.blue.set(Value.kForward);
    	}
    	if (speed<0 )
    	{
    		RobotMap.green.set(Value.kReverse);
    	} else {
    		RobotMap.green.set(Value.kForward);    		
    	}
    	
    	RobotMap.drive.drive(speed, curve);
    }
    
    //Colors
    private boolean flip = false;
    public void white()
    {
    	RobotMap.green.set(Value.kReverse);
		RobotMap.red.set(Value.kReverse);
		RobotMap.blue.set(Value.kReverse);
    }
    public void green()
    {
    	RobotMap.green.set(Value.kReverse);
		RobotMap.red.set(Value.kForward);
		RobotMap.blue.set(Value.kForward);
    }
    public void red()
    {
    	RobotMap.green.set(Value.kForward);
		RobotMap.red.set(Value.kReverse);
		RobotMap.blue.set(Value.kForward);
    }
    public void blue()
    {
    	RobotMap.green.set(Value.kForward);
		RobotMap.red.set(Value.kForward);
		RobotMap.blue.set(Value.kReverse);
    }
    public void aqua()
    {
    	RobotMap.green.set(Value.kReverse);
		RobotMap.red.set(Value.kForward);
		RobotMap.blue.set(Value.kReverse);
    }
    public void purple()
    {
    	RobotMap.green.set(Value.kForward);
		RobotMap.red.set(Value.kReverse);
		RobotMap.blue.set(Value.kReverse);
    }
    public void yellow()
    {
    	RobotMap.green.set(Value.kReverse);
		RobotMap.red.set(Value.kReverse);
		RobotMap.blue.set(Value.kForward);
    }
    public void pink()
    {
    	if (flip)
    		white();
    	else
    		red();
    	flip = !flip;
    }
    public void orange()
    {
    	if (flip)
    		yellow();
    	else
    		red();
    	flip = !flip;
    }
    public void lime()
    {
    	if (flip)
    		white();
    	else
    		green();
    	flip = !flip;
    }
}

