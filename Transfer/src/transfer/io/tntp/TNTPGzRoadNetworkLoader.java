package transfer.io.tntp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import transfer.graph.base.Graph;
import transfer.io.AbstractRoadNetworkLoader;

public class TNTPGzRoadNetworkLoader extends TNTPRoadNetworkReader implements AbstractRoadNetworkLoader {

	@Override
	public Graph loadFromFile(String fileName) {
		
		Graph graph = null;
		
		try {
			graph = loadRoadNetworkFromStream(new GZIPInputStream(new FileInputStream(fileName)));
		} catch (IOException e) {
			// problem
		}
		
		return graph;
	}

}
