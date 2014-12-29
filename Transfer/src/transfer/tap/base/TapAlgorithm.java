package transfer.tap.base;

import transfer.graph.base.Graph;
import transfer.graph.sp.ShortestPathAlgorithm;

public interface TapAlgorithm {
	
	public void init(Graph graph, Demand[] demands, ShortestPathAlgorithm shortestPathAlgorithm);
	
	public void iteration();
	
}
