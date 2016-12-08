package transfer.benchmark.tap;

import java.util.LinkedList;

import transfer.graph.base.Graph;
import transfer.graph.sp.ShortestPathAlgorithm;
import transfer.tap.base.Demand;
import transfer.tap.base.TapAlgorithm;
import transfer.tap.criterion.AbstractCriterion;

public class BenchmarkTapSolver {
	private Graph graph;
	private Demand[] demands;
	private TapAlgorithm tapAlgorithm;
	private ShortestPathAlgorithm shortestPathAlgorithm;
	
	public long initStartTime;
	public long initEndTime;
	
	public LinkedList<Long> iterationStartTime;
	public LinkedList<Long> iterationEndTime;
	
	public BenchmarkTapSolver(Graph graph, Demand[] demands, TapAlgorithm tapAlgorithm, ShortestPathAlgorithm shortestPathAlgorithm) {
		this.graph = graph;
		this.demands = demands;
		this.tapAlgorithm = tapAlgorithm;
		this.shortestPathAlgorithm = shortestPathAlgorithm;
		
		iterationStartTime = new LinkedList<>();
		iterationEndTime = new LinkedList<>();
	}
	
	public void solve(AbstractCriterion criterion) {
		initStartTime = System.nanoTime();
		tapAlgorithm.init(graph, demands, shortestPathAlgorithm);
		initEndTime = System.nanoTime();
		while (criterion.check()) {
			iterationStartTime.add(System.nanoTime());
			tapAlgorithm.iteration();
			iterationEndTime.add(System.nanoTime());
		}
	}
	
	public void printResult() {
		System.out.println("InitTime: " + (initEndTime - initStartTime) / 1000000.0);
		for (int i = 0; i < iterationStartTime.size(); ++i) {
			System.out.println("IterationTime: " + (iterationEndTime.get(i) - iterationStartTime.get(i)) / 1000000.0);
		}		
	}
	
}
