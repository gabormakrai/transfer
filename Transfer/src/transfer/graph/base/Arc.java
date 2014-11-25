package transfer.graph.base;

public class Arc {
	public final int id;
	public final int from;
	public final int to;
	public final double freeFlowTravelTime;
	public final double linkCapacity; 
	public double traffic;
	public Arc(int id, int from, int to, double freeFlowTravelTime, double linkCapacity) {
		this.id = id;
		this.from = from;
		this.to = to;
		this.freeFlowTravelTime = freeFlowTravelTime;
		this.linkCapacity = linkCapacity;
	}
}
