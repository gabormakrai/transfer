package transfer.tap.path;

import java.util.LinkedList;

public class ODPaths {
	
	public final int fromID;
	public final int toID;
	public final LinkedList<Path> paths;
	
	public ODPaths(int fromID, int toID) {
		this.fromID = fromID;
		this.toID = toID;
		paths = new LinkedList<>();
	}
	
}
