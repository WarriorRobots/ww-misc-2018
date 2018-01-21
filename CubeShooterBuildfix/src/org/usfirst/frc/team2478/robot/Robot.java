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

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	private WPI_TalonSRX masterMotor, slaveMotor;
	private boolean pidEnabled;
	
	@Override
	public void robotInit() {
		// initialize both talons, one master and one slave
		masterMotor = new WPI_TalonSRX(Constants.MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(Constants.SLAVE_MOTOR);
		slaveMotor.setInverted(true);
		slaveMotor.follow(masterMotor);

		// relative = quadrature, absolute = PWM
		masterMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,
												 Constants.PROCESS_ID,
												 Constants.PID_TIMEOUT_MS);
		// align sensor to motor direction
		masterMotor.setSensorPhase(true);
		
		// set Talon default outputs (0 and 0) output limits (-1 to 1)
		masterMotor.configNominalOutputForward(0, Constants.PID_TIMEOUT_MS);
		masterMotor.configNominalOutputReverse(0, Constants.PID_TIMEOUT_MS);
		masterMotor.configPeakOutputForward(1, Constants.PID_TIMEOUT_MS);
		masterMotor.configPeakOutputReverse(-1, Constants.PID_TIMEOUT_MS);
	}

	@Override
	public void teleopInit() {		
	SmartDashboard.putNumber("P gain", 0);
	SmartDashboard.putNumber("I gain", 0);
	SmartDashboard.putNumber("D gain", 0);
	SmartDashboard.putNumber("F gain", 0);
	SmartDashboard.putNumber("Percentage", 0);
	SmartDashboard.putNumber("PID Motor Target", 0);
	SmartDashboard.putBoolean("PID enabled", false);
	}
	
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("RPM", (masterMotor.getSelectedSensorVelocity(0) / 5) * ((double)600/(double)4096));
		
		// send PID values to master motor
		masterMotor.config_kP(Constants.PROCESS_ID, SmartDashboard.getNumber("P gain", 0), Constants.PID_TIMEOUT_MS);
		masterMotor.config_kI(Constants.PROCESS_ID, SmartDashboard.getNumber("I gain", 0), Constants.PID_TIMEOUT_MS);
		masterMotor.config_kD(Constants.PROCESS_ID, SmartDashboard.getNumber("D gain", 0), Constants.PID_TIMEOUT_MS); 
		masterMotor.config_kF(Constants.PROCESS_ID, SmartDashboard.getNumber("F gain", 0), Constants.PID_TIMEOUT_MS);

		// get PID enable button state from dashboard
		pidEnabled = SmartDashboard.getBoolean("PID enabled", false);
		
		// run PID if button is true, run percentage output if not
		if (pidEnabled==true) {
			DriverStation.reportError("PID not tuned", false);
//			masterMotor.set(ControlMode.Velocity, SmartDashboard.getNumber("PID Motor Target", Constants.SHOOTER_SPEED_TARGET_DEFAULT));
			masterMotor.config_kP(0, 0.01, 10);
			masterMotor.set(ControlMode.Velocity, 2500);
		} else {
			masterMotor.set(ControlMode.PercentOutput, SmartDashboard.getNumber("Percentage", Constants.SHOOTER_SPEED_PERCENT_DEFAULT));
		}
	}
	
	@Override
	public void disabledInit() {
		masterMotor.stopMotor();
	}
}