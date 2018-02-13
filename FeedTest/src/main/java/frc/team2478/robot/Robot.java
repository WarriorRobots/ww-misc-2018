package frc.team2478.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
    
    private static final int MASTER_ID = 1;
    private static final int SLAVE_ID = 2; 
    private WPI_TalonSRX masterMotor, slaveMotor;

    @Override
    public void robotInit() {
        masterMotor = new WPI_TalonSRX(MASTER_ID); // right
        slaveMotor = new WPI_TalonSRX(SLAVE_ID); // left
        slaveMotor.follow(masterMotor);
    }

    @Override
    public void disabledInit() {
    	masterMotor.stopMotor();
        SmartDashboard.putBoolean("FEED RESET", false);
    }

    //-----------------------//

    @Override
    public void disabledPeriodic() {
        if (SmartDashboard.getBoolean("FEED RESET", false)) {
            SmartDashboard.putNumber("SET FEED", 0);
            SmartDashboard.putBoolean("FEED RESET", true);
        }
    }

    @Override
    public void teleopPeriodic() {
        masterMotor.set(SmartDashboard.getNumber("SET FEED", 0));
        SmartDashboard.putNumber("CURRENT FEED SPEED", masterMotor.getMotorOutputPercent());
    }

}