package frc.team2478.robot;

public class Constants {
	
	public static final int TIMEOUT_MS = 10;
	public static final int PROCESS_ID = 0;
	
	public static final double P = 0.04;
	public static final double I = 0;
	public static final double D = 1.3;
	public static final double F = 0.00825;
	
	public static final double SHOOTER_DEFAULT = 1000;
	public static final double FEED_DEFAULT = 1;
	public static final double SHOOTER_DEFAULT_RPM = 0.4;
	
	public static final double RPM_CONVERSION = (double)600 / (double)4096;
	public static final double GEARBOX_RATIO = (double)5 / (double)1;
	
    public static double velocityToRpm(double vel) {
		return (vel / GEARBOX_RATIO) * RPM_CONVERSION;
	}
    
    public static double rpmToVelocity(double rpm) {
		return (rpm / RPM_CONVERSION) * GEARBOX_RATIO;
	}

}
