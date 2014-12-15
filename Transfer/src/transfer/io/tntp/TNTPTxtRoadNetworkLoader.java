package transfer.io.tntp;

import java.io.FileInputStream;
import java.io.IOException;

import transfer.graph.base.Graph;
import transfer.io.AbstractRoadNetworkLoader;

public class TNTPTxtRoadNetworkLoader extends TNTPRoadNetworkReader implements AbstractRoadNetworkLoader {

	@Override
	public Graph loadFromFile(String fileName) {
		
		Graph graph = null;
		
		try {
			graph = loadRoadNetworkFromStream(new FileInputStream(fileName));
		} catch (IOException e) {
			throw new RuntimeException("Problem during parsing the road network...", e);
		}
		
		return graph;
	}

}
