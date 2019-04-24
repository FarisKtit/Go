package models;

/**
 * Implements leaderboard class.
 * @author Antonius Ricky Sanjaya
 * @version 0.1
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** 
 * Leaderboard class creates leaderboard.
 */
public class Leaderboard {

	/**
	 * Method to get all users and sort them based on win percentage.
	 * @return Sorted map of all users based on win percentage.
	 * @throws Exception
	 */
	public static Map<User, Double> showLeaders() throws Exception {
		ArrayList<User> allUsers = MainStorage.getUserList();
		Map<User, Double> leaderTable = new HashMap<User, Double>();
		for (User user : allUsers) {
			leaderTable.put(user, user.calculateWinPercentage());
		}
		Map<User, Double> sorted = sortHashMapByValues(leaderTable);
        return sorted;
	}

	/**
	 * Method to sort user array according to win percentage.
	 * @param leaderTable Map all the user.
	 * @return Map is sorted according to win percentage.
	 */
	public static Map<User, Double> sortHashMapByValues(Map<User, Double> leaderTable) {
		List<User> mapKeys = new ArrayList<>(leaderTable.keySet());
		List<Double> mapValues = new ArrayList<>(leaderTable.values());
		Collections.sort(mapValues);
		// Collections.sort(mapKeys);

		LinkedHashMap <User, Double> sortedMap = new LinkedHashMap<User, Double>();

		Iterator<Double> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			boolean exit = false;
			Double val = valueIt.next();
			Iterator<User> keyIt = mapKeys.iterator();

			while (keyIt.hasNext() && (!exit)) {
				User key = keyIt.next();
				Double comp1 = leaderTable.get(key);
				Double comp2 = val;

				if (comp1 <= comp2  && sortedMap.get(key)==null) {
					keyIt.remove();
					sortedMap.put(key, val);
					exit = true;
				}
			}
		}
		return sortedMap;
	}
}
