package transfer.heuristic;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.GZIPInputStream;

import transfer.graph.base.Graph;
import transfer.graph.sp.ShortestPathAlgorithm;
import transfer.graph.sp.pqdijkstra.PriorityQueueDijkstraShortestPathAlgorithm;
import transfer.performance.RelativeGap;
import transfer.tap.base.Demand;
import transfer.tap.base.TapSolver;
import transfer.tap.criterion.IterationCriterion;
import transfer.tap.path.ODPaths;
import transfer.tap.path.OpenPathEquilibriumTap;

public class PathAnalysisMain {
	
	public static final String yorkGraphFile = "d:\\temp\\yorkgraph.object";
	public static final String yorkDemandFile = "d:\\temp\\yorkdemand.object";
	
	Graph graph;
	Demand[] demand;
	
	public static void main(String[] args) {
		new PathAnalysisMain().run();
	}
	
	private int numberOfPaths(ODPaths[] odPaths) {
		int numberOfPaths = 0;
		for (ODPaths odPath : odPaths) {
			numberOfPaths += odPath.paths.size();
		}
		return numberOfPaths;
	}
	
	private void run() {
		loadGraphAndDemand(yorkGraphFile, yorkDemandFile);
		
		
		
		ShortestPathAlgorithm spAlgorithm = new PriorityQueueDijkstraShortestPathAlgorithm();
		spAlgorithm.init(graph);
		OpenPathEquilibriumTap tapAlgorithm = new OpenPathEquilibriumTap(0.005);
		
		for (int i = 1; i < 20; ++i) {
			System.out.println("Iteration: " + i);
			TapSolver solver = new TapSolver(graph, demand, tapAlgorithm, spAlgorithm);
			solver.solve(new IterationCriterion(i, false));
			
			RelativeGap rgap = new RelativeGap(graph, demand, spAlgorithm);
			System.out.println("RGap: " + rgap.calculate());
			
			System.out.println("#paths: " + numberOfPaths(tapAlgorithm.getODPaths()));
		}
		
		System.out.println("asd");
	}
	
	private void loadGraphAndDemand(String graphFile, String demandFile) {
		try {
			ObjectInputStream ois = new ObjectInputStream(new GZIPInputStream(new FileInputStream(graphFile)));
			graph = (Graph)ois.readObject();
			ois.close();
			
			ois = new ObjectInputStream(new GZIPInputStream(new FileInputStream(demandFile)));
			demand = (Demand[])ois.readObject();
			ois.close();
		} catch (IOException e) {
			throw new RuntimeException("Problem: ", e);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("Problem: ", e);
		}
	}
}
