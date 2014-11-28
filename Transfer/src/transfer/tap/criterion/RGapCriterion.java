package transfer.tap.criterion;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;
import transfer.graph.sp.Dijkstra;
import transfer.tap.base.Demand;

public class RGapCriterion implements AbstractCriterion{
	
	private final double criterionLimit;
	private final boolean debug;
	private final Graph graph;
	private final Demand[] demands;
	private final double[][] travelTime;
	private final double[] distance;
	private final int[] previous;
	
	public RGapCriterion(Graph graph, Demand[] demands, double criterionLimit, boolean debug) {
		this.criterionLimit = criterionLimit;
		this.debug = debug;
		this.graph = graph;
		this.demands = demands;
		
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
		
		int largestNodeId = graph.getLargestNodeId();
		previous = new int[largestNodeId + 1];
		distance = new double[largestNodeId + 1];
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
	
	@Override
	public boolean check() {
		double rgap = calculateRGap();
		
		if (debug) {
			System.out.println("RGap limit: " + rgap + "/" + criterionLimit + " -> " + (rgap > criterionLimit));
		}
		
		return rgap > criterionLimit;
	}
	
	private double calculateRGap() {
		
		calculateTravelTimes();
		
		double A = 0.0;
		int currentSourceId = -1;
		// AON traffic assignment
		for (Demand demand : demands) {
			
			if (currentSourceId == -1 || currentSourceId != demand.fromId) {
				currentSourceId = demand.fromId;
				Dijkstra.createPreviousArray(graph, travelTime, currentSourceId, distance, previous);
			}
			
			Arc[] shortestPath = Dijkstra.findShortestPath(graph, previous, demand.toId);
			
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
	
	private double travelTime(double trafficVolume, double freeFlowTravelTime, double capacity) {
		return freeFlowTravelTime * (1.0 + 0.15 * Math.pow(trafficVolume / capacity, 4.0));
	}
	
}
