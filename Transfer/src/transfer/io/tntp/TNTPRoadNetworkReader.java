package transfer.io.tntp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import transfer.graph.base.Arc;
import transfer.graph.base.Graph;

public class TNTPRoadNetworkReader {
	
	protected Graph loadRoadNetworkFromStream(InputStream inputStream) {
		
		LinkedList<Arc> arcsList = new LinkedList<>();
		
		@SuppressWarnings("unused")
		int numberOfZones = 0;
		@SuppressWarnings("unused")
		int numberOfNodes = 0;
		int numberOfLinks = 0;
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String line = "";
			boolean header = true;
			
			while((line = br.readLine()) != null) {
				if (line.length() > 0 && line.charAt(0) == '~' || line.trim().length() == 0) {
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
						int from = Integer.parseInt(splittedLine[1]);
						int to = Integer.parseInt(splittedLine[2]);
						double linkCapacity = Double.parseDouble(splittedLine[3]);
						double freeFlowTravelTime = Double.parseDouble(splittedLine[5]);
						Arc arc = new Arc(arcsList.size(), from, to, freeFlowTravelTime, linkCapacity);
						arcsList.add(arc);
					} catch (NumberFormatException e) {
						System.out.println("Uncompatible line: '" + line + "'");
					} catch (ArrayIndexOutOfBoundsException e) {
						System.out.println("Uncompatible line: '" + line + "'");
					}
				}
			}
			
			br.close();
			inputStream.close();
			
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
