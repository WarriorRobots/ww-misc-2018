package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;
import frc.team2478.robot.subsystems.WheelMotor;

public class RunMotorWithDashboard extends Command {

	public RunMotorWithDashboard() {
		requires(WheelMotor.getInstance());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("Wheel Motor manual control");
		builder.addDoubleProperty("Set Wheel Motor Speed",
				() -> WheelMotor.getInstance().getSpeed(),
				(double percentage) -> WheelMotor.getInstance().setMotor(percentage));
	}
}
