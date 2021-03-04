package laszlo.airports;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Airports {

	/**
	 * Given a path to a csv file containing information of airports in the world
	 * reads the csv file line by line using a bufferedReader to find the countries 
	 * which has the highests amount of airports.
	 * 
	 * @param The location the  csv containing information of airports 
	 * @param The location the  csv containing information of countries 
	 * @return String of countries with the highest amount of airports separated by a comma
	 */
	public static HashMap<String,Integer> getAirportsCountries(String pathAirports, String pathCountries) {
		String lLine = "";

		HashMap<String, Integer> countryIdAirports = new HashMap<String, Integer>();

		try {
			BufferedReader br = new BufferedReader(new FileReader(pathAirports));

			while ((lLine = br.readLine()) != null) {
				String[] values = lLine.split(",");
				String countryId = values[8];
				if (!countryId.isEmpty()) {
					int count = countryIdAirports.getOrDefault(countryId, 0);
					countryIdAirports.put(countryId, count + 1);	
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return countryIdAirports;
	}
		
	
	
	/**
	 * Function that given a country will give all airport of that country and the airport identifier in a hashmap
	 * @param countryCode - string containing the unique countrycode of a country
	 * @param pathAirports - string containing the path to the airports csv file
	 * @return a hashmap containing airport identifiers and names
	 */
	public static HashMap<String, String> getAirportIdentifiersNames(String countryCode, String pathAirports) {
		HashMap<String, String> AirportIdentifiersAndNames = new HashMap<String, String>();
		String line = "";

		try {
			BufferedReader br = new BufferedReader(new FileReader(pathAirports));

			while ((line = br.readLine()) != null) {
				String[] values = line.split(",");
				if (values[8].trim().equals(countryCode)) {
					AirportIdentifiersAndNames.put(values[0], values[3]);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return AirportIdentifiersAndNames;
	}
}

