package frc.team2478.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.team2478.robot.subsystems.DrivetrainSubsystem;
import frc.team2478.robot.subsystems.IntakeSubsystem;
import frc.team2478.robot.subsystems.PneumaticSubsystem;

public class Robot extends TimedRobot {
    
	public static final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
	public static final PneumaticSubsystem pneumatics = new PneumaticSubsystem();
	public static final IntakeSubsystem intake = new IntakeSubsystem();
	
	@Override
	public void robotInit() {
		drivetrain.init();
		pneumatics.init();
	}
	
	@Override
	public void disabledInit() {
		
	}
	
	@Override
	public void autonomousInit() {
		
	}
	
	@Override
	public void autonomousPeriodic() {
		
	}
	
	@Override
	public void teleopInit() {
		
	}
	
	@Override
	public void teleopPeriodic() {
		
	}
	
}