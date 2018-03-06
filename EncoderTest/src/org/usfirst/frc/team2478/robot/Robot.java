/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2478.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.networktables.*;

public class Robot extends IterativeRobot {

	NetworkTableInstance table;
	public void robotInit() {
		table = NetworkTableInstance.getDefault();
	}
	
	public void autonomous() {

	}
	
	public void operatorControl() {
		double x = 0;
		double y = 0;
		while (isOperatorControl() && isEnabled()) {
			Timer.delay(0.25);
			NetworkTableValue.makeDouble(20);
		}
	}
	
	
}
