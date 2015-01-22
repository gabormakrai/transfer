package transfer.io.tntp;

import static org.junit.Assert.*;

import org.junit.Test;

import transfer.graph.base.Graph;
import transfer.tap.base.Demand;

public class PrismDataLoaderTest {

	private String getDataDirectory() {
		String dataDirectory = System.getProperty("DataDirectory");
		assertTrue(dataDirectory != null);
		assertFalse(dataDirectory.equals(""));
		return dataDirectory;
	}	
	
	@Test
	public void testRoadNetworkLoader() {
		TNTPGzRoadNetworkLoader roadNetworkLoader = new TNTPGzRoadNetworkLoader();
		Graph graph = roadNetworkLoader.loadFromFile(getDataDirectory() + "prism_m_net.txt.gz");
		assertTrue(graph != null);
		assertEquals(33937, graph.arcArray.length);
		assertEquals(14640, graph.arcs.length);
		assertEquals(1, graph.arcs[1].length);
		assertEquals(6595, graph.arcs[1][0].to);
		assertEquals(1, graph.arcs[14639].length);
		assertEquals(14347, graph.arcs[14639][0].to);
	}
	
	@Test
	public void testDemandLoader() {
		TNTPGzDemandLoader demandLoader = new TNTPGzDemandLoader();
		Demand[] demands = demandLoader.loadFromFile(getDataDirectory() + "prism_m_trips.txt.gz");
		assertTrue(demands != null);
		assertEquals(471637, demands.length);
		assertEquals(1, demands[0].fromId);
		assertEquals(20, demands[0].toId);
		assertEquals(15.4242, demands[0].volume, 0.0000001);
		assertEquals(446, demands[235818].fromId);
		assertEquals(556, demands[235818].toId);
		assertEquals(0.83448, demands[235818].volume, 0.0000001);
		assertEquals(898, demands[471636].fromId);
		assertEquals(868, demands[471636].toId);
		assertEquals(0.11628, demands[471636].volume, 0.0000001);
	}
}
