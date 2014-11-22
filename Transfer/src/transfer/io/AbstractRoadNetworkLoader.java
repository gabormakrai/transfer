package transfer.io;

import transfer.graph.base.Graph;

public interface AbstractRoadNetworkLoader {
	public Graph loadFromFile(String fileName);
}
