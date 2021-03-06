package transfer.tap.path;

import org.junit.Test;

import transfer.graph.base.Graph;
import transfer.graph.sp.dijkstra.DijkstraShortestPathAlgorithm;
import transfer.tap.TapTest;
import transfer.tap.base.Demand;
import transfer.tap.base.TapSolver;
import transfer.tap.criterion.IterationCriterion;

public class PathEquilibriumTapTest extends TapTest{

	@Test
	public void test1() {
		Graph graph = generateGraph();
		Demand[] demands = generateDemands();
		DijkstraShortestPathAlgorithm shortestPathAlgorithm = new DijkstraShortestPathAlgorithm();
		shortestPathAlgorithm.init(graph);
		
		PathEquilibriumTap algorithm = new PathEquilibriumTap(0.1);
		TapSolver solver = new TapSolver(graph, demands, algorithm, shortestPathAlgorithm);
		solver.solve(new IterationCriterion(100, true));
				
		checkTapResult(graph);
	}	
}
