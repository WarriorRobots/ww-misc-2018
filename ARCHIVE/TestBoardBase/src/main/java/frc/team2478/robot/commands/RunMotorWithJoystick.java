package frc.team2478.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2478.robot.ControlHandler;
import frc.team2478.robot.subsystems.WheelMotor;

public class RunMotorWithJoystick extends Command {

	public RunMotorWithJoystick() {
		requires(WheelMotor.getInstance());
	}
	
	@Override
	protected void execute() {
		WheelMotor.getInstance().setMotor(ControlHandler.getInstance().getRightY());
		SmartDashboard.putNumber("speed", ControlHandler.getInstance().getRightY());
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}
}
