package laszlo.airports;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Countries {

	/**
	 * Check if the input can be found in the countries file. It checks if it matches 
	 * a countryCode if so it returns the input. Then it check if it matches the country name
	 * if so it returns the corresponding countryCode.
	 * @param input either a countryName or countryCode
	 * @param pathCountries path to the countries csv file
	 * @return
	 */
	public static String getCountryCode(String input, String pathCountries) {
		String lLine = "";
		String inputQuotes = "\"" + input + "\"";
		try {
			BufferedReader br = new BufferedReader(new FileReader(pathCountries));

			while ((lLine = br.readLine()) != null) {
				String[] values = lLine.split(",");
				if(input.equalsIgnoreCase(values[1]) || input.equalsIgnoreCase(values[2]) ||
				inputQuotes.equalsIgnoreCase(values[1]) || inputQuotes.equalsIgnoreCase(values[2])) {
					return values[1];
				}	
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return "NOT FOUND";
	}

	/**
	 * For a list of countryCodes return a string array of country names. 
	 * The idea is that we only want to go through the countries csv once.
	 * We could easily achieve this be checking all country codes again and again
	 * for a match. Now we rebuild the array when a name has been found.
	 * Problem is then we don't remember the positioning to maintain this
	 * we do a separate check again. This also eliminates problem if country codes
	 * occur more than once in the array.
	 * @param countryCodes a list containing strings of country codes.
	 * @return A string containing country names.
	 */
	public static String[] getCountryNames(String[] countryCodes , String pathCountries) {
		String lLine = "";
		String[] countryNames = new String[countryCodes.length];
		String [] originalCodes = countryCodes;
		int originalLength = originalCodes.length;

		try {
			BufferedReader br = new BufferedReader(new FileReader(pathCountries));

			while ((lLine = br.readLine()) != null) {
				String[] values = lLine.split(",");
				for(int i = 0; i < countryCodes.length; i ++) {
					if(values[1].trim().equals(countryCodes[i])) {
						for(int z = 0; z < originalLength; z++) {
							if(values[1].trim().equals(originalCodes[z])) {
								countryNames[z] = values[2];
							}
						}
						String [] countryCodesCopy = new String[countryCodes.length -1];
						for(int y = 0; y< i; y++) {
							countryCodesCopy[y] = countryCodes[y];
						}
						for(int y = i; y < countryCodes.length -1; y++) {
							countryCodesCopy[y] = countryCodes[y + 1];
						}
						countryCodes = countryCodesCopy;
						break;
					}
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return countryNames;
	}
}
