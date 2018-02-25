package frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class InfaredSensor extends Subsystem {

	private static InfaredSensor singletonInstance;
	private DigitalInput infaredSensor;
	
	private InfaredSensor() {
		infaredSensor = new DigitalInput(0);
	}
	
	public static InfaredSensor getInstance() {
		if (singletonInstance == null) {
			singletonInstance = new InfaredSensor();
		}
		return singletonInstance;
	}
	
	public boolean getSensorState() {
		return !infaredSensor.get();
	}
	
	@Override
	protected void initDefaultCommand() {}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("InfaredSensor");
		builder.addBooleanProperty("Sensor Activated?", () -> getSensorState(), null);
	}
}
