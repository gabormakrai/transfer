package transfer.graph.sp.pqdijkstra;

import java.util.TreeSet;

public class TreeSetPriorityQueue {
	
	TreeSet<PriorityQueueObject> tree = new TreeSet<PriorityQueueObject>();

	public void add(PriorityQueueObject item) {
		tree.add(item);
	}

	public void decreasePriority(PriorityQueueObject item, double priority) {
		tree.remove(item);
		item.priority = priority;
		tree.add(item);
	}

	public PriorityQueueObject extractMin() {
		return tree.pollFirst();
	}

	public void clear() {
		tree.clear();
	}

	public int size() {
		return tree.size();
	}	

}
