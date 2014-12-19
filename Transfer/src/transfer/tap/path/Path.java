package transfer.tap.path;

import java.util.Arrays;

import transfer.graph.base.Arc;

public class Path {
	
	public final Arc[] arcs;
	public double volume;
	
	public Path(Arc[] arcs, double volume) {
		this.arcs = arcs;
		this.volume = volume;
	}
	public Path(Arc[] arcs) {
		this(arcs, 0.0);
	}
	
	@Override
	public String toString() {
		return "Path(volume:" + volume + ",arcs:" + Arrays.toString(arcs) + ")";
	}
	
	public boolean isSameArcs(Arc[] arcs) {
		if (this.arcs.length != arcs.length) {
			return false;
		}
		for (int i = 0; i < arcs.length; ++i) {
			if (this.arcs[i].id != arcs[i].id) {
				return false;
			}
		}
		return true;
	}
	
	public double calculateTravelTime() {
		double travelTime = 0.0;
		for (Arc arc : arcs) {
			travelTime += travelTime(arc.traffic, arc.freeFlowTravelTime, arc.linkCapacity); 
		}
		return travelTime;
	}
	
	private double travelTime(double trafficVolume, double freeFlowTravelTime, double capacity) {
		return freeFlowTravelTime * (1.0 + 0.15 * Math.pow(trafficVolume / capacity, 4.0));
	}
}
