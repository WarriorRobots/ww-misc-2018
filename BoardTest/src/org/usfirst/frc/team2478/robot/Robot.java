package org.usfirst.frc.team2478.robot;

import edu.wpi.first.wpilibj.SimpleRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.networktables;

public class EasyNetworkTabeExample extends SimpleRobot{
	Network table;
	public void robotInit() {
		table = NetworkTable.getTable("SmartDashboard");
	}
	
	public void autonomous() {
		
	}
	
	public void operatorControl() {
		double x = 0;
		double y = 0;
		while (isOperatorControl() && isEnabled()) {
			Timer.delay(0.25);
			table.putNumber("X",x);
			table.putNumber("Y", y);
		}
	}
	
	
}