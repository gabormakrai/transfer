package transfer.io.tntp;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;

import transfer.io.AbstractDemandLoader;
import transfer.tap.base.Demand;

public class TNTPGzDemandLoader extends TNTPDemandReader implements AbstractDemandLoader {

	@Override
	public Demand[] loadFromFile(String fileName) {
		Demand[] demands = null;
		
		try {
			demands = loadDemandFromStream(new GZIPInputStream(new FileInputStream(fileName)));
		} catch (IOException e) {
			throw new RuntimeException("Problem during parsing the road network...", e);
		}
	
	return demands;
	}

}
