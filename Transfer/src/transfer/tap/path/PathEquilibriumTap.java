package transfer.tap.path;

import java.util.HashSet;

import transfer.graph.base.Arc;
import transfer.tap.base.Demand;

public class PathEquilibriumTap extends PathBasedTap {

	@Override
	protected void shiftFlow(ODPaths odPaths, Arc[] newPath, Demand demand) {
		
		if (odPaths.paths.size() == 0) {
			odPaths.paths.add(new Path(newPath, demand.volume));
			return;
		}
				
		// find the longest path
		Path longestPath = null;
		double longestPathTravelTime = 0.0;
		for (Path path : odPaths.paths) {
			if (longestPath == null) {
				longestPath = path;
				longestPathTravelTime = path.calculateTravelTime();
			} else {
				double pathTravelTime = path.calculateTravelTime();
				if (pathTravelTime > longestPathTravelTime) {
					longestPath = path;
					longestPathTravelTime = pathTravelTime;
				}
			}
		}
				
		if (longestPath.isSameArcs(newPath)) {
			return;
		}
		
		// check: new shortestpath is in the paths set
		Path shortestPath = null; 
		for (Path path : odPaths.paths) {
			if (path.isSameArcs(newPath)) {
				shortestPath = path;
				break;
			}
		}
		
		if (shortestPath == null) {
			shortestPath = new Path(newPath);
			odPaths.paths.add(shortestPath);
		}
		
		double shortestTravelTime = shortestPath.calculateTravelTime();
		
		HashSet<Integer> shortestPathArcs = new HashSet<>();
		for (Arc arc : newPath) {
			shortestPathArcs.add(arc.id);
		}
		
		double A = longestPathTravelTime - shortestTravelTime;
		double B = 0.0;
		for (Arc arc : longestPath.arcs) {
			if (shortestPathArcs.contains(arc.id)) {
				B += travelTimeDerivative(arc.traffic, arc.freeFlowTravelTime, arc.linkCapacity);
			}
		}
		
		double delta = 0.0;
		if (B == 0.0) {
			delta = Double.POSITIVE_INFINITY;
		} else {
			delta = A / B;
		}
		
		if (delta > longestPath.volume) {
			shortestPath.volume = longestPath.volume;
			odPaths.paths.remove(longestPath);
		} else {
			shortestPath.volume += delta;
			longestPath.volume -= delta;
		}
	}

	private double travelTimeDerivative(double trafficVolume, double freeFlowTravelTime, double capacity) {
		return 0.6 * freeFlowTravelTime * Math.pow(1.0 / capacity, 4.0) * Math.pow(trafficVolume, 3.0);
	}
	
}
