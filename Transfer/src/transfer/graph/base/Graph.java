package transfer.graph.base;

public class Graph {
	
	public final Arc[][] arcs;
	
	public Graph(Arc[][] arcs) {
		this.arcs = arcs;
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
}
