package laszlo.airports;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Runways {
	
	/**
	 * Find all the runways airports have. Reads the csv file runways line by line and check if it contains the airport identifier 
	 * given as a hashmap argument. If it find one it adds the runway id to a new hash map containing the airport name.
	 * @param airportIdentifiersNames hashmap containing airport identifier and it's name.
	 * @param pathRunways string containing the path to the runways csv file.
	 */
	public static HashMap<String, String> getAirportsRunways(HashMap<String, String> airportIdentifiersNames, String pathRunways) {
		String lLine = "";
		HashMap<String, String> airportsNamesRunways = new HashMap<String, String>();
		for (HashMap.Entry<String, String> entry : airportIdentifiersNames.entrySet()) {
			airportsNamesRunways.put(entry.getValue(), "");
	      }
				
		try {
			BufferedReader br = new BufferedReader(new FileReader(pathRunways));

			while ((lLine = br.readLine()) != null) {
				String[] values = lLine.split(",");

				if (airportIdentifiersNames.containsKey(values[1])) {
					String nameAirport = airportIdentifiersNames.get(values[1]);
					String oldValue = airportsNamesRunways.get(nameAirport);
					String newValue;
					if (oldValue.equals("")) {
						newValue = values[0];
					} else {
						newValue = oldValue + ":" + values[0];
					}
					airportsNamesRunways.put(nameAirport, newValue);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return airportsNamesRunways;
	}

}
