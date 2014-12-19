package transfer.tap;

import static org.junit.Assert.assertEquals;
import transfer.graph.base.Arc;
import transfer.graph.base.Graph;
import transfer.tap.base.Demand;

public class TapTest {
	protected Graph generateGraph() {
		Arc[] arcArray = new Arc[] {
			new Arc(0, 0, 1, 20, 100),
			new Arc(1, 1, 2, 20, 100),
			new Arc(2, 1, 3, 20, 100),
			new Arc(3, 2, 4, 20, 100),
			new Arc(4, 3, 4, 20, 100),
			new Arc(5, 4, 5, 20, 100),
			new Arc(6, 5, 6, 20, 100),
			new Arc(7, 5, 7, 20, 100)
		};
		
		Graph graph = new Graph(arcArray);
		
		return graph;
	}
	
	protected Demand[] generateDemands() {
		
		Demand[] demands = new Demand[] {
			new Demand(0, 6, 100.0),
			new Demand(0, 7, 100.0)
		};
		
		return demands;
	}
	
	protected void checkTapResult(Graph graph) {
		assertEquals(200, graph.arcArray[0].traffic, 0.000001);
		assertEquals(100, graph.arcArray[1].traffic, 0.000001);
		assertEquals(100, graph.arcArray[2].traffic, 0.000001);
		assertEquals(100, graph.arcArray[3].traffic, 0.000001);
		assertEquals(100, graph.arcArray[4].traffic, 0.000001);
		assertEquals(200, graph.arcArray[5].traffic, 0.000001);
		assertEquals(100, graph.arcArray[6].traffic, 0.000001);
		assertEquals(100, graph.arcArray[7].traffic, 0.000001);
	}
	
}
