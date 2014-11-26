package transfer.tap.base;

import transfer.graph.base.Graph;
import transfer.tap.criterion.AbstractCriterion;

public abstract class TapSolver {
	protected Graph graph;
	protected Demand[] demands;
	
	protected TapSolver(Graph graph, Demand[] demands) {
		this.graph = graph;
		this.demands = demands;
	}
	
	public void solve(AbstractCriterion criterion) {
		init();
		while (criterion.check()) {
			iteration();
		}
	}
	
	protected abstract void init();
	
	protected abstract void iteration();
}
