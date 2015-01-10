package transfer.graph.sp.fhdijkstra;

public class FibonacciHeap {
	
	FibonacciHeapNode min = null;
	int n = 0;
	
	FibonacciHeapNode[] cachedA = null;
	
	public FibonacciHeap() {
	}
	
	public FibonacciHeap(int maxSize) {
		int Dn = (int) Math.floor(Math.log(maxSize) / Math.log(2)) + 2;
		cachedA = new FibonacciHeapNode[Dn + 1];
	}
	
	public void clear() {
		min = null;
		n = 0;
	}
	
	public int size() {
		return n;
	}
	
	public void insert(FibonacciHeapNode node) {
		
		if (min == null) {
			min = node;
		} else {
			
			min.right.left = node;
			node.right = min.right;
			min.right = node;
			node.left = min;			
			
			if (node.key < min.key) {
				min = node;
			}
			
		}
		
		++n;
	}
	
	public FibonacciHeapNode insert(double key, int value) {
		
		FibonacciHeapNode newNode = new FibonacciHeapNode(key, value);
		
		insert(newNode);
		
		return newNode;
	}
		
	public FibonacciHeapNode extractMin() {
		FibonacciHeapNode z = min;
		
		if (z != null) {
			
			FibonacciHeapNode t;
			FibonacciHeapNode w;
			
			if (z.child != null) {
				w = z.child;
				t = w;

				while (t != w) {
					t.parent = null;
					t = t.right;
				}

				min.left.right = w.right;
				w.right.left = min.left;
				min.left = w;
				w.right = min;
			}

			z.left.right = z.right;
			z.right.left = z.left;

			if (z == z.right) {
				min = null;
			} else {
				min = z.right;
				consolidate();
			}
			
			--n;
		}
		
		return z;
	}	
	
	public void decreaseKey(FibonacciHeapNode x, double key) {
		
		x.key = key;
		
		FibonacciHeapNode y = x.parent;

		if (y != null && x.key < y.key) {
			cut(x, y);
			cascadingCut(y);
		}

		if (x.key < min.key) {
			min = x;
		}
	}
	
	private void cut(FibonacciHeapNode x, FibonacciHeapNode y) {
		if (x.right == x) {
			y.child = null;
		} else {
			y.child = x.right;
		}

		x.left.right = x.right;
		x.right.left = x.left;

		--y.degree;

		min.right.left = x;
		x.right = min.right;
		min.right = x;
		x.left = min;
		x.parent = null;

		x.mark = false;
	}
	
	private void cascadingCut(FibonacciHeapNode y) {
		FibonacciHeapNode z = y.parent;

		if (z != null) {
			if (y.mark == false) {
				y.mark = true;
			} else {
				cut(y, z);
				cascadingCut(z);
			}
		}
	}	
	
	private void consolidate() {
		
		int Dn = (int) Math.floor(Math.log(n) / Math.log(2)) + 2;
		
		FibonacciHeapNode[] A;
		
		if (cachedA == null) {
			A = new FibonacciHeapNode[Dn];
		} else {
			A = cachedA;
			for (int i = 0; i < Dn; ++i) {
				A[i] = null;
			}
		}

		FibonacciHeapNode i = min;

		FibonacciHeapNode w = i;

		while (w != i) {
			FibonacciHeapNode x = w;
			int d = x.degree;

			if (A[d] != x) {
				
				while (A[d] != null) {
					FibonacciHeapNode y = A[d];

					if (y.key < x.key) {
						FibonacciHeapNode temp = x;
						x = y;
						y = temp;
					}

					link(y, x);
					i = x;
					w = x;
					A[d] = null;
					d += 1;
				}

				A[d] = x;
			}

			w = w.right;
		}

		min = i;
		w = i;

		do {
			if (w.key < min.key) {
				min = w;
			}
			w = w.right;
		} while (w != i);
		
	}
	
	private void link(FibonacciHeapNode y, FibonacciHeapNode x) {
		y.left.right = y.right;
		y.right.left = y.left;

		if (x.child == null) {
			y.right = y;
			y.left = y;
			x.child = y;
		} else {
			y.right = x.child.right;
			y.left = x.child;
			x.child.right.left = y;
			x.child.right = y;
		}

		y.parent = x;
		++x.degree;
		y.mark = false;
	}	
	
}
