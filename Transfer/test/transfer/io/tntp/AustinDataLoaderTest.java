package transfer.io.tntp;

import static org.junit.Assert.*;

import org.junit.Test;

import transfer.graph.base.Graph;
import transfer.tap.base.Demand;

public class AustinDataLoaderTest {

	private String getDataDirectory() {
		String dataDirectory = System.getProperty("DataDirectory");
		assertTrue(dataDirectory != null);
		assertFalse(dataDirectory.equals(""));
		return dataDirectory;
	}	
	
	@Test
	public void testRoadNetworkLoader() {
		TNTPGzRoadNetworkLoader roadNetworkLoader = new TNTPGzRoadNetworkLoader();
		Graph graph = roadNetworkLoader.loadFromFile(getDataDirectory() + "austin_net.txt.gz");
		assertTrue(graph != null);
		assertEquals(18961, graph.arcArray.length);
		assertEquals(7389, graph.arcs.length);
		assertEquals(1, graph.arcs[1].length);
		assertEquals(2, graph.arcs[1][0].to);
		assertEquals(4, graph.arcs[3600].length);
		assertEquals(56, graph.arcs[3600][0].to);
		assertEquals(1, graph.arcs[7388].length);
		assertEquals(6288, graph.arcs[7388][0].to);
	}
	
	@Test
	public void testDemandLoader() {
		TNTPGzDemandLoader demandLoader = new TNTPGzDemandLoader();
		Demand[] demands = demandLoader.loadFromFile(getDataDirectory() + "austin_trips_am.txt.gz");
		assertTrue(demands != null);
		assertEquals(1247689, demands.length);
		assertEquals(1, demands[0].fromId);
		assertEquals(1, demands[0].toId);
		assertEquals(1.14, demands[0].volume, 0.0000001);
		assertEquals(3138, demands[623844].fromId);
		assertEquals(3138, demands[623844].toId);
		assertEquals(0.41, demands[623844].volume, 0.0000001);
		assertEquals(7388, demands[1247688].fromId);
		assertEquals(7388, demands[1247688].toId);
		assertEquals(22.49, demands[1247688].volume, 0.0000001);
	}
}
