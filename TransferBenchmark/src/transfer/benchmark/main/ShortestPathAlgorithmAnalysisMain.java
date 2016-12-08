package transfer.benchmark.main;

import transfer.benchmark.data.TNTPDataLoader;
import transfer.benchmark.tap.BenchmarkTapSolver;
import transfer.graph.base.Graph;
import transfer.graph.sp.ShortestPathAlgorithm;
import transfer.graph.sp.pqdijkstra.PriorityQueueDijkstraShortestPathAlgorithm;
import transfer.tap.base.Demand;
import transfer.tap.base.TapAlgorithm;
import transfer.tap.criterion.IterationCriterion;
import transfer.tap.fw.FrankWolfeTap;

public class ShortestPathAlgorithmAnalysisMain {
	
	public static void main(String[] args) {
		new ShortestPathAlgorithmAnalysisMain().run();
	}
	
	private void run() {
		TNTPDataLoader loader = new TNTPDataLoader(System.getProperty("DataDirectory"));
		
		Graph graph = loader.loadChicagoSketchRoadNetwork();
		Demand[] demands = loader.loadChicageSketchDemand();
		
		
//		ShortestPathAlgorithm shortestPathAlgorithm = new DijkstraShortestPathAlgorithm();
		ShortestPathAlgorithm shortestPathAlgorithm = new PriorityQueueDijkstraShortestPathAlgorithm();
		
//		TapAlgorithm tapAlgorithm = new PathEquilibriumTap(0.1);
		TapAlgorithm tapAlgorithm = new FrankWolfeTap();
		shortestPathAlgorithm.init(graph);
		
		BenchmarkTapSolver solver = new BenchmarkTapSolver(graph, demands, tapAlgorithm, shortestPathAlgorithm);
		solver.solve(new IterationCriterion(2, true));
		
		solver.printResult();
	}
}
