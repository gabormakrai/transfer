package transfer.graph.base;

import java.io.Serializable;

public class Arc implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
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
	
	public Arc(Arc arc) {
		this.id = arc.id;
		this.from = arc.from;
		this.to = arc.to;
		this.freeFlowTravelTime = arc.freeFlowTravelTime;
		this.linkCapacity = arc.linkCapacity;
	}
	
	@Override
	public String toString() {
		return "Arc(id:" + id + ",from:" + from + ",to:" + to + ",traffic:" + traffic +")";
	}
	
}
