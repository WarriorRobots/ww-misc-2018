package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team2478.robot.commands.RunMotorAtPercentage;

public class WheelMotor extends Subsystem {

	private static WheelMotor singletonInstance;
	private WPI_TalonSRX wheelMotor;
	
	private WheelMotor() {
		wheelMotor = new WPI_TalonSRX(1);
	}
	
	public static WheelMotor getInstance() {
		if (singletonInstance == null) {
			singletonInstance = new WheelMotor();
		}
		return singletonInstance;
	}
	
	public void setMotor(double speed) {
		wheelMotor.set(speed);
	}
	
	public void stopMotor() {
		wheelMotor.stopMotor();
	}
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(new RunMotorAtPercentage());
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("motor");
		builder.addDoubleProperty("Current Draw (amps)", () -> wheelMotor.getOutputCurrent(), null);
		builder.addDoubleProperty("Speed (percentage)", () -> wheelMotor.get(), null);
	}
}
