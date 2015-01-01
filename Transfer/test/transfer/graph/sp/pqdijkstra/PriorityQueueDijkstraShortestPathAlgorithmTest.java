package transfer.graph.sp.pqdijkstra;

import org.junit.Test;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;
import transfer.graph.sp.ShortestPathAlgorithmTest;

public class PriorityQueueDijkstraShortestPathAlgorithmTest extends ShortestPathAlgorithmTest {
	@Test
	public void testDijkstra1() {

		Graph graph = generateGraph();
		double[][] travelTime = generateTravelTime();
		
		PriorityQueueDijkstraShortestPathAlgorithm spAlgorithm = new PriorityQueueDijkstraShortestPathAlgorithm();
		spAlgorithm.init(graph);
		Arc[] shortestPath = spAlgorithm.shortestPath(graph, travelTime, 0, 4);

		assertResult(shortestPath);
	}
}
