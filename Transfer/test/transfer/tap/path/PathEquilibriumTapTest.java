package transfer.tap.path;

import org.junit.Test;

import transfer.graph.base.Graph;
import transfer.tap.TapTest;
import transfer.tap.base.Demand;
import transfer.tap.base.TapSolver;
import transfer.tap.criterion.IterationCriterion;

public class PathEquilibriumTapTest extends TapTest{

	@Test
	public void test1() {
		Graph graph = generateGraph();
		Demand[] demands = generateDemands();
		
		PathEquilibriumTap algorithm = new PathEquilibriumTap();
		TapSolver solver = new TapSolver(graph, demands, algorithm);
		solver.solve(new IterationCriterion(100, true));
				
		checkTapResult(graph);
	}	
}
