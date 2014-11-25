package transfer.tap.base;

import transfer.graph.base.Graph;
import transfer.tap.criterion.AbstractCriterion;

public abstract class TapSolver {
	protected Graph graph;
	protected Demand[] demands;
	
	public void solve(Graph graph, Demand[] demands, AbstractCriterion criterion) {
		init();
		while (criterion.check()) {
			iteration();
		}
	}
	
	protected abstract void init();
	
	protected abstract void iteration();
}
