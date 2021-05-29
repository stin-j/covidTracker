package covidTracker;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class dataContinents {
    private JSONArray data;

    public dataContinents(JSONArray data_states) {
        data = data_states;
    }

    public void printTotals() {
        System.out.println("Data for continents:");
        Iterator<JSONObject> iterator = data.iterator();
        while(iterator.hasNext()) {
            JSONObject continent = iterator.next();
            System.out.println("Cases for " + continent.get("continent") + ": " + continent.get("cases"));
        }
        System.out.println();
    }
    public String printContinent(String continentname) {
		Iterator<JSONObject> iterator = data.iterator();
		while (iterator.hasNext()) {
			JSONObject continent = iterator.next();
			if(continent.get("continent").equals(continentname)) {
				return("Cases for " + continent.get("continent") + ": " + continent.get("cases"));
			}
		}
		return null;
	}
}
