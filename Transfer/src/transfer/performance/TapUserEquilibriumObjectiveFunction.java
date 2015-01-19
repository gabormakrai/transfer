package transfer.performance;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;

public class TapUserEquilibriumObjectiveFunction implements AbstractPerformance {
	
	Graph graph;

	public TapUserEquilibriumObjectiveFunction(Graph graph) {
		this.graph = graph;
	}	
	
	@Override
	public double calculate() {
		double sum = 0.0;
		for (Arc arc : graph.arcArray) {
			sum += travelTimeIntegral(arc.traffic, arc.freeFlowTravelTime, arc.linkCapacity);
		}
		return sum;
	}
	
	private double travelTimeIntegral(double trafficVolume, double freeFlowTravelTime, double capacity) {
		return freeFlowTravelTime * (Math.pow(trafficVolume, 5.0) / (5.0 * Math.pow(capacity, 4.0) + trafficVolume));
	}

}
