package transfer.graph.sp;

import static org.junit.Assert.*;

import org.junit.Test;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;
import transfer.graph.sp.dijkstra.DijkstraShortestPathAlgorithm;

public class DijkstraShortestPathAlgorithmTest {

	@Test
	public void testDijkstra1() {
		
		Arc[] arcArray = new Arc[] {
			new Arc(0, 0, 1, 0, 0),
			new Arc(1, 1, 2, 0, 0),
			new Arc(2, 1, 3, 0, 0),
			new Arc(3, 2, 4, 0, 0),
			new Arc(4, 3, 4, 0, 0)
		};
		
		Graph graph = new Graph(arcArray);
						
		double[][] travelTime = new double[][] {
			new double[] { 1.0 },
			new double[] { 1.0, 10.0 },
			new double[] { 1.0 },
			new double[] { 1.0 },
			null			
		};
				
		ShortestPathAlgorithm spAlgorithm = new DijkstraShortestPathAlgorithm();
		spAlgorithm.init(graph);
		
		Arc[] shortestPath = spAlgorithm.shortestPath(graph, travelTime, 0, 4);
		
		assertTrue(shortestPath != null);
		assertEquals(3, shortestPath.length);
		assertEquals(0, shortestPath[0].id);
		assertEquals(1, shortestPath[1].id);
		assertEquals(3, shortestPath[2].id);
	}

}