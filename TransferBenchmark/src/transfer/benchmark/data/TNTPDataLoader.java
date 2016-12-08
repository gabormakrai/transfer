package transfer.benchmark.data;

import transfer.graph.base.Graph;
import transfer.io.tntp.TNTPGzDemandLoader;
import transfer.io.tntp.TNTPGzRoadNetworkLoader;
import transfer.io.tntp.TNTPTxtDemandLoader;
import transfer.io.tntp.TNTPTxtRoadNetworkLoader;
import transfer.tap.base.Demand;

public class TNTPDataLoader {
	
	String dataDirectory;
	
	public TNTPDataLoader(String dataDirectory) {
		this.dataDirectory = dataDirectory;
	}
	
	private Graph loadTxtTNTPRoadnetworkFile(String fileName) {
		TNTPTxtRoadNetworkLoader loader = new TNTPTxtRoadNetworkLoader();
		return loader.loadFromFile(fileName);
	}
	
	private Graph loadGzTNTPRoadnetworkFile(String fileName) {
		TNTPGzRoadNetworkLoader loader = new TNTPGzRoadNetworkLoader();
		return loader.loadFromFile(fileName);
	}
	
	public Graph loadAnaheimRoadNetwork() {
		return loadGzTNTPRoadnetworkFile(dataDirectory + "anaheim_net.txt.gz");
	}
	public Graph loadBraessRoadNetwork() {
		return loadTxtTNTPRoadnetworkFile(dataDirectory + "Braess_net.txt");
	}
	public Graph loadChicagoSketchRoadNetwork() {
		return loadGzTNTPRoadnetworkFile(dataDirectory + "chicagosketch_net.txt.gz"); 
	}
	public Graph loadPhiladelphiaRoadNetwork() {
		return loadGzTNTPRoadnetworkFile(dataDirectory + "Philadelphia_network.txt.gz");
	}
	public Graph loadSiouxFallsRoadNetwork() {
		return loadGzTNTPRoadnetworkFile(dataDirectory + "siouxfalls_net.txt.gz");
	}
	
	private Demand[] loadTxtTNTPDemandFile(String fileName) {
		TNTPTxtDemandLoader loader = new TNTPTxtDemandLoader();
		return loader.loadFromFile(fileName);
	}
	
	private Demand[] loadGzTNTPDemandFile(String fileName) {
		TNTPGzDemandLoader loader = new TNTPGzDemandLoader();
		return loader.loadFromFile(fileName);
	}
	
	public Demand[] loadAnaheimDemand() {
		return loadGzTNTPDemandFile(dataDirectory + "anaheim_trips.txt.gz");
	}
	public Demand[] loadBraessDemand() {
		return loadTxtTNTPDemandFile(dataDirectory + "Braess_trips.txt");
	}
	public Demand[] loadChicageSketchDemand() {
		return loadGzTNTPDemandFile(dataDirectory + "chicagosketch_trips.txt.gz");
	}
	public Demand[] loadPhiladelhiaDemand() {
		return loadGzTNTPDemandFile(dataDirectory + "Philadelphia_trips.txt.gz");
	}
	public Demand[] loadSiouxFallsDemand() {
		return loadGzTNTPDemandFile(dataDirectory + "siouxfalls_trips.txt.gz");
	}
}
