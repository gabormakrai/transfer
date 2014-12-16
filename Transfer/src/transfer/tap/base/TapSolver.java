package transfer.tap.base;

import transfer.graph.base.Graph;
import transfer.tap.criterion.AbstractCriterion;

public class TapSolver {
	private Graph graph;
	private Demand[] demands;
	private TapAlgorithm tapAlgorithm;
	
	public TapSolver(Graph graph, Demand[] demands, TapAlgorithm tapAlgorithm) {
		this.graph = graph;
		this.demands = demands;
		this.tapAlgorithm = tapAlgorithm;
	}
	
	public void solve(AbstractCriterion criterion) {
		tapAlgorithm.init(graph, demands);
		while (criterion.check()) {
			tapAlgorithm.iteration();
		}
	}
	
}
