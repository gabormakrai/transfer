package transfer.tap.fw;

import org.junit.Test;

import transfer.graph.base.Graph;
import transfer.graph.sp.DijkstraShortestPathAlgorithm;
import transfer.tap.TapTest;
import transfer.tap.base.Demand;
import transfer.tap.base.TapSolver;
import transfer.tap.criterion.IterationCriterion;

public class FrankWolfeTapTest extends TapTest{

	@Test
	public void test1() {

		Graph graph = generateGraph();
		Demand[] demands = generateDemands();
		DijkstraShortestPathAlgorithm shortestPathAlgorithm = new DijkstraShortestPathAlgorithm();
		shortestPathAlgorithm.init(graph);
		
		FrankWolfeTap algorithm = new FrankWolfeTap();
		TapSolver solver = new TapSolver(graph, demands, algorithm, shortestPathAlgorithm);
		solver.solve(new IterationCriterion(100, true));
		
		checkTapResult(graph);
						
	}	
}
