package transfer.io.tntp;

import static org.junit.Assert.*;

import org.junit.Test;

import transfer.graph.base.Graph;

public class TNTPTxtRoadNetworkLoaderTest {
	
	private String getDataDirectory() {
		String dataDirectory = System.getProperty("DataDirectory");
		assertTrue(dataDirectory != null);
		assertFalse(dataDirectory.equals(""));
		return dataDirectory;
	}
	
	@Test
	public void chichagoTest() {
		Graph graph = new TNTPTxtRoadNetworkLoader().loadFromFile(getDataDirectory() + "chicagoregional_net.txt");
		assertEquals(graph.arcArray.length, 39018);
	}
	
}
