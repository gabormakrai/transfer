package transfer.tap.base;

import transfer.graph.base.Graph;

public interface TapAlgorithm {
	
	public void init(Graph graph, Demand[] demands);
	
	public void iteration();
	
}
