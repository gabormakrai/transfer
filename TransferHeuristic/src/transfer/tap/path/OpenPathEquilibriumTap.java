package transfer.tap.path;

import transfer.tap.path.PathEquilibriumTap;

public class OpenPathEquilibriumTap extends PathEquilibriumTap {

	public OpenPathEquilibriumTap(double alpha) {
		super(alpha);
	}
	
	public ODPaths[] getODPaths() {
		return this.odPaths;
	}

}
