package transfer.io.tntp;

import static org.junit.Assert.*;

import org.junit.Test;

import transfer.graph.base.Graph;
import transfer.tap.base.Demand;

public class ChicagoSketchDataLoaderTest {

	private String getDataDirectory() {
		String dataDirectory = System.getProperty("DataDirectory");
		assertTrue(dataDirectory != null);
		assertFalse(dataDirectory.equals(""));
		return dataDirectory;
	}	
	
	@Test
	public void testRoadNetworkLoader() {
		TNTPGzRoadNetworkLoader roadNetworkLoader = new TNTPGzRoadNetworkLoader();
		Graph graph = roadNetworkLoader.loadFromFile(getDataDirectory() + "chicagosketch_net.txt.gz");
		assertTrue(graph != null);
		assertEquals(2950, graph.arcArray.length);
		assertEquals(934, graph.arcs.length);
		assertEquals(1, graph.arcs[1].length);
		assertEquals(547, graph.arcs[1][0].to);
		assertEquals(2, graph.arcs[933].length);
		assertEquals(387, graph.arcs[933][0].to);
		assertEquals(534, graph.arcs[933][1].to);
	}
	
	@Test
	public void testDemandLoader() {
		TNTPGzDemandLoader demandLoader = new TNTPGzDemandLoader();
		Demand[] demands = demandLoader.loadFromFile(getDataDirectory() + "chicagosketch_trips.txt.gz");
		assertTrue(demands != null);
		assertEquals(142890, demands.length);
		assertEquals(1, demands[0].fromId);
		assertEquals(1, demands[0].toId);
		assertEquals(273.18, demands[0].volume, 0.0000001);
		assertEquals(189, demands[71445].fromId);
		assertEquals(241, demands[71445].toId);
		assertEquals(0.01, demands[71445].volume, 0.0000001);
		assertEquals(387, demands[142879].fromId);
		assertEquals(356, demands[142879].toId);
		assertEquals(8.0, demands[142879].volume, 0.0000001);
	}
}
