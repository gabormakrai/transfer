package transfer.graph.sp.dijkstra;

import org.junit.Test;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;
import transfer.graph.sp.ShortestPathAlgorithm;
import transfer.graph.sp.ShortestPathAlgorithmTest;
import transfer.graph.sp.dijkstra.DijkstraShortestPathAlgorithm;

public class DijkstraShortestPathAlgorithmTest extends ShortestPathAlgorithmTest {

	@Test
	public void testDijkstra1() {

		Graph graph = generateGraph();
		double[][] travelTime = generateTravelTime();
		
		ShortestPathAlgorithm spAlgorithm = new DijkstraShortestPathAlgorithm();
		spAlgorithm.init(graph);
		Arc[] shortestPath = spAlgorithm.shortestPath(graph, travelTime, 0, 4);

		assertResult(shortestPath);
	}

}
