package KBot;

import KBot.OperatorController;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick leftDriver = new Joystick(0);
	public Joystick rightDriver = new Joystick(1);
	
	// set up a button for sound here?
	public JoystickButton  soundButton = new JoystickButton(leftDriver,OperatorController.JOY_SOUND);
	
	public OI()
	{
		//soundButton.whenPressed(command); //@todo have to put in a joystick sound command
	}
	

}

