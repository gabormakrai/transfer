package transfer.graph.base;

import java.io.Serializable;
import java.util.LinkedList;

public class Graph implements Serializable{
	
	private static final long serialVersionUID = 1L;

	private final int largestArcId;
	
	private final int largestNodeId;
	
	public final Arc[][] arcs;
	
	public final Arc[] arcArray;
		
	public Graph(Arc[] arcArray) {
		this.arcArray = arcArray;
		int largestArcId = Integer.MIN_VALUE;
		int largestNodeId = Integer.MIN_VALUE;
		for (Arc arc : arcArray) {
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
		this.largestArcId = largestArcId;
		this.largestNodeId = largestNodeId;
		
		this.arcs = new Arc[largestNodeId + 1][];
		
		@SuppressWarnings("unchecked")
		LinkedList<Arc>[] arcsList = (LinkedList<Arc>[])new LinkedList[largestNodeId + 1];
		
		for (Arc arc : arcArray) {
			if (arcsList[arc.from] == null) {
				arcsList[arc.from] = new LinkedList<Arc>();
			}
			arcsList[arc.from].add(arc);
		}
		
		for (int i = 0; i < arcsList.length; ++i) {
			if (arcsList[i] == null) {
				this.arcs[i] = null;
			} else {
				this.arcs[i] = new Arc[arcsList[i].size()];
				for (int j = 0; j < this.arcs[i].length; ++j) {
					this.arcs[i][j] = arcsList[i].get(j);
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
	
	public Graph copy() {
		Arc[] newArcArray = new Arc[arcArray.length];
		for (int i = 0; i < newArcArray.length; ++i) {
			newArcArray[i] = new Arc(arcArray[i]);
		}
		return new Graph(newArcArray);
	}

}
