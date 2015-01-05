package transfer.graph.sp.pqdijkstra;

public class PriorityQueueObject implements Comparable<PriorityQueueObject>{
	public double priority;
	public int node;
	
	public PriorityQueueObject(int node, double priority, int arcId) {
		this.node = node;
		this.priority = priority;
	}

	@Override
	public int compareTo(PriorityQueueObject o) {
		if (priority < o.priority) {
			return -1;
		} else if (priority > o.priority) {
			return +1;
		} else if (o != this ) {
			return +1;
		} else {
			return 0;
		}
	}

}
