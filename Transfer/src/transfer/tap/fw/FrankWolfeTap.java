package transfer.tap.fw;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;
import transfer.graph.sp.ShortestPathAlgorithm;
import transfer.tap.base.Demand;
import transfer.tap.base.TapAlgorithm;

public class FrankWolfeTap implements TapAlgorithm {
		
	private Graph graph;
	private Demand[] demands;
	private ShortestPathAlgorithm shortestPathAlgorithm;
	
	private double[] y;
	
	private double[] d;
	
	private double[][] travelTime;
				
	@Override
	public void init(Graph graph, Demand[] demands, ShortestPathAlgorithm shortestPathAlgorithm) {
		
		this.graph = graph;
		this.demands = demands;
		this.shortestPathAlgorithm = shortestPathAlgorithm;
		
		int largestArcId = graph.getLargestArcId();
		
		y = new double[largestArcId + 1];
		
		d = new double[largestArcId + 1];
		
		travelTime = new double[graph.arcs.length][];
		
		for (int i = 0; i < graph.arcs.length; ++i) {
			if (graph.arcs[i] == null) {
				travelTime[i] = null;
			} else {
				travelTime[i] = new double[graph.arcs[i].length];
				for (int j = 0; j < travelTime[i].length; ++j) {
					travelTime[i][j] = graph.arcs[i][j].freeFlowTravelTime;
				}
			}
		}
				
		// create the feasible initial solution
		iteration(true);
	}
	
	@Override
	public void iteration() {
		iteration(false);
	}
	
	private void iteration(boolean initial) {
		
		for (int i = 0; i < y.length; ++i) {
			y[i] = 0.0;
			d[i] = 0.0;
		}

		// AON traffic assignment
		for (Demand demand : demands) {
			
			Arc[] shortestPath = shortestPathAlgorithm.shortestPath(graph, travelTime, demand.fromId, demand.toId);
			
			if (shortestPath == null) {
				continue;
			}
			
			for (Arc arc : shortestPath) {
				y[arc.id] += demand.volume;
			}
		}
		
		if (initial) {
			for (Arc arc : graph.arcArray) {
				arc.traffic = y[arc.id];
			}
			for (int i = 0; i < graph.arcs.length; ++i) {
				if (graph.arcs[i] == null) {
					continue;
				}
				for (int j = 0; j < graph.arcs[i].length; ++j) {
					travelTime[i][j] = travelTime(graph.arcs[i][j].traffic, graph.arcs[i][j].freeFlowTravelTime, graph.arcs[i][j].linkCapacity);
				}
			}	
			return;
		}
				
		// calculating d
		for (int i = 0; i < graph.arcArray.length; ++i) {
			d[i] = y[i] - graph.arcArray[i].traffic;
		}
		
		// calculating alpha
		
		double alpha = 0;
		
		double A = 0.0;
		double B = 0.0;
		
		for (int i = 0; i < graph.arcArray.length; ++i) {
			double trafficVolume = graph.arcArray[i].traffic;
			double freeFlowTravelTime = graph.arcArray[i].freeFlowTravelTime;
			double linkCapacity = graph.arcArray[i].linkCapacity;
			A += travelTime(trafficVolume, freeFlowTravelTime, linkCapacity) * d[i];
			B += travelTimeDerivative(trafficVolume, freeFlowTravelTime, linkCapacity) * Math.pow(d[i], 2.0);
		}
		
		if (Math.abs(B) < 0.0000000001) {
			return;
		}
				
		alpha = -1.0 * A / B;
				
		for (int i = 0; i < graph.arcs.length; ++i) {
			if (graph.arcs[i] == null) {
				continue;
			}
			for (int j = 0; j < graph.arcs[i].length; ++j) {
				Arc arc = graph.arcs[i][j];
				arc.traffic += alpha * d[arc.id];
				travelTime[i][j] = travelTime(arc.traffic, arc.freeFlowTravelTime, arc.linkCapacity);
			}
		}	
		
	}

	private double travelTime(double trafficVolume, double freeFlowTravelTime, double capacity) {
		return freeFlowTravelTime * (1.0 + 0.15 * Math.pow(trafficVolume / capacity, 4.0));
	}
	
	private double travelTimeDerivative(double trafficVolume, double freeFlowTravelTime, double capacity) {
		return 0.6 * freeFlowTravelTime * Math.pow(1.0 / capacity, 4.0) * Math.pow(trafficVolume, 3.0);
	}

}
