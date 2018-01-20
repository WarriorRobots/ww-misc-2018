/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team2478.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	private WPI_TalonSRX masterMotor, slaveMotor;
	private static final double DEFAULT_MOTOR_SPEED = 0;

	@Override
	public void robotInit() {
		masterMotor = new WPI_TalonSRX(2);
		slaveMotor = new WPI_TalonSRX(1);
		masterMotor.configOpenloopRamp(1, 10);
		slaveMotor.setInverted(true);
		slaveMotor.follow(masterMotor);
		masterMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
		SmartDashboard.putNumber("Motor Speed Percentage", DEFAULT_MOTOR_SPEED);
	}

	@Override
	public void teleopPeriodic() {
		masterMotor.set(ControlMode.PercentOutput, SmartDashboard.getNumber("Motor Speed Percentage", DEFAULT_MOTOR_SPEED));
		
		SmartDashboard.putNumber("Current Motor Speed", masterMotor.getSelectedSensorVelocity(0));
	}
}