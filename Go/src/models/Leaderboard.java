package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Leaderboard {

	public static Map<User, Double> showLeaders() throws Exception {
		ArrayList<User> allUsers = MainStorage.getUserList();
		Map<User, Double> leaderTable = new HashMap<User, Double>();
		for (User user : allUsers) {
			leaderTable.put(user, user.calculateWinPercentage());
		}
		Map<User, Double> sorted = sortHashMapByValues(leaderTable);
        return sorted;
	}

	public static Map<User, Double> sortHashMapByValues(Map<User, Double> leaderTable) {
		List<User> mapKeys = new ArrayList<>(leaderTable.keySet());
		List<Double> mapValues = new ArrayList<>(leaderTable.values());
		Collections.sort(mapValues);
		// Collections.sort(mapKeys);

		LinkedHashMap<User, Double> sortedMap = new LinkedHashMap<User, Double>();

		Iterator<Double> valueIt = mapValues.iterator();
		while (valueIt.hasNext()) {
			boolean declare = false;
			Double val = valueIt.next();
			Iterator<User> keyIt = mapKeys.iterator();

			while (keyIt.hasNext()) {
				User key = keyIt.next();
				Double comp1 = leaderTable.get(key);
				Double comp2 = val;

				if (comp1 <= comp2  && sortedMap.get(key)==null && declare) {
					keyIt.remove();
					sortedMap.put(key, val);
					declare = true;
				}
			}
		}
		return sortedMap;
	}

}
