package transfer.io.tntp;

import static org.junit.Assert.*;

import org.junit.Test;

import transfer.graph.base.Graph;

public class ChichagoSketchDataLoaderTest {

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
		demandLoader.loadFromFile(getDataDirectory() + "chicagosketch_trips.txt.gz");
	}
}
