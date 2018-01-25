package frc.team2478.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
    DigitalInput sensor;
	
	@Override
    public void robotInit() {
		sensor = new DigitalInput(4);
	}

    @Override
    public void disabledInit() { }

    @Override
    public void autonomousInit() { }

    @Override
    public void teleopInit() { }

    @Override
    public void testInit() { }

    @Override
    public void robotPeriodic() {
    	SmartDashboard.putBoolean("DIO3", sensor.get());
    }

    @Override
    public void disabledPeriodic() { }
    
    @Override
    public void autonomousPeriodic() { }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testPeriodic() { }
}