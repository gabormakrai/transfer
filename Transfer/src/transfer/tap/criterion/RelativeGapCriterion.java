package transfer.tap.criterion;

import transfer.graph.base.Graph;
import transfer.graph.sp.ShortestPathAlgorithm;
import transfer.performance.RelativeGap;
import transfer.tap.base.Demand;

public class RelativeGapCriterion implements AbstractCriterion{
	
	private final double criterionLimit;
	private final boolean debug;
	private RelativeGap relativeGap;
	
	public RelativeGapCriterion(Graph graph, Demand[] demands, ShortestPathAlgorithm shortestPathAlgorithm, double criterionLimit, boolean debug) {
		this.criterionLimit = criterionLimit;
		this.debug = debug;
		this.relativeGap = new RelativeGap(graph, demands, shortestPathAlgorithm);
	}
		
	@Override
	public boolean check() {
		double rgap = relativeGap.calculate();
		
		if (debug) {
			System.out.println("RGap limit: " + rgap + "/" + criterionLimit + " -> " + (rgap > criterionLimit));
		}
		
		return rgap > criterionLimit;
	}
		
}
