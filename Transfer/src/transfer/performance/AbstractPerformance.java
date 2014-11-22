package transfer.performance;

import transfer.graph.base.Graph;
import transfer.tap.base.Demand;

public interface AbstractPerformance {
	public double calculate(Graph graph, Demand[] demands);
}
