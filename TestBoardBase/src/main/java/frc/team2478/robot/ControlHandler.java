package frc.team2478.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team2478.robot.commands.RunMotorWithDashboard;
import frc.team2478.robot.commands.RunMotorWithJoystick;

public class ControlHandler {
	
	private static ControlHandler singletonInstance;
	private XboxController xbox;
	private JoystickButton buttonA, buttonB;
	
	private ControlHandler() {
		xbox = new XboxController(0);
		buttonA = new JoystickButton(xbox, 1);
		buttonA.whenPressed(new RunMotorWithJoystick());
		buttonB = new JoystickButton(xbox, 2);
		buttonB.whenPressed(new RunMotorWithDashboard());
	}
	
	public static ControlHandler getInstance() {
		if (singletonInstance == null) {
			singletonInstance = new ControlHandler();
		}
		return singletonInstance;
	}
	
	public double getLeftY() {
		return -xbox.getY(Hand.kLeft);
	}

	public double getRightY() {
		return -xbox.getY(Hand.kRight);
	}
	
	public boolean getAButton() {
		return xbox.getAButton();
	}
	
	public boolean getBButton() {
		return xbox.getBButton();
	}
	
	public boolean getXButton() {
		return xbox.getXButton();
	}
	
	public boolean getYButton() {
		return xbox.getYButton();
	}
}
