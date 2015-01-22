package transfer.performance;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;

public class TapSystemOptimalObjectiveFunction implements AbstractPerformance {
	
	Graph graph;

	public TapSystemOptimalObjectiveFunction(Graph graph) {
		this.graph = graph;
	}	
	
	@Override
	public double calculate() {
		double sum = 0.0;
		for (Arc arc : graph.arcArray) {
			sum += arc.traffic * travelTime(arc.traffic, arc.freeFlowTravelTime, arc.linkCapacity);
		}
		return sum;
	}
	
	private double travelTime(double trafficVolume, double freeFlowTravelTime, double capacity) {
		return freeFlowTravelTime * (1.0 + 0.15 * Math.pow(trafficVolume / capacity, 4.0));
	}

}
