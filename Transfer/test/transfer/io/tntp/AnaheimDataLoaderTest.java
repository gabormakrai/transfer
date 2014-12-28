package transfer.io.tntp;

import static org.junit.Assert.*;

import org.junit.Test;

import transfer.graph.base.Graph;
import transfer.tap.base.Demand;

public class AnaheimDataLoaderTest {

	private String getDataDirectory() {
		String dataDirectory = System.getProperty("DataDirectory");
		assertTrue(dataDirectory != null);
		assertFalse(dataDirectory.equals(""));
		return dataDirectory;
	}	
	
	@Test
	public void testRoadNetworkLoader() {
		TNTPGzRoadNetworkLoader roadNetworkLoader = new TNTPGzRoadNetworkLoader();
		Graph graph = roadNetworkLoader.loadFromFile(getDataDirectory() + "anaheim_net.txt.gz");
		assertTrue(graph != null);
		assertEquals(914, graph.arcArray.length);
		assertEquals(417, graph.arcs.length);
		assertEquals(1, graph.arcs[1].length);
		assertEquals(117, graph.arcs[1][0].to);
		assertEquals(2, graph.arcs[416].length);
		assertEquals(23, graph.arcs[416][0].to);
		assertEquals(407, graph.arcs[416][1].to);
	}
	
	@Test
	public void testDemandLoader() {
		TNTPGzDemandLoader demandLoader = new TNTPGzDemandLoader();
		Demand[] demands = demandLoader.loadFromFile(getDataDirectory() + "anaheim_trips.txt.gz");
		assertTrue(demands != null);
		assertEquals(1406, demands.length);
		assertEquals(1, demands[0].fromId);
		assertEquals(2, demands[0].toId);
		assertEquals(1365.9, demands[0].volume, 0.0000001);
		assertEquals(20, demands[703].fromId);
		assertEquals(1, demands[703].toId);
		assertEquals(25.5, demands[703].volume, 0.0000001);
		assertEquals(38, demands[1405].fromId);
		assertEquals(37, demands[1405].toId);
		assertEquals(2.3, demands[1405].volume, 0.0000001);
	}
}
