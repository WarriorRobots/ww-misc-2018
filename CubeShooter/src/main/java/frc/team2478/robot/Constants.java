package frc.team2478.robot;

public class Constants {
	
	public static final int MASTER_MOTOR = 2;
	public static final int SLAVE_MOTOR = 1;

	public static final int TIMEOUT_MS = 10;
	public static final int PROCESS_ID = 0;

	public static final double RPM_CONVERSION = (double)600 / (double)4096;
	public static final double GEARBOX_RATIO = (double)5 / (double)1;

	public static double VelocityToRpm(double vel) {
		System.out.println(Double.toString(vel) + "native units");
		return (vel / GEARBOX_RATIO) * RPM_CONVERSION;
	}

	public static double RpmToVelocity(double rpm) {
		System.out.println(Double.toString((rpm / RPM_CONVERSION) * GEARBOX_RATIO) + " what the robot sees");
		return (rpm / RPM_CONVERSION) * GEARBOX_RATIO;
	}
	
}