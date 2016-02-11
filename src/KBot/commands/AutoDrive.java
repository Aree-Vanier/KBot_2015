package KBot.commands;

import KBot.Robot;
import KBot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoDrive extends Command {
	int targetLeftWheelRotations;
	int targetRightWheelRotations;
	double lspeed,rspeed;
    public AutoDrive(double lspeed, double rspeed,int targetRightDistance,int targetLeftDistance) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	
    	/*ENCODER INFORMATION
    	 * Encoder to shaft ratio: 36:12 or 1:3
    	 * 1x encoding
    	 * Wheel circumference: ~7.75cm 
    	 * Encoder rotations to cm: targetCM/(7.75*Pi)*3 OR 
    	 */
    	this.targetLeftWheelRotations = (int) (targetLeftDistance/(7.75*Math.PI)*3);
    	this.targetRightWheelRotations = (int) (targetRightDistance/(7.75*Math.PI)*3);
    	this.lspeed = lspeed;
    	this.rspeed = rspeed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.driveEncoderLeft.reset();
    	RobotMap.driveEncoderRight.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.drive(lspeed, rspeed, false, false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	int leftWheelRotations = RobotMap.driveEncoderRight.get();
    	int rightWheelRotations = RobotMap.driveEncoderLeft.get();  //returning the count of the encoder
    	
    	//int leftWheelRotations = targetLeftWheelRotations; //Hard code override TODO replace when we have another encoder
    	
    	if((leftWheelRotations >= targetLeftWheelRotations && rightWheelRotations >= targetRightWheelRotations) || RobotMap.driveEncoderRight.getStopped()){
    		//if we have hit our target rotations or we have stopped (crashed) then stop trying to drive
    		
    		return true;
    	}
    	
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.drive(0.0d, 0.0d, false, false);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
