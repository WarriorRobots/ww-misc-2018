/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2478.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.SpeedControllerGroup;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {

	public WPI_TalonSRX leftMotor, rightMotor;
	public SpeedControllerGroup shooterGroup;
	
	@Override
	public void robotInit() {
		leftMotor = new WPI_TalonSRX(Constants.LEFT_MOTOR);
		rightMotor = new WPI_TalonSRX(Constants.RIGHT_MOTOR);
		shooterGroup = new SpeedControllerGroup(leftMotor, rightMotor);
	}

	@Override
	public void teleopInit() {}
	
	@Override
	public void teleopPeriodic() {
		shooterGroup.set(Constants.SHOOTER_SPEED);

	}
	
	@Override
	public void disabledInit() {
		shooterGroup.stopMotor();
	}
}
