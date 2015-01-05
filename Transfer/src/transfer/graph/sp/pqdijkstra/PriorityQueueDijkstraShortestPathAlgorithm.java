package transfer.graph.sp.pqdijkstra;

import java.util.LinkedList;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;
import transfer.graph.sp.ShortestPathAlgorithm;

public class PriorityQueueDijkstraShortestPathAlgorithm implements ShortestPathAlgorithm {
	
	int previousFromId;
	int[] previous;
	PriorityQueueObject[] priorityQueueObjectArray;
	TreeSetPriorityQueue priorityQueue;
		
	@Override
	public void init(Graph graph) {
		previousFromId = -1;
		
		int largestArcId = graph.getLargestArcId();
		previous = new int[largestArcId + 1];
		
		int largestNodeId = graph.getLargestNodeId();
		priorityQueueObjectArray = new PriorityQueueObject[largestNodeId + 1];
		for (int i = 0; i < priorityQueueObjectArray.length; ++i) {
			priorityQueueObjectArray[i] = new PriorityQueueObject(i, Double.POSITIVE_INFINITY, 0);
		}
		
		priorityQueue = new TreeSetPriorityQueue();
	}

	@Override
	public Arc[] shortestPath(Graph graph, double[][] travelTime, int from, int to) {
		if (previousFromId != from) {
			createPreviousArray(graph, travelTime, from);
			previousFromId = from;
		}
		
		return findShortestPath(graph, to);
	}
	
	private Arc[] findShortestPath(Graph graph, int target) {
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

	private void createPreviousArray(Graph graph, double[][] travelTime, int sourceId) {
		
		for (int i = 0; i < priorityQueueObjectArray.length; ++i) {
			priorityQueueObjectArray[i].priority = Double.MAX_VALUE;
			previous[i] = -1;
		}
		
		priorityQueueObjectArray[sourceId].priority = 0.0;
		
		priorityQueue.clear();
		for (int i = 0; i < priorityQueueObjectArray.length; ++i) {
			priorityQueue.add(priorityQueueObjectArray[i]);
		}
				
		while (priorityQueue.size() != 0) {
			
			// extract min
			PriorityQueueObject min = priorityQueue.extractMin();
			int u = min.node;
			
			// find the neighbours
			if (graph.arcs[u] == null) {
				continue;
			}
						
			for (int i = 0; i < graph.arcs[u].length; ++i) {
				double alt = priorityQueueObjectArray[u].priority + travelTime[u][i];
				if (alt < priorityQueueObjectArray[graph.arcs[u][i].to].priority) {
					priorityQueue.decreasePriority(priorityQueueObjectArray[graph.arcs[u][i].to], alt);
					previous[graph.arcs[u][i].to] = graph.arcs[u][i].id;
				}
			}
		}
	}
	
	@Override
	public void prepareForNextIteration() {
		this.previousFromId = -1;
	}	
	
}
