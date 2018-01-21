/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2478.robot;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;

public class Robot extends IterativeRobot {

	Encoder testEncoder1 = new Encoder(0,1);
	Encoder testEncoder2 = new Encoder(2,3);
	
	@Override
	public void robotInit() {
		testEncoder1.reset();
		testEncoder2.reset();
	}
	
	@Override
	public void teleopPeriodic() {
		System.out.println("1: " + Integer.toString(testEncoder1.get()));
		System.out.println("2: " + Integer.toString(testEncoder2.get()));
	}
}
