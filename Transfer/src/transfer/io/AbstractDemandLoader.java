package transfer.io;

import transfer.tap.base.Demand;

public interface AbstractDemandLoader {
	public Demand[] loadFromFile(String fileName);
}
