package frc.team2478.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	private static final int SHOOTER_MASTER_ID = 5;
	private static final int SHOOTER_SLAVE_ID = 3;
	private WPI_TalonSRX shooterMasterMotor, shooterSlaveMotor;
	
    private static final int FEED_MASTER_ID = 1;
    private static final int FEED_SLAVE_ID = 2; 
    private WPI_TalonSRX feedMasterMotor, feedSlaveMotor;
    
    private static int c = 0;

    @Override
    public void robotInit() {
        feedMasterMotor = new WPI_TalonSRX(FEED_MASTER_ID);
        feedSlaveMotor = new WPI_TalonSRX(FEED_SLAVE_ID);
        feedSlaveMotor.follow(feedMasterMotor);
        
        shooterMasterMotor = new WPI_TalonSRX(SHOOTER_MASTER_ID);
        shooterSlaveMotor = new WPI_TalonSRX(SHOOTER_SLAVE_ID);
        shooterSlaveMotor.follow(shooterMasterMotor);
        shooterSlaveMotor.setInverted(true);
        shooterMasterMotor.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder, 0, 10);
        
        SmartDashboard.putBoolean("Refresh", true);
        SmartDashboard.putBoolean("FEED/SHOOTER RESET", false);
    }

    @Override
    public void robotPeriodic() {
    	
        if (SmartDashboard.getBoolean("Refresh", true)){
        	
        	 // The reason for the embeded get statements is to perserve an already existing value
        	SmartDashboard.putNumber("Shooter P gain", SmartDashboard.getNumber("Shooter P gain", Constants.P));
        	SmartDashboard.putNumber("Shooter I gain", SmartDashboard.getNumber("Shooter I gain", Constants.I));
        	SmartDashboard.putNumber("Shooter D gain", SmartDashboard.getNumber("Shooter D gain", Constants.D));
        	SmartDashboard.putNumber("Shooter F gain", SmartDashboard.getNumber("Shooter F gain", Constants.F));
        	
        	SmartDashboard.putBoolean("Closed Loop", true);
        	SmartDashboard.putBoolean("Refresh", false);
        	SmartDashboard.putBoolean("FEED/SHOOTER RESET", true);
        };
        
        if (SmartDashboard.getBoolean("FEED/SHOOTER RESET", true)) {

        	SmartDashboard.putNumber("SET FEED %", Constants.FEED_DEFAULT);
            SmartDashboard.putNumber("SET SHOOTER Velocity", Constants.SHOOTER_DEFAULT);
            SmartDashboard.putNumber("SET SHOOTER %", Constants.SHOOTER_DEFAULT_RPM);
            
        	SmartDashboard.putNumber("CURRENT VELOCITY", 0);
            SmartDashboard.putNumber("CURRENT RPM", 0);
            
            SmartDashboard.putBoolean("FEED/SHOOTER RESET", false);
        }
    }
    
    @Override
    public void disabledInit() {
    	c = 0;
    	feedMasterMotor.stopMotor();
    	shooterMasterMotor.stopMotor();
    	SmartDashboard.putNumber("CURRENT VELOCITY", 0);
        SmartDashboard.putNumber("CURRENT RPM", 0);
    }

    @Override
    public void teleopPeriodic() {
    	shooterMasterMotor.config_kP(Constants.PROCESS_ID, SmartDashboard.getNumber("Shooter P gain", Constants.P), Constants.TIMEOUT_MS);
		shooterMasterMotor.config_kI(Constants.PROCESS_ID, SmartDashboard.getNumber("Shooter I gain", Constants.I), Constants.TIMEOUT_MS);
		shooterMasterMotor.config_kD(Constants.PROCESS_ID, SmartDashboard.getNumber("Shooter D gain", Constants.D), Constants.TIMEOUT_MS); 
		shooterMasterMotor.config_kF(Constants.PROCESS_ID, SmartDashboard.getNumber("Shooter F gain", Constants.F), Constants.TIMEOUT_MS);
		
		double shooterVelo = SmartDashboard.getNumber("SET SHOOTER Velocity", 0);
		double shooterPerc = SmartDashboard.getNumber("SET SHOOTER %", 0);
		if (SmartDashboard.getBoolean("Closed Loop", true)) {
			shooterMasterMotor.set(ControlMode.Velocity, shooterVelo);
		}
		else {
			shooterMasterMotor.set(ControlMode.PercentOutput, shooterPerc);
		}
		
		feedMasterMotor.set(SmartDashboard.getNumber("SET FEED %", 0));
    	if (c < 100) {
    		feedMasterMotor.set(0);
    	}
        SmartDashboard.putNumber("CURRENT VELOCITY", shooterMasterMotor.getSelectedSensorVelocity(0));
        SmartDashboard.putNumber("CURRENT RPM", Constants.velocityToRpm(shooterMasterMotor.getSelectedSensorVelocity(0)));
        c++;
        System.out.println(c);
    }
}