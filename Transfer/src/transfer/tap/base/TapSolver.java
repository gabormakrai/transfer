package transfer.tap.base;

import transfer.graph.base.Graph;
import transfer.graph.sp.ShortestPathAlgorithm;
import transfer.tap.criterion.AbstractCriterion;

public class TapSolver {
	private Graph graph;
	private Demand[] demands;
	private TapAlgorithm tapAlgorithm;
	private ShortestPathAlgorithm shortestPathAlgorithm;
	
	public TapSolver(Graph graph, Demand[] demands, TapAlgorithm tapAlgorithm, ShortestPathAlgorithm shortestPathAlgorithm) {
		this.graph = graph;
		this.demands = demands;
		this.tapAlgorithm = tapAlgorithm;
		this.shortestPathAlgorithm = shortestPathAlgorithm;
	}
	
	public void solve(AbstractCriterion criterion) {
		tapAlgorithm.init(graph, demands, shortestPathAlgorithm);
		while (criterion.check()) {
			tapAlgorithm.iteration();
		}
	}
	
}
