package transfer.tap.base;

import java.io.Serializable;

public class Demand implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public final int fromId;
	public final int toId;
	public final double volume;
	
	public Demand(int fromId, int toId, double volume) {
		this.fromId = fromId;
		this.toId = toId;
		this.volume = volume;
	}
	
	@Override
	public String toString() {
		return "Demand(from:" + fromId + ",to:" + toId + ",volume:" + volume + ")";
	}
}
