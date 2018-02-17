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
	
	/**
	 * ENCODER_UNITS_TO_RPM_CONVERSION is 600 / 4096 in 100ms/clicks.
	 * Also equal to {@value}.
	 */
	public static final double ENCODER_UNITS_TO_RPM_CONVERSION = 600.0 / 4096.0; //0.1465
	/**
	 * Out_per_in_GEARBOX is for every 5 rotations of the motor, the shooter rotatates 1 time
	 * Equal to {@value}.
	 */
	public static final double Out_per_in_GEARBOX = 1.0 / 5.0;
	
	/**
	 * Convert a velocity to an RPM
	 * @param vel Velocity In clicks per 100ms
	 * @return Velocity in rotations/min
	 */
    public static double velocityToRpm(double vel) {
		return vel * ENCODER_UNITS_TO_RPM_CONVERSION;
	}
    
    
    /**
	 * Convert a velocity to an RPM
	 * @param vel Velocity In clicks per 100ms
	 * @return Velocity in rotations/min
	 */
    public static double rpmToVelocity(double rpm) {
		return rpm / ENCODER_UNITS_TO_RPM_CONVERSION;
	}
    
    /**
     * Convert the rotation of the shooter into the rotations of the motor
     * @param out The outward shooter speed.
     * @return The inward motor speed.
     */
    public static double gearboxIn(double out) {
    	return out/Out_per_in_GEARBOX;
    }
    
    /**
     * Convert the rotation of the motor into the rotations of the shooter
     * @param in The inward motor speed.
     * @return The outward shooter speed.
     */
    public static double gearboxOut(double in) {
    	return in*Out_per_in_GEARBOX;
    }

}