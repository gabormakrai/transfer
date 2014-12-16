package transfer.tap.base;

import transfer.graph.base.Graph;

public interface TapAlgorithm {
	
	public void init(Graph graph, Demand[] demand);
	
	public void iteration();
	
}
