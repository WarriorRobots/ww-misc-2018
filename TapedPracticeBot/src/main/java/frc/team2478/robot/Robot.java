package frc.team2478.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends IterativeRobot {
    WPI_TalonSRX leftFront, leftMiddle, leftBack, rightFront, rightMiddle, rightBack;
    SpeedControllerGroup leftGroup, rightGroup;
    DifferentialDrive drive;
    
    Joystick leftJoy, rightJoy;
    XboxController xbox;
	
	@Override
    public void robotInit() {
		leftFront = new WPI_TalonSRX(2);
		leftMiddle = new WPI_TalonSRX(4);
		leftBack = new WPI_TalonSRX(9);
		rightFront = new WPI_TalonSRX(6);
		rightMiddle = new WPI_TalonSRX(7);
		rightBack = new WPI_TalonSRX(8);
        leftGroup = new SpeedControllerGroup(leftFront, leftMiddle, leftBack);
        rightGroup = new SpeedControllerGroup(rightFront, rightMiddle, rightBack);
        drive = new DifferentialDrive(leftGroup, rightGroup);
        
        leftJoy = new Joystick(1);
        rightJoy = new Joystick(0);
        xbox = new XboxController(2);
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
    public void disabledPeriodic() { }
    
    @Override
    public void autonomousPeriodic() { }

    @Override
    public void teleopPeriodic() {
    	double left = leftJoy.getY();
    	double right = rightJoy.getY();
    	drive.tankDrive(left, right, true);
    }

    @Override
    public void testPeriodic() { }
}