package covidTracker;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class dataStates {
	private JSONArray data;

	public dataStates(JSONArray data_states) {
		data = data_states;
	}

	public void printTotals() {
		JSONArray jsonArray = (JSONArray) data.get(0);
		Iterator<String> iterator = jsonArray.iterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
	}
}
