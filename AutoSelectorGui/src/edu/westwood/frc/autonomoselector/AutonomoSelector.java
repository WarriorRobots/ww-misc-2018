package edu.westwood.frc.autonomoselector;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class AutonomoSelector {
	
	public static void main(String[] args) {
		new AutonomoSelector().run();
	}
	
	public void run() {
		NetworkTable randomTable = NetworkTableInstance.getDefault().getTable("randomTable");
		NetworkTableEntry position = randomTable.getEntry("position");
		NetworkTableEntry target = randomTable.getEntry("target");
		target.set
		
	}

}
