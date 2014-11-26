package transfer.graph.sp;

import java.util.HashSet;
import java.util.LinkedList;

import transfer.graph.base.Arc;

public class Dijkstra {
			
	public static void createPreviousArray(int[][] neighbours, int[][] arcs, double[][] weights, int source, double[] distance, int[] previous) {
		
		int largestVertexId = neighbours.length;
		
		for (int i = 0; i < largestVertexId; ++i) {
			distance[i] = Double.MAX_VALUE;
			previous[i] = -1;
		}
		
		distance[source] = 0.0;
		
		HashSet<Integer> verticies = new HashSet<Integer>();
		for (int i = 0; i < largestVertexId; ++i) {
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
			
			if (neighbours[u] == null) {
				continue;
			}
			
			for (int i = 0; i < neighbours[u].length; ++i) {
				double alt = distance[u] + weights[u][i];
				if (alt < distance[neighbours[u][i]]) {
					distance[neighbours[u][i]] = alt;
					previous[neighbours[u][i]] = arcs[u][i];
				}
			}
		}
	}
	
	public static int[] findShortestPath(Arc[] arcArray, int[][] edges, int[] previous, int target) {
		if (previous[target] == -1) {
			return null;
		}
		
		LinkedList<Integer> reversedRoute = new LinkedList<>();
		int arcId = previous[target];
		
		while (arcId != -1) {
			reversedRoute.add(arcId);
			Arc arc = arcArray[arcId];
			arcId = previous[arc.from];
		}
		
		int[] route = new int[reversedRoute.size()];
		for (int i = 0; i < route.length; ++i) {
			route[i] = reversedRoute.get(route.length - 1 - i);
		}
		
		return route;
	}
	
}

