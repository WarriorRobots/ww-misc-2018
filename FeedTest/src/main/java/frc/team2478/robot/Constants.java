package frc.team2478.robot;

public class Constants {
	
	public static final int TIMEOUT_MS = 10;
	public static final int PROCESS_ID = 0;
	
	public static final double P = 0.04;
	public static final double I = 0;
	public static final double D = 1.3;
	public static final double F = 0.00825;
	
	public static final double SHOOTER_DEFAULT_RPM = 1000;
	public static final double FEED_DEFAULT_PERC = 1.0;
	public static final double SHOOTER_DEFAULT_PERC = 0.4;
	
	public static final double ENCODER_UNITS_TO_RPM_CONVERSION = 600.0 / 4096.0; // 0.1648
	public static final double GEARBOX_RATIO = 5.0 / 1.0;
	
//    public static double velocityToRpm(double vel) {
//		return (vel / GEARBOX_RATIO) * ENCODER_UNITS_TO_RPM_CONVERSION;
//	}

    public static double velocityToRpm(double vel) {
		return vel * ENCODER_UNITS_TO_RPM_CONVERSION;
	}
    
//    public static double rpmToVelocity(double rpm) {
//		return (rpm / ENCODER_UNITS_TO_RPM_CONVERSION) * GEARBOX_RATIO;
//	}
    
    public static double rpmToVelocity(double rpm) {
		return rpm / ENCODER_UNITS_TO_RPM_CONVERSION;
	}

}