package transfer.graph.sp;

import java.util.HashSet;
import java.util.LinkedList;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;

public class DijkstraShortestPathAlgorithm implements ShortestPathAlgorithm {
	
	int previousFromId;
	int[] previous;
	double[] distance;

	@Override
	public void init(Graph graph) {
		previousFromId = -1;
		
		int largestArcId = graph.getLargestArcId();
		previous = new int[largestArcId + 1];
		distance = new double[largestArcId + 1];

	}

	@Override
	public Arc[] shortestPath(Graph graph, double[][] travelTime, int from, int to) {
		
		if (previousFromId != from) {
			createPreviousArray(graph, travelTime, from, distance, previous);
		}
		
		return findShortestPath(graph, previous, to);
	}
	
	private void createPreviousArray(Graph graph, double[][] weights, int source, double[] distance, int[] previous) {
		
		int largestNodeId = graph.getLargestNodeId();
		
		for (int i = 0; i < largestNodeId + 1; ++i) {
			distance[i] = Double.MAX_VALUE;
			previous[i] = -1;
		}
		
		distance[source] = 0.0;
		
		HashSet<Integer> verticies = new HashSet<Integer>();
		for (int i = 0; i < largestNodeId + 1; ++i) {
			verticies.add(i);
		}
		
		while (verticies.size() != 0) {
			
			int u = -1;
			
			// search the element where the distance is minimum
			for (int v : verticies) {
				if (u == -1) {
					u = v;
				} else {
					if (distance[u] > distance[v]) {
						u = v;
					}
				}
			}
			
			verticies.remove(u);
			// find the neighbours
			
			if (graph.arcs[u] == null) {
				continue;
			}
			
			for (int i = 0; i < graph.arcs[u].length; ++i) {
				double alt = distance[u] + weights[u][i];
				if (alt < distance[graph.arcs[u][i].to]) {
					distance[graph.arcs[u][i].to] = alt;
					previous[graph.arcs[u][i].to] = graph.arcs[u][i].id;
				}
			}
		}
	}
	
	private Arc[] findShortestPath(Graph graph, int[] previous, int target) {
		if (previous[target] == -1) {
			return null;
		}
		
		LinkedList<Arc> reversedRoute = new LinkedList<>();
		int arcId = previous[target];
		
		while (arcId != -1) {
			reversedRoute.add(graph.arcArray[arcId]);
			Arc arc = graph.arcArray[arcId];
			arcId = previous[arc.from];
		}
		
		Arc[] route = new Arc[reversedRoute.size()];
		for (int i = 0; i < route.length; ++i) {
			route[i] = reversedRoute.get(route.length - 1 - i);
		}
		
		return route;
	}
	

}
