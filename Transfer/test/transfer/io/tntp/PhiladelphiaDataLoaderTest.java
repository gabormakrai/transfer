package transfer.io.tntp;

import static org.junit.Assert.*;

import org.junit.Test;

import transfer.graph.base.Graph;
import transfer.tap.base.Demand;

public class PhiladelphiaDataLoaderTest {

	private String getDataDirectory() {
		String dataDirectory = System.getProperty("DataDirectory");
		assertTrue(dataDirectory != null);
		assertFalse(dataDirectory.equals(""));
		return dataDirectory;
	}	
	
	@Test
	public void testRoadNetworkLoader() {
		TNTPGzRoadNetworkLoader roadNetworkLoader = new TNTPGzRoadNetworkLoader();
		Graph graph = roadNetworkLoader.loadFromFile(getDataDirectory() + "Philadelphia_network.txt.gz");
		assertTrue(graph != null);
		assertEquals(40003, graph.arcArray.length);
		assertEquals(13390, graph.arcs.length);
		assertEquals(4, graph.arcs[1].length);
		assertEquals(4067, graph.arcs[1][0].to);
		assertEquals(1, graph.arcs[13389].length);
		assertEquals(3834, graph.arcs[13389][0].to);
	}
	
	@Test
	public void testDemandLoader() {
		TNTPGzDemandLoader demandLoader = new TNTPGzDemandLoader();
		Demand[] demands = demandLoader.loadFromFile(getDataDirectory() + "Philadelphia_trips.txt.gz");
		assertTrue(demands != null);
		assertEquals(1151166, demands.length);
		assertEquals(1, demands[0].fromId);
		assertEquals(1, demands[0].toId);
		assertEquals(352.0, demands[0].volume, 0.0000001);
		assertEquals(84, demands[71445].fromId);
		assertEquals(485, demands[71445].toId);
		assertEquals(6.0, demands[71445].volume, 0.0000001);
		assertEquals(1525, demands[1151165].fromId);
		assertEquals(1525, demands[1151165].toId);
		assertEquals(166.0, demands[1151165].volume, 0.0000001);
	}
}
