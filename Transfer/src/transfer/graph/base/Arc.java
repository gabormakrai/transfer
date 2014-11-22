package transfer.graph.base;

public class Arc {
	public final int from;
	public final int to;
	public final double freeFlowTravelTime;
	public final double linkCapacity; 
	public double traffic;
	public double travelTime;
	public Arc(int from, int to, double freeFlowTravelTime, double linkCapacity) {
		this.from = from;
		this.to = to;
		this.freeFlowTravelTime = freeFlowTravelTime;
		this.linkCapacity = linkCapacity;
	}
	public void recalculateTravelTime() {
		//TODO: not yet implemented
	}
}
