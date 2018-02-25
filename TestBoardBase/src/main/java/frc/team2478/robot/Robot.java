package frc.team2478.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team2478.robot.subsystems.InfaredSensor;
import frc.team2478.robot.subsystems.WheelMotor;

public class Robot extends TimedRobot {
	
	@Override
	public void robotInit() {
		SmartDashboard.putData(InfaredSensor.getInstance());
		SmartDashboard.putData(WheelMotor.getInstance());
	}
	
    @Override
    public void teleopPeriodic() {
    	Scheduler.getInstance().run();
    }
}