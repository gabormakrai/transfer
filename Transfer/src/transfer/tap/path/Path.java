package transfer.tap.path;

import java.util.Arrays;

import transfer.graph.base.Arc;

public class Path {
	
	public final Arc[] arcs;
	public double volume;
	
	public Path(Arc[] arcs, double volume) {
		this.arcs = arcs;
		this.volume = volume;
	}
	public Path(Arc[] arcs) {
		this(arcs, 0.0);
	}
	
	@Override
	public String toString() {
		return "Path(volume:" + volume + ",arcs:" + Arrays.toString(arcs) + ")";
	}
}
