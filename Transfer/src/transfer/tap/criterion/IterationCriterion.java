package transfer.tap.criterion;

import transfer.graph.base.Graph;

public class IterationCriterion implements AbstractCriterion{
	
	private final int iterationLimit;
	private int iteration;
	private final boolean debug;
	
	public IterationCriterion(int iterationLimit, boolean debug) {
		this.iterationLimit = iterationLimit;
		this.iteration = 0;
		this.debug = debug;
	}
	
	@Override
	public boolean check(Graph graph) {
		if (debug) {
			System.out.println("Iteration limit: " + (iteration + 1) + "/" + iterationLimit);
		}
		return (iteration++) < iterationLimit;
	}
	
	
}
