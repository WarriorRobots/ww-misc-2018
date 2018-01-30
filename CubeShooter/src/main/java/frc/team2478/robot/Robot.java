package frc.team2478.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	private WPI_TalonSRX masterMotor, slaveMotor;
	private SendableChooser<Double> chooser = new SendableChooser<>();
	
	@Override
	public void robotInit() {
		masterMotor = new WPI_TalonSRX(Constants.MASTER_MOTOR);
		slaveMotor = new WPI_TalonSRX(Constants.SLAVE_MOTOR);
		
		// inverts slave motor and links it to the master
		slaveMotor.setInverted(true);
		slaveMotor.follow(masterMotor);

		// initializes Quadrature encoder to process ID 0, and with a failure timeout of 10ms
		masterMotor.configSelectedFeedbackSensor(
			FeedbackDevice.QuadEncoder,
			Constants.PROCESS_ID,
			Constants.TIMEOUT_MS
		);

		// flips Quadrature encoder direction to match motor
		masterMotor.setSensorPhase(true);
		
		// adds RPM options to Dashboard radio buttons
		chooser.addDefault("MID", new Double(Constants.RPM_MID));
		chooser.addObject("LOW", new Double(Constants.RPM_LOW));
		chooser.addObject("HIGH", new Double(Constants.RPM_HIGH));
	}

	@Override
	public void robotPeriodic() {
		// updates dashboard if button is true
		if (SmartDashboard.getBoolean("RESET DASHBOARD", false) == true) {
			this.updateDashboard();
		}
	}
	
	@Override
	public void teleopPeriodic() {
		SmartDashboard.putNumber("RPM", Constants.VelocityToRpm(masterMotor.getSelectedSensorVelocity(0)));
		
		masterMotor.config_kP(Constants.PROCESS_ID, SmartDashboard.getNumber("P gain", Constants.P), Constants.TIMEOUT_MS);
		masterMotor.config_kI(Constants.PROCESS_ID, SmartDashboard.getNumber("I gain", Constants.I), Constants.TIMEOUT_MS);
		masterMotor.config_kD(Constants.PROCESS_ID, SmartDashboard.getNumber("D gain", Constants.D), Constants.TIMEOUT_MS); 
		masterMotor.config_kF(Constants.PROCESS_ID, SmartDashboard.getNumber("F gain", Constants.F), Constants.TIMEOUT_MS);
		
		// PID enabled
		if (SmartDashboard.getBoolean("CLOSED LOOP", false)) {
			masterMotor.set(ControlMode.Velocity, Constants.RpmToVelocity(chooser.getSelected()));
		} else { // percentage output if PID disabled
			masterMotor.set(ControlMode.PercentOutput, SmartDashboard.getNumber("PERCENTAGE", 0));
		}
	}
	
	public void disabledInit() {
		// puts reset button on dashboard if it is missing
		SmartDashboard.putBoolean("RESET DASHBOARD", false);
	}
	
	public void updateDashboard() {
		// puts widgets on dashboard
		SmartDashboard.putNumber("P gain", Constants.P);
		SmartDashboard.putNumber("I gain", Constants.I);
		SmartDashboard.putNumber("D gain", Constants.D);
		SmartDashboard.putNumber("F gain", Constants.F);
		SmartDashboard.putNumber("PERCENTAGE", Constants.PERCENTAGE_DEFAULT); // equivalent to 2000 RPM
		SmartDashboard.putBoolean("CLOSED LOOP", true);
		SmartDashboard.putData(chooser);
		SmartDashboard.putBoolean("RESET DASHBOARD", false);
	}
}