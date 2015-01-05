package transfer.tap.base;

public class Demand {
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
