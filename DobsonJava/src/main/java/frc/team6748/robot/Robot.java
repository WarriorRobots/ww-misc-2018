package frc.team6748.robot;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends IterativeRobot {
	
	Spark leftMotors, rightMotors;
	XboxController xbox;
	DifferentialDrive drivetrain;
	
	Timer autoTimer;
	
	@Override
	public void robotInit() {
		leftMotors = new Spark(0);
		rightMotors = new Spark(1);
		drivetrain = new DifferentialDrive(leftMotors, rightMotors);
		
		xbox = new XboxController(0);
		autoTimer = new Timer();
	}
	
	@Override
	public void autonomousInit() {
		autoTimer.reset();
		autoTimer.start();
	}
	
	@Override
	public void autonomousPeriodic() {
		if (autoTimer.get() < 4) {
			drivetrain.arcadeDrive(0.75, 0);
		} else {
			drivetrain.stopMotor();
		}
	}
	
	@Override
	public void teleopPeriodic() {
		double leftTrigger = xbox.getTriggerAxis(Hand.kLeft);
		double rightTrigger = xbox.getTriggerAxis(Hand.kRight);
		double leftJoyX = xbox.getX(Hand.kLeft);
		
		if (rightTrigger > 0.05) {
			drivetrain.arcadeDrive(rightTrigger, leftJoyX);
		} else if (leftTrigger > 0.05) {
			drivetrain.arcadeDrive(-leftTrigger, leftJoyX);
		} else {
			drivetrain.arcadeDrive(0, leftJoyX);
		}
	}
    
}