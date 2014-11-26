package transfer.graph.sp;

import static org.junit.Assert.*;

import org.junit.Test;

import transfer.graph.base.Arc;
import transfer.tap.criterion.IterationCriterion;

public class DijkstraTest {
//	DirectedEdgeListGraph graph = new DirectedEdgeListGraph();
//	graph.addEdge(e0);
//	graph.addEdge(e1);
//	graph.addEdge(e2);
//	graph.addEdge(e3);
//	graph.addEdge(e4);
//	
//	DirectedWeightedEdge[] edgeArray = Dijkstra.getDirectedEdgeArray(graph);
//	
//	Object[] arrays = Dijkstra.createArrays(edgeArray);
//	
//	int[][] neighbours = (int[][]) arrays[0];
//	int[][] edges = (int[][]) arrays[1];
//	double[][] weights = (double[][]) arrays[2];
//	double[] distance = (double[]) arrays[3];
//	int[] previous = (int[]) arrays[4];
//	
//	Dijkstra.createPreviousArray(neighbours, edges, weights, v0.id, distance, previous);
//	
//	int[] edgeRoute = Dijkstra.findShortestPath(edgeArray, edges, previous, v4.id);
//	
//	assertTrue(edgeRoute != null);
//	assertEquals(3, edgeRoute.length);
//	assertEquals(4, edgeRoute[0]);
//	assertEquals(2, edgeRoute[1]);
//	assertEquals(0, edgeRoute[2]);
//	
//	edgeRoute = Dijkstra.findShortestPath(edgeArray, edges, previous, v3.id);
//	assertTrue(edgeRoute != null);
//	assertEquals(2, edgeRoute.length);
//	assertEquals(4, edgeRoute[0]);
//	assertEquals(3, edgeRoute[1]);
//	
//	edgeRoute = Dijkstra.findShortestPath(edgeArray, edges, previous, v2.id);
//	assertTrue(edgeRoute != null);
//	assertEquals(2, edgeRoute.length);
//	assertEquals(4, edgeRoute[0]);
//	assertEquals(2, edgeRoute[1]);
//	
//	edgeRoute = Dijkstra.findShortestPath(edgeArray, edges, previous, v2.id);
//	assertTrue(edgeRoute != null);
//	assertEquals(2, edgeRoute.length);
//	assertEquals(4, edgeRoute[0]);
//	assertEquals(2, edgeRoute[1]);


	@Test
	public void testDijkstra1() {
		
		Arc[] arcArray = new Arc[] {
			new Arc(0, 0, 1, 0, 0),
			new Arc(1, 1, 2, 0, 0),
			new Arc(2, 1, 3, 0, 0),
			new Arc(3, 2, 4, 0, 0),
			new Arc(4, 3, 4, 0, 0)
		};
		
		Arc[][] arcs = new Arc[][] {
			new Arc[] { arcArray[0] },
			new Arc[] { arcArray[1], arcArray[2] },
			new Arc[] { arcArray[3] },
			new Arc[] { arcArray[4] },
			null
		};
		
		int[][] neighbours = new int[][] {
			new int[] { 1 },
			new int[] { 2, 3 },
			new int[] { 4 },
			new int[] { 4 },
			null
		};
		
		int[][] arcIds = new int[][] {
			new int[] { 0 },
			new int[] { 1, 2 },
			new int[] { 3 },
			new int[] { 4 },
			null			
		};
		
		double[][] travelTime = new double[][] {
			new double[] { 1.0 },
			new double[] { 1.0, 10.0 },
			new double[] { 1.0 },
			new double[] { 1.0 },
			null			
		};
		
		double[] distance = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0 };
		int[] previous = new int[] { 0, 0, 0, 0, 0 };
		
		Dijkstra.createPreviousArray(neighbours, arcIds, travelTime, 0, distance, previous);
		
		int[] shortestPath = Dijkstra.findShortestPath(arcArray, arcIds, previous, 4);
		
		assertTrue(shortestPath != null);
		assertEquals(3, shortestPath.length);
		assertEquals(0, shortestPath[0]);
		assertEquals(1, shortestPath[1]);
		assertEquals(3, shortestPath[2]);
	}

}
