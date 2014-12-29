package transfer.graph.sp;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;

public interface ShortestPathAlgorithm {
	public void init(Graph graph);
	public Arc[] shortestPath(Graph graph, double[][] travelTime, int from, int to);
}
