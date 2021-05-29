package covidTracker;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class dataCountries {
	private JSONArray data;

	public dataCountries(JSONArray data_states) {
		data = data_states;
	}

	public void printTotals() {
		System.out.println("Data for countries:");
		Iterator<JSONObject> iterator = data.iterator();
		while (iterator.hasNext()) {
			JSONObject country = iterator.next();
			System.out.println("Cases for " + country.get("country") + ": " + country.get("cases"));
		}
		System.out.println();
	}
    public String printCountry(String countryname) {
		Iterator<JSONObject> iterator = data.iterator();
		while (iterator.hasNext()) {
			JSONObject country = iterator.next();
			if(country.get("country").equals(countryname)) {
				return("Cases for " + country.get("country") + ": " + country.get("cases"));
			}
		}
		return null;
	}
}
