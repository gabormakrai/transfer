package transfer.graph.sp.fhdijkstra;

public class FibonacciHeapNode {
	
	public double key;
	public int value;
	
	FibonacciHeapNode child;
	FibonacciHeapNode parent;
	FibonacciHeapNode left;
	FibonacciHeapNode right;
	boolean mark;
	int degree;
	
	public FibonacciHeapNode(double key, int value) {
		child = null;
		parent = null;
		left = this;
		right = this;
		mark = false;
		degree = 0;
		
		this.key = key;
		this.value = value;
	}
}
