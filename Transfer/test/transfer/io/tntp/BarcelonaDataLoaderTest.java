package transfer.io.tntp;

import static org.junit.Assert.*;

import org.junit.Test;

import transfer.graph.base.Graph;
import transfer.tap.base.Demand;

public class BarcelonaDataLoaderTest {

	private String getDataDirectory() {
		String dataDirectory = System.getProperty("DataDirectory");
		assertTrue(dataDirectory != null);
		assertFalse(dataDirectory.equals(""));
		return dataDirectory;
	}	
	
	@Test
	public void testRoadNetworkLoader() {
		TNTPGzRoadNetworkLoader roadNetworkLoader = new TNTPGzRoadNetworkLoader();
		Graph graph = roadNetworkLoader.loadFromFile(getDataDirectory() + "barcelona_net.txt.gz");
		assertTrue(graph != null);
		assertEquals(2522, graph.arcArray.length);
		assertEquals(1021, graph.arcs.length);
		assertEquals(3, graph.arcs[1].length);
		assertEquals(290, graph.arcs[1][0].to);
		assertEquals(307, graph.arcs[1][1].to);
		assertEquals(316, graph.arcs[1][2].to);
		assertEquals(3, graph.arcs[1020].length);
		assertEquals(74, graph.arcs[1020][0].to);
		assertEquals(304, graph.arcs[1020][1].to);
		assertEquals(306, graph.arcs[1020][2].to);
	}
	
	@Test
	public void testDemandLoader() {
		TNTPGzDemandLoader demandLoader = new TNTPGzDemandLoader();
		Demand[] demands = demandLoader.loadFromFile(getDataDirectory() + "barcelona_trips.txt.gz");
		assertTrue(demands != null);
		assertEquals(7922, demands.length);
		assertEquals(1, demands[0].fromId);
		assertEquals(3, demands[0].toId);
		assertEquals(402.1, demands[0].volume, 0.0000001);
		assertEquals(46, demands[3961].fromId);
		assertEquals(92, demands[3961].toId);
		assertEquals(8.29, demands[3961].volume, 0.0000001);
		assertEquals(99, demands[7921].fromId);
		assertEquals(109, demands[7921].toId);
		assertEquals(2.481, demands[7921].volume, 0.0000001);
	}
}
