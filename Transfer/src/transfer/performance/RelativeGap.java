package transfer.performance;

import java.util.Arrays;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;
import transfer.graph.sp.ShortestPathAlgorithm;
import transfer.tap.base.Demand;

public class RelativeGap implements AbstractPerformance {
	
	Graph graph;
	Demand[] demands;
	ShortestPathAlgorithm shortestPathAlgorithm;
	double[][] travelTime;
	double[] arrayForA;
	double[] arrayForB;
	
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
		
		arrayForA = new double[demands.length];
		arrayForB = new double[graph.arcArray.length];
	}
	
	@Override
	public double calculate() {
		
		shortestPathAlgorithm.prepareForNextIteration();
		
		calculateTravelTimes();
		
		for (int i = 0; i < demands.length; ++i) {
			
			Demand demand = demands[i];
			
			if (demand.volume == 0.0) {
				continue;
			}
			
			Arc[] shortestPath = shortestPathAlgorithm.shortestPath(graph, travelTime, demand.fromId, demand.toId);
			
			if (shortestPath == null) {
				continue;
			}
			
			for (Arc arc : shortestPath) {
				double a = demand.volume * travelTime(arc.traffic, arc.freeFlowTravelTime, arc.linkCapacity);
				arrayForA[i] += a;
			}
		}
				
		for (int i = 0; i < graph.arcArray.length; ++i) {
			Arc arc = graph.arcArray[i];
			double b = arc.traffic * travelTime(arc.traffic, arc.freeFlowTravelTime, arc.linkCapacity); 
			arrayForB[i] = b;
		}
		
		double A = 0.0;
		double B = 0.0;
		
		Arrays.sort(arrayForA);
		Arrays.sort(arrayForB);
		for (double a : arrayForA) {
			A += a;
		}
		for (double b : arrayForB) {
			B += b;
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
