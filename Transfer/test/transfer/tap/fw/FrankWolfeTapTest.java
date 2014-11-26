package transfer.tap.fw;

import static org.junit.Assert.*;

import org.junit.Test;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;
import transfer.tap.base.Demand;
import transfer.tap.criterion.IterationCriterion;

public class FrankWolfeTapTest {

	@Test
	public void test1() {
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
		
		Demand[] demands = new Demand[] {
			new Demand(0, 6, 100.0),
			new Demand(0, 7, 100.0)
		};
		
		FrankWolfeTap tapSolver = new FrankWolfeTap(graph, demands);
		tapSolver.solve(new IterationCriterion(100, true));
				
		assertEquals(200, arcArray[0].traffic, 0.000001);
		assertEquals(100, arcArray[1].traffic, 0.000001);
		assertEquals(100, arcArray[2].traffic, 0.000001);
		assertEquals(100, arcArray[3].traffic, 0.000001);
		assertEquals(100, arcArray[4].traffic, 0.000001);
		assertEquals(200, arcArray[5].traffic, 0.000001);
		assertEquals(100, arcArray[6].traffic, 0.000001);
		assertEquals(100, arcArray[7].traffic, 0.000001);
		
	}	
}
