package transfer.graph.base;

import static org.junit.Assert.*;

import org.junit.Test;

public class GraphTest {
	@Test
	public void test1() {
		Arc[] arcArray = new Arc[] {
			new Arc(0, 0, 1, 0, 0),	
			new Arc(1, 0, 2, 0, 0),	
			new Arc(2, 0, 3, 0, 0),	
			new Arc(3, 0, 4, 0, 0),	
			new Arc(4, 0, 5, 0, 0),	
		};
		
		Graph graph = new Graph(arcArray);
		
		Arc[][] arcs = graph.arcs;
		
		assertTrue(arcs != null);
		assertEquals(6, arcs.length);
		assertEquals(5, arcs[0].length);
		assertEquals(1, arcs[0][0].to);
		assertEquals(2, arcs[0][1].to);
		assertEquals(3, arcs[0][2].to);
		assertEquals(4, arcs[0][3].to);
		assertEquals(5, arcs[0][4].to);
		assertTrue(arcs[1] == null);
		assertTrue(arcs[2] == null);
		assertTrue(arcs[3] == null);
		assertTrue(arcs[4] == null);
		assertTrue(arcs[5] == null);
	}
}
