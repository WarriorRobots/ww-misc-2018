/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package src.org.usfirst.frc.team2478.robot;

import org.usfirst.frc.team2478.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public WPI_TalonSRX masterMotor, slaveMotor;
	public boolean pidEnabled;
	public double p, i, d, f;
	
	@Override
	public void robotInit() {
		System.out.println("robotInit()");
		// initialize both talons, one master and one slave
		masterMotor = new WPI_TalonSRX(Constants.MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(Constants.SLAVE_MOTOR);
		masterMotor.setInverted(true);
		slaveMotor.follow(masterMotor);

		// relative = quadrature, absolute = PWM
		masterMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,
												 Constants.PROCESS_ID,
												 Constants.PID_TIMEOUT_MS);
		// align sensor to motor direction
		masterMotor.setSensorPhase(true);
		
		// set Talon default outputs (0 and 0) output limits (-1 to 1)
//		masterMotor.configNominalOutputForward(0, Constants.PID_TIMEOUT_MS);
//		masterMotor.configNominalOutputReverse(0, Constants.PID_TIMEOUT_MS);
//		masterMotor.configPeakOutputForward(1, Constants.PID_TIMEOUT_MS);
//		masterMotor.configPeakOutputReverse(-1, Constants.PID_TIMEOUT_MS);
	}

	@Override
	public void teleopInit() {
		System.out.println("teleopInit()");
		// put editable fields on dashboard
		SmartDashboard.putNumber("P", p);
		SmartDashboard.putNumber("I", i);
		SmartDashboard.putNumber("D", d);
		SmartDashboard.putNumber("F", f);
		SmartDashboard.putNumber("Percentage Motor Speed", 0);
		SmartDashboard.putNumber("PID Motor Target", 0);
		SmartDashboard.putBoolean("PID enabled?", pidEnabled);
	}
	
	@Override
	public void teleopPeriodic() {
		System.out.println("teleopPeriodic()");
		SmartDashboard.putNumber("PID Motor Output", masterMotor.getSelectedSensorVelocity(Constants.PROCESS_ID));
		
		// get PID constants from Dashboard fields
		p = SmartDashboard.getNumber("P", 0);
		i = SmartDashboard.getNumber("I", 0);
		d = SmartDashboard.getNumber("D", 0);
		f = SmartDashboard.getNumber("F", 0);

		// send PID values to master motor
		masterMotor.config_kP(Constants.PROCESS_ID, p, Constants.PID_TIMEOUT_MS);
		masterMotor.config_kI(Constants.PROCESS_ID, i, Constants.PID_TIMEOUT_MS);
		masterMotor.config_kD(Constants.PROCESS_ID, d, Constants.PID_TIMEOUT_MS); 
		masterMotor.config_kF(Constants.PROCESS_ID, f, Constants.PID_TIMEOUT_MS);

		// get PID enable button state from dashboard
		pidEnabled = SmartDashboard.getBoolean("PID enabled?", false);
		
		// run PID if button is true, run percentage output if not
		if (pidEnabled==true) {
			DriverStation.reportError("PID not tuned", false);
			masterMotor.set(ControlMode.Velocity, SmartDashboard.getNumber("PID Motor Target", Constants.SHOOTER_SPEED_TARGET_DEFAULT));
		} else {
			masterMotor.set(ControlMode.PercentOutput, SmartDashboard.getNumber("Percentage Motor Speed", Constants.SHOOTER_SPEED_PERCENT_DEFAULT));
		}
	}
	
	@Override
	public void disabledInit() {
		masterMotor.stopMotor();
	}
}
