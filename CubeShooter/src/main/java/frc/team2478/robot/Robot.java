package frc.team2478.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	// creates two private member Motor objects
	private WPI_TalonSRX masterMotor, slaveMotor;
	
	// runs at beginning of program, often BEFORE driver station is connected
	@Override
	public void robotInit() {
		// initializes Master and Slave motors to Talon IDs
		masterMotor = new WPI_TalonSRX(Constants.MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(Constants.SLAVE_MOTOR);
		
		// inverts slave motor and links it to the master
		slaveMotor.setInverted(true);
		slaveMotor.follow(masterMotor);

		// initializes Quadrature encoder to process ID 0, and with a failure timeout of 10ms
		masterMotor.configSelectedFeedbackSensor (
			FeedbackDevice.QuadEncoder,
			Constants.PROCESS_ID,
			Constants.TIMEOUT_MS
		);

		// flips Quadrature encoder to return positive when motor runs positively
		masterMotor.setSensorPhase(true);
	}

	@Override
	public void robotPeriodic() {
		// updates dashboard if button is true
		if (SmartDashboard.getBoolean("RESET DASHBOARD", false) == true) {
			this.updateDashboard();
			System.out.println("helloworld");
		}
	}
	
	@Override
	public void teleopPeriodic() {
		// sends RPM calculation to the dashboard, every loop
		SmartDashboard.putNumber("RPM", Constants.VelocityToRpm(masterMotor.getSelectedSensorVelocity(0)));
		
		// sends PID values to master motor, defaulting to 0 on failure
		masterMotor.config_kP(Constants.PROCESS_ID, SmartDashboard.getNumber("P gain", 0), Constants.TIMEOUT_MS);
		masterMotor.config_kI(Constants.PROCESS_ID, SmartDashboard.getNumber("I gain", 0), Constants.TIMEOUT_MS);
		masterMotor.config_kD(Constants.PROCESS_ID, SmartDashboard.getNumber("D gain", 0), Constants.TIMEOUT_MS); 
		masterMotor.config_kF(Constants.PROCESS_ID, SmartDashboard.getNumber("F gain", 0), Constants.TIMEOUT_MS);
		
		// enables PID if button is true, defaulting to false
		if (SmartDashboard.getBoolean("CLOSED LOOP", false)) {
			DriverStation.reportError("PID not tuned", false);
			masterMotor.set(ControlMode.Velocity,
							Constants.RpmToVelocity(SmartDashboard.getNumber("TARGET RPM", 0)));
			System.out.println(Constants.RpmToVelocity(SmartDashboard.getNumber("TARGET RPM", 0)));
			masterMotor.set(ControlMode.Velocity, 2500);
		} else { // runs percentage output if button is false
			masterMotor.set(ControlMode.PercentOutput, SmartDashboard.getNumber("PERCENTAGE", 0));
		}
	}
	
	public void disabledInit() {
		SmartDashboard.putBoolean("RESET DASHBOARD", false);
	}
	
	public void updateDashboard() {
		// puts numbers on dashboard
		SmartDashboard.putNumber("P gain", 0);
		SmartDashboard.putNumber("I gain", 0);
		SmartDashboard.putNumber("D gain", 0);
		SmartDashboard.putNumber("F gain", 0);
		SmartDashboard.putNumber("PERCENTAGE", 0);
		SmartDashboard.putNumber("TARGET RPM", 0);
		SmartDashboard.putBoolean("CLOSED LOOP", false);
		SmartDashboard.putBoolean("RESET DASHBOARD", false);
	}
}