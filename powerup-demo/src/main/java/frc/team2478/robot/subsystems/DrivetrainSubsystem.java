package frc.team2478.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class DrivetrainSubsystem extends Subsystem {

	private static final double RAMPRATE_SECONDS = 0.15;
	private static final int TIMEOUT_MS = 10;
	
	private WPI_TalonSRX leftFront, leftMiddle, leftBack, rightFront, rightMiddle, rightBack;
	private SpeedControllerGroup leftGroup, rightGroup;

	public Encoder leftEnc, rightEnc;
	public AHRS navx;
	public DifferentialDrive differentialDrive;
	
	public DrivetrainSubsystem() {
		leftFront = new WPI_TalonSRX(4);
		leftMiddle = new WPI_TalonSRX(5);
		leftBack = new WPI_TalonSRX(6);

		rightFront = new WPI_TalonSRX(1);
		rightMiddle = new WPI_TalonSRX(2);
		rightBack = new WPI_TalonSRX(3);

		leftGroup = new SpeedControllerGroup(leftFront, leftMiddle, leftBack);
		rightGroup = new SpeedControllerGroup(rightFront, rightMiddle, rightBack);

		differentialDrive = new DifferentialDrive(leftGroup, rightGroup);
		
		try {
			navx = new AHRS(I2C.Port.kMXP);
		} catch (Exception ex) {
			DriverStation.reportError(ex.getMessage(), true);
		}

		leftEnc = new Encoder(2, 3);
		rightEnc = new Encoder(0, 1);
	}
	
	public void init() {
		leftGroup.setInverted(true);
		rightGroup.setInverted(true);
	
		differentialDrive.setSafetyEnabled(true);
		
		leftEnc.setReverseDirection(true);
		rightEnc.setReverseDirection(false);
		
		leftFront.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
		leftMiddle.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
		leftBack.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
		
		rightFront.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
		rightMiddle.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
		rightBack.configOpenloopRamp(RAMPRATE_SECONDS, TIMEOUT_MS);
	}
	
	@Override
	protected void initDefaultCommand() {}

}
