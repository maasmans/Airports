package laszlo.airports;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Simple class for handling a hashmap created for the Airports program
 * 
 * @author laszlo
 *
 */
public class HashMapping {

	/**
	 * This returns a sorted list of entries given a hashmap containing string as key
	 * and integer as value.
	 * 
	 * @param map - the information that you want to convert to a sorted list of
	 *            entries.
	 * @return A sorted list of entries which is sorted based on the values of the
	 */
	public static List<Entry<String, Integer>> sortHashMap(HashMap<String, Integer> map) {
		Set<Entry<String, Integer>> entrySet = map.entrySet();

		List<Entry<String, Integer>> list = new ArrayList<>(entrySet);

		Collections.sort(list, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));
		return list;
	}
	
	/**
	 * Returns keys of a hashmap as a string array counting from the last value going
	 * for a specified amount these are added to a string separated with a comma.
	 * 
	 * @param The list from where the values will be taken.
	 * @param The to be specified amount of values you want.
	 * @return String of values separated by a comma
	 */
	public static String[] lastKeys(List<Entry<String, Integer>> sortedList, int amountValues) {
		String[] keys = new String[amountValues];
		int sizeList = sortedList.size();

		for (int i = 0; i < amountValues; i++) {
			keys[i] = sortedList.get(sizeList - i - 1).getKey();
		}
		return keys;
	}

	/**
	 * Formats the hashmap with information about airports and it's runway's to a
	 * easily readable string.
	 * 
	 * @param airportsRunways the hashmap.
	 * @return String of information about the airports and it's runway's.
	 */
	public static String formatHashMap(HashMap<String, String> airportsRunways) {
		String text = "";
		for (HashMap.Entry<String, String> entry : airportsRunways.entrySet()) {
			if (entry.getValue().equals("")) {
				text = text.concat(entry.getKey() + " has no runway's");
			} else {
				String[] runwayIds = entry.getValue().split(":");
				int runwaysAmount = runwayIds.length;
				text = text.concat(entry.getKey() + " has " + runwaysAmount + " runway's the id's are:");
				for (String runwayId : runwayIds) {
					text = text + " " + runwayId;
				}
			}
			text = text + System.getProperty("line.separator");
		}

		return text;
	}
}
