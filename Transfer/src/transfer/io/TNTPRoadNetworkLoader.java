package transfer.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;

public class TNTPRoadNetworkLoader implements AbstractRoadNetworkLoader {
	@Override
	public Graph loadFromFile(String fileName) {
		
		LinkedList<Arc> arcsList = new LinkedList<>();
		
		@SuppressWarnings("unused")
		int numberOfZones = 0;
		@SuppressWarnings("unused")
		int numberOfNodes = 0;
		int numberOfLinks = 0;
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			String line = "";
			boolean header = true;
			
			while((line = br.readLine()) != null) {
				if (line.length() > 0 && line.charAt(0) == '~') {
					continue;
				}
				if (line.contains("<END OF METADATA>")) {
					header = false;
				} else if (header && line.contains("<NUMBER OF ZONES>")) {
					String[] splittedLine = line.split("\\ ");
					try {
						numberOfZones = Integer.parseInt(splittedLine[3].replace("\t", ""));
					} catch (NumberFormatException e) {
						// do nothing
					}
				} else if (header && line.contains("<NUMBER OF NODES>")) {
					String[] splittedLine = line.split("\\ ");
					try {
						numberOfNodes = Integer.parseInt(splittedLine[3].replace("\t", ""));
					} catch (NumberFormatException e) {
						// do nothing
					}
				} else if (header && line.contains("<NUMBER OF LINKS>")) {
					String[] splittedLine = line.split("\\ ");
					try {
						numberOfLinks = Integer.parseInt(splittedLine[3].replace("\t", ""));
					} catch (NumberFormatException e) {
						// do nothing
					}
				} else if (!header && line.length() > 0) {
					String[] splittedLine = line.split("\\\t");
					try {
						int from = Integer.parseInt(splittedLine[0]);
						int to = Integer.parseInt(splittedLine[1]);
						double linkCapacity = Double.parseDouble(splittedLine[2]);
						double freeFlowTravelTime = Double.parseDouble(splittedLine[4]);
						Arc arc = new Arc(arcsList.size(), from, to, freeFlowTravelTime, linkCapacity);
						arcsList.add(arc);
					} catch (NumberFormatException e) {
						System.out.println("Uncompatible line: '" + line + "'");
					}
				}
			}
			
			br.close();
			
		} catch (IOException e) {
			return null;
		}
		
		if (arcsList.size() != numberOfLinks) {
			return null;
		} else {
			return new Graph(arcsList.toArray(new Arc[0]));
		}
	}
}
