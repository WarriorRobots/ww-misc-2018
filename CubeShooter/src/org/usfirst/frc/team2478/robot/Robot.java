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
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {

	public WPI_TalonSRX masterMotor, slaveMotor;
	public Timer pidTimer;
	public double shooterSpeedPercent, shooterSpeedActual, shooterSpeedTarget = 0;
	public boolean pidEnabled;
	public double p, i, d, f;
	
	@Override
	public void robotInit() {
		// initialize both talons, one master and one slave
		masterMotor = new WPI_TalonSRX(Constants.MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(Constants.SLAVE_MOTOR);

		// relative = quadrature, absolute = PWM
		masterMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative,
												 Constants.PID_ID,
												 Constants.PID_TIMEOUT_MS);
		// align sensor to motor direction
		masterMotor.setSensorPhase(true);
		
		// put editable fields on dashboard
		SmartDashboard.putNumber("P", p);
		SmartDashboard.putNumber("I", i);
		SmartDashboard.putNumber("D", d);
		SmartDashboard.putNumber("F", f);
		SmartDashboard.putNumber("Percentage Motor Speed", shooterSpeedPercent);
		SmartDashboard.putNumber("PID Motor Target", shooterSpeedTarget);
		SmartDashboard.putBoolean("PID enabled?", pidEnabled);
		SmartDashboard.setDefaultNumber("Percentage Motor Speed", Constants.SHOOTER_SPEED_PERCENT_DEFAULT);

		// set Talon default outputs (0 and 0) output limits (-1 to 1)
        masterMotor.configNominalOutputForward(0, Constants.PID_TIMEOUT_MS);
        masterMotor.configNominalOutputReverse(0, Constants.PID_TIMEOUT_MS);
        masterMotor.configPeakOutputForward(1, Constants.PID_TIMEOUT_MS);
        masterMotor.configPeakOutputReverse(-1, Constants.PID_TIMEOUT_MS);
	}

	@Override
	public void teleopInit() {
	}
	
	@Override
	public void teleopPeriodic() {
		// get shooterSpeed variables from Dashboard fields
		shooterSpeedTarget = SmartDashboard.getNumber("PID Motor Target", Constants.SHOOTER_SPEED_TARGET_DEFAULT);
		shooterSpeedPercent = SmartDashboard.getNumber("Percentage Motor Speed", Constants.SHOOTER_SPEED_PERCENT_DEFAULT);
		shooterSpeedActual = masterMotor.getSelectedSensorVelocity(Constants.PID_ID);
		SmartDashboard.putNumber("PID Motor Output", shooterSpeedActual);
		
		// get PID constants from Dashboard fields
		p = SmartDashboard.getNumber("P", 0);
		i = SmartDashboard.getNumber("I", 0);
		d = SmartDashboard.getNumber("D", 0);
		f = SmartDashboard.getNumber("F", 0);

		// send PID values to master motor
		masterMotor.config_kF(Constants.PID_ID, p, Constants.PID_TIMEOUT_MS);
        masterMotor.config_kP(Constants.PID_ID, i, Constants.PID_TIMEOUT_MS);
        masterMotor.config_kI(Constants.PID_ID, d, Constants.PID_TIMEOUT_MS); 
        masterMotor.config_kD(Constants.PID_ID, f, Constants.PID_TIMEOUT_MS);
		
		// get PID enable button state from dashboard
		pidEnabled = SmartDashboard.getBoolean("PID enabled?", false);
		
		// run PID if button is true, run percentage output if not
		if (pidEnabled) {
			DriverStation.reportError("PID not tuned", false);
			masterMotor.set(ControlMode.Velocity, shooterSpeedTarget);
		} else {
			masterMotor.set(ControlMode.PercentOutput, shooterSpeedPercent);
		}
	}
	
	@Override
	public void disabledInit() {
		masterMotor.stopMotor();
	}
}
