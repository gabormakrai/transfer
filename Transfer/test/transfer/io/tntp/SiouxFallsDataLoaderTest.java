package transfer.io.tntp;

import static org.junit.Assert.*;

import org.junit.Test;

import transfer.graph.base.Graph;
import transfer.tap.base.Demand;

public class SiouxFallsDataLoaderTest {

	private String getDataDirectory() {
		String dataDirectory = System.getProperty("DataDirectory");
		assertTrue(dataDirectory != null);
		assertFalse(dataDirectory.equals(""));
		return dataDirectory;
	}	
	
	@Test
	public void testRoadNetworkLoader() {
		TNTPGzRoadNetworkLoader roadNetworkLoader = new TNTPGzRoadNetworkLoader();
		Graph graph = roadNetworkLoader.loadFromFile(getDataDirectory() + "siouxfalls_net.txt.gz");
		assertTrue(graph != null);
		assertEquals(76, graph.arcArray.length);
		assertEquals(25, graph.arcs.length);
		assertEquals(2, graph.arcs[1].length);
		assertEquals(2, graph.arcs[1][0].to);
		assertEquals(3, graph.arcs[24].length);
		assertEquals(13, graph.arcs[24][0].to);
		assertEquals(21, graph.arcs[24][1].to);
		assertEquals(23, graph.arcs[24][2].to);
	}
	
	@Test
	public void testDemandLoader() {
		TNTPGzDemandLoader demandLoader = new TNTPGzDemandLoader();
		Demand[] demands = demandLoader.loadFromFile(getDataDirectory() + "siouxfalls_trips.txt.gz");
		assertTrue(demands != null);
		assertEquals(576, demands.length);
		assertEquals(1, demands[0].fromId);
		assertEquals(1, demands[0].toId);
		assertEquals(0.0, demands[0].volume, 0.0000001);
		assertEquals(13, demands[288].fromId);
		assertEquals(1, demands[288].toId);
		assertEquals(500.0, demands[288].volume, 0.0000001);
		assertEquals(24, demands[575].fromId);
		assertEquals(24, demands[575].toId);
		assertEquals(0.0, demands[575].volume, 0.0000001);
	}
}
