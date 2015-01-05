package transfer.performance;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;
import transfer.graph.sp.ShortestPathAlgorithm;
import transfer.tap.base.Demand;

public class RelativeGap implements AbstractPerformance {
	
	Graph graph;
	Demand[] demands;
	ShortestPathAlgorithm shortestPathAlgorithm;
	double[][] travelTime; 
	
	public RelativeGap(Graph graph, Demand[] demands, ShortestPathAlgorithm shortestPathAlgorithm) {
		this.graph = graph;
		this.demands = demands;
		this.shortestPathAlgorithm = shortestPathAlgorithm;
		
		travelTime = new double[graph.arcs.length][];
		for (int i = 0; i < graph.arcs.length; ++i) {
			if (graph.arcs[i] == null) {
				travelTime[i] = null;
			} else {
				travelTime[i] = new double[graph.arcs[i].length];
				for (int j = 0; j < travelTime[i].length; ++j) {
					travelTime[i][j] = 0.0;
				}
			}
		}
		
	}
	
	@Override
	public double calculate() {
		calculateTravelTimes();
		
		double A = 0.0;
		
		for (Demand demand : demands) {
			
			Arc[] shortestPath = shortestPathAlgorithm.shortestPath(graph, travelTime, demand.fromId, demand.toId);
			
			if (shortestPath == null) {
				continue;
			}
			
			for (Arc arc : shortestPath) {
				A += demand.volume * travelTime(arc.traffic, arc.freeFlowTravelTime, arc.linkCapacity);
			}
		}
		
		
		double B = 0.0;
		for (Arc arc : graph.arcArray) {
			B += arc.traffic * travelTime(arc.traffic, arc.freeFlowTravelTime, arc.linkCapacity); 
		}
		
		return 1.0 - A / B;
	}
	
	private void calculateTravelTimes() {
		for (int i = 0; i < graph.arcs.length; ++i) {
			if (graph.arcs[i] == null) {
				continue;
			} else {
				for (int j = 0; j < travelTime[i].length; ++j) {
					Arc arc = graph.arcs[i][j];
					travelTime[i][j] = travelTime(arc.traffic, arc.freeFlowTravelTime, arc.linkCapacity);
				}
			}
		}
		
	}	
		
	private double travelTime(double trafficVolume, double freeFlowTravelTime, double capacity) {
		return freeFlowTravelTime * (1.0 + 0.15 * Math.pow(trafficVolume / capacity, 4.0));
	}	
}
