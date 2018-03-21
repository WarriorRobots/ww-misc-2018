package frc.team2478.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class ControlHandler {
	
	private static ControlHandler singletonInstance;
	private XboxController xbox;
	
	private ControlHandler() {
		xbox = new XboxController(0);
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
