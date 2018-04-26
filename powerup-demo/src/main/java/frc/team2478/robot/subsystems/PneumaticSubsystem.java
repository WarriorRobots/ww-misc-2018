package frc.team2478.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableBuilder;

public class PneumaticSubsystem extends Subsystem {

	public Compressor compressor;
	public DoubleSolenoid hoodSolenoid, pickupSolenoid;
	
	private boolean hoodLifted;
	private boolean pickupOut;
	
	public PneumaticSubsystem() {
		hoodSolenoid = new DoubleSolenoid(7, 0);
		pickupSolenoid = new DoubleSolenoid(6, 1);
		compressor = new Compressor();
	}
	
	public void init() {
		compressor.start();
		hoodLifted = false;
		pickupOut = false;
	}
	
	public boolean isHoodLifted() {
		return hoodLifted;
	}
	
	public boolean isPickupOut() {
		return pickupOut;
	}
	
	@Override
	protected void initDefaultCommand() {}
	
	@Override
	public void initSendable(SendableBuilder builder) {
		builder.setSmartDashboardType("subsystem-pneumatics");
		builder.addBooleanProperty("hood-up", () -> isHoodLifted(), null);
		builder.addBooleanProperty("pickup-out", () -> isPickupOut(), null);
	}

}
