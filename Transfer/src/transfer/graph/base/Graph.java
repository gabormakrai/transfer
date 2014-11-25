package transfer.graph.base;

public class Graph {
	
	private final int largestArcId;
	
	private final int largestNodeId;
	
	public final Arc[][] arcs;
	
	public Graph(Arc[][] arcs) {
		this.arcs = arcs;
		int largestArcId = Integer.MIN_VALUE;
		int largestNodeId = Integer.MIN_VALUE;
		for (Arc[] a : arcs) {
			for (Arc arc : a) {
				if (arc.id > largestArcId) {
					largestArcId = arc.id;
				}
				if (arc.from > largestNodeId) {
					largestNodeId = arc.from;
				}
				if (arc.to > largestNodeId) {
					largestNodeId = arc.to;
				}
			}
		}
		this.largestArcId = largestArcId;
		this.largestNodeId = largestNodeId;
	}
	
	public void resetTrafficOnArcs() {
		for (int i = 0; i < arcs.length; ++i) {
			if (arcs[i] != null) {
				for (int j = 0; j < arcs[i].length; ++j) {
					arcs[i][j].traffic = 0.0;
				}
			}
		}
	}
	
	public int getLargestArcId() {
		return largestArcId;
	}
	
	public int getLargestNodeId() {
		return largestNodeId;
	}
}
