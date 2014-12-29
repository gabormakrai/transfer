package transfer.tap.path;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;
import transfer.graph.sp.Dijkstra;
import transfer.tap.base.Demand;
import transfer.tap.base.TapAlgorithm;

public abstract class PathBasedTap implements TapAlgorithm {

	Graph graph;
	ODPaths[] odPaths;
	Demand[] demands;
	
	private double[] distance;
	private int[] previous;
	
	private double[][] travelTime;
	
	@Override
	public void init(Graph graph, Demand[] demands) {
		
		this.graph = graph;
		this.demands = demands;
		
		this.odPaths = new ODPaths[demands.length];
		for (int i = 0; i < odPaths.length; ++i) {
			odPaths[i] = new ODPaths(demands[i].fromId, demands[i].toId);
		}
		
		int largestNodeId = graph.getLargestNodeId();
		previous = new int[largestNodeId + 1];
		distance = new double[largestNodeId + 1];

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
	}	
	
	@Override
	public void iteration() {
//		
//		double pathVolume = 0.0;
//		for (ODPaths paths : odPaths) {
//			for (Path path : paths.paths) {
//				pathVolume += path.volume;
//			}
//		}
//		System.out.println("Before#PathVolume: " + pathVolume);
//		
		int currentSourceId = -1;
		
		// AON traffic assignment
		for (int i = 0; i < demands.length; ++i) {
			
			Demand demand = demands[i];
			
			if (currentSourceId == -1 || currentSourceId != demand.fromId) {
				currentSourceId = demand.fromId;
				Dijkstra.createPreviousArray(graph, travelTime, currentSourceId, distance, previous);
			}
			
			Arc[] shortestPath = Dijkstra.findShortestPath(graph, previous, demand.toId);
			
			if (shortestPath == null) {
				continue;
			}

			shiftFlow(odPaths[i], shortestPath, demand);
		}
		
		// pushing back the flows from paths to the whole graph
		for (Arc arc : graph.arcArray) {
			arc.traffic = 0.0;
		}
		for (ODPaths odPath : odPaths) {
			for (Path path : odPath.paths) {
				for (Arc arc : path.arcs) {
					arc.traffic += path.volume;
				}
			}
		}
		// updating traveltime
		for (int i = 0; i < graph.arcs.length; ++i) {
			if (graph.arcs[i] == null) {
				continue;
			}
			for (int j = 0; j < graph.arcs[i].length; ++j) {
				travelTime[i][j] = travelTime(graph.arcs[i][j].traffic, graph.arcs[i][j].freeFlowTravelTime, graph.arcs[i][j].linkCapacity);
			}
		}

//		int pathSize = 0;
//		pathVolume = 0.0;
//		for (ODPaths paths : odPaths) {
//			pathSize += paths.paths.size();
//			for (Path path : paths.paths) {
//				pathVolume += path.volume;
//			}
//		}
//		System.out.println("#Path: " + pathSize);
//		System.out.println("#PathVolume: " + pathVolume);
	}
	
	protected abstract void shiftFlow(ODPaths odpaths, Arc[] newPath, Demand demand);
	
	private double travelTime(double trafficVolume, double freeFlowTravelTime, double capacity) {
		return freeFlowTravelTime * (1.0 + 0.15 * Math.pow(trafficVolume / capacity, 4.0));
	}
	
}
