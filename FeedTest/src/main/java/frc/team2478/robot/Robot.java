package frc.team2478.robot;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
    
	public static final double RPM_CONVERSION = 600.0 / 4096.0;
	public static final double GEARBOX_RATIO = 5.0 / 1.0;
	
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
    }

    @Override
    public void robotPeriodic() {
        if (SmartDashboard.getBoolean("FEED/SHOOTER RESET", false)) {
            SmartDashboard.putNumber("SET FEED RPM", 0);
            SmartDashboard.putNumber("SET SHOOTER RPM", 0);
            SmartDashboard.putBoolean("FEED/SHOOTER RESET", false);
        }
    }
    
    @Override
    public void disabledInit() {
    	c = 0;
    	feedMasterMotor.stopMotor();
    	shooterMasterMotor.stopMotor();
        SmartDashboard.putBoolean("FEED/SHOOTER RESET", false);
    }

    @Override
    public void teleopPeriodic() {
//        feedMasterMotor.set(SmartDashboard.getNumber("SET FEED RPM", 0));
//        shooterMasterMotor.set(SmartDashboard.getNumber("SET SHOOTER RPM", 0));
    	shooterMasterMotor.set(0.5);
    	if (c > 100) {
    		feedMasterMotor.set(1);
    	}
        SmartDashboard.putNumber("CURRENT VELOCITY", velocityToRpm(shooterMasterMotor.getSelectedSensorVelocity(0)));
        c++;
        System.out.println(c);
    }

    //-----------------------//
    
    public static double velocityToRpm(double vel) {
		return (vel / GEARBOX_RATIO) * RPM_CONVERSION;
	}
    
    public static double rpmToVelocity(double rpm) {
		return (rpm / RPM_CONVERSION) * GEARBOX_RATIO;
	}

}