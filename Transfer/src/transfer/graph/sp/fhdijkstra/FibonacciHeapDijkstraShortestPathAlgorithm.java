package transfer.graph.sp.fhdijkstra;

import java.util.LinkedList;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;
import transfer.graph.sp.ShortestPathAlgorithm;

public class FibonacciHeapDijkstraShortestPathAlgorithm implements ShortestPathAlgorithm {
	
	int previousFromId;
	int[] previous;
	FibonacciHeap heap;
	FibonacciHeapNode[] nodes;
		
	@Override
	public void init(Graph graph) {
		previousFromId = -1;
		
		int largestArcId = graph.getLargestArcId();
		previous = new int[largestArcId + 1];
		
		int largestNodeId = graph.getLargestNodeId();
		heap = new FibonacciHeap(largestNodeId + 1);
		nodes = new FibonacciHeapNode[largestNodeId + 1];
		for (int i = 0; i < nodes.length; ++i) {
			nodes[i] = new FibonacciHeapNode(Double.MAX_VALUE, i);
		}
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
		
		for (int i = 0; i < previous.length; ++i) {
			previous[i] = -1;
		}
		
		heap.clear();
		for (int i = 0; i < nodes.length; ++i) {
			nodes[i].key = Double.MAX_VALUE;
			heap.insert(nodes[i]);
		}
		
		heap.decreaseKey(nodes[sourceId], 0.0);
		
		while (heap.size() != 0) {
			
			// extract min
			FibonacciHeapNode min = heap.extractMin();
			int u = min.value;
			
			// find the neighbours
			if (graph.arcs[u] == null) {
				continue;
			}
						
			for (int i = 0; i < graph.arcs[u].length; ++i) {
				double alt = nodes[u].key + travelTime[u][i];
				if (alt < nodes[graph.arcs[u][i].to].key) {
					heap.decreaseKey(nodes[graph.arcs[u][i].to], alt);
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
