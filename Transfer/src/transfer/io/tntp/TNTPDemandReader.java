package transfer.io.tntp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;

import transfer.tap.base.Demand;

public class TNTPDemandReader {
	
	protected Demand[] loadDemandFromStream(InputStream inputStream) {
		
		LinkedList<Demand> demandList = new LinkedList<>();
		int numberOfZones = -1;
		double totalODFlows = Double.NaN;

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
			String line = "";
			boolean header = true;
			int zone = -1;
						
			while((line = br.readLine()) != null) {
				if (line.length() > 0 && line.charAt(0) == '~') {
					continue;
				}
				if (line.contains("<END OF METADATA>")) {
					header = false;
				} else if (header && line.contains("<NUMBER OF ZONES>")) {
					String[] splittedLine = line.split("\\ ");
					numberOfZones = Integer.parseInt(splittedLine[3].replace("\t", ""));
				} else if (header && line.contains("<TOTAL OD FLOW>")) {
					String[] splittedLine = line.split("\\ ");
					totalODFlows = Double.parseDouble(splittedLine[3].replace("\t", ""));
				} else if (!header && line.length() > 0) {
					if (line.startsWith("Origin")) {
						String[] splittedLine = line.split("\\ ");
						for (int i = 1; i < splittedLine.length; ++i) {
							try {
								zone = Integer.parseInt(splittedLine[i]);
								break;
							} catch (NumberFormatException e) {
								// do nothing: it is expected
							}
						}
						if (zone > numberOfZones) {
							System.out.println("Strange zone id: " + zone + " where numberOfZones: " + numberOfZones);
						}
					} else {
						String[] splittedLine = line.replace(" ", "").split("\\;");
						for (String record : splittedLine) {
							String[] splittedRecord = record.split("\\:");
							int toZone = Integer.parseInt(splittedRecord[0]);
							if (toZone > numberOfZones) {
								System.out.println("Strange zone id: " + zone + " where numberOfZones: " + numberOfZones);
							}
							double flow = Double.parseDouble(splittedRecord[1]);
							demandList.add(new Demand(zone, toZone, flow));
						}
					}
				}
			}
			
			br.close();
			inputStream.close();
			
		} catch (IOException e) {
			return null;
		}
		
		double totalFlow = 0.0;
		for (Demand demand : demandList) {
			totalFlow += demand.volume;
		}
		
		if (Math.abs(totalFlow - totalODFlows) > 0.000001) {
			System.out.println("Problem during checking the flow volumes with the metadata total flow volume...");
			return null;
		}
		
		return demandList.toArray(new Demand[0]);		
	}
}
