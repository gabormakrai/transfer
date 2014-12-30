package transfer.io.tntp;

import static org.junit.Assert.*;

import org.junit.Test;

import transfer.graph.base.Graph;
import transfer.tap.base.Demand;

public class BraessDataLoaderTest {

	private String getDataDirectory() {
		String dataDirectory = System.getProperty("DataDirectory");
		assertTrue(dataDirectory != null);
		assertFalse(dataDirectory.equals(""));
		return dataDirectory;
	}	
	
	@Test
	public void testRoadNetworkLoader() {
		TNTPTxtRoadNetworkLoader roadNetworkLoader = new TNTPTxtRoadNetworkLoader();
		Graph graph = roadNetworkLoader.loadFromFile(getDataDirectory() + "Braess_net.txt");
		assertTrue(graph != null);
		assertEquals(5, graph.arcArray.length);
		assertEquals(5, graph.arcs.length);
		assertEquals(2, graph.arcs[1].length);
		assertEquals(3, graph.arcs[1][0].to);
		assertEquals(1, graph.arcs[4].length);
		assertEquals(2, graph.arcs[4][0].to);
	}
	
	@Test
	public void testDemandLoader() {
		TNTPTxtDemandLoader demandLoader = new TNTPTxtDemandLoader();
		Demand[] demands = demandLoader.loadFromFile(getDataDirectory() + "Braess_trips.txt");
		assertTrue(demands != null);
		assertEquals(2, demands.length);
		assertEquals(1, demands[0].fromId);
		assertEquals(1, demands[0].toId);
		assertEquals(0.0, demands[0].volume, 0.0000001);
		assertEquals(1, demands[1].fromId);
		assertEquals(2, demands[1].toId);
		assertEquals(6.0, demands[1].volume, 0.0000001);
	}
}
