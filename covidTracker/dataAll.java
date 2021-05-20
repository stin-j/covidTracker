package covidTracker;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class dataAll {
	private JSONObject data ;
	public dataAll(JSONObject data_obj) {
		data = data_obj;
	}
	public void printTotals() {
		System.out.println("Total Cases: "+data.get("cases"));
        System.out.println("New Cases Today: "+data.get("todayCases"));
        System.out.println("Total Deaths: "+data.get("deaths"));
        System.out.println("Deaths Today: "+data.get("todayDeaths"));
        System.out.println("Total Recovered: "+data.get("cases"));
        System.out.println("New Recoveries Today: "+data.get("todayRecovered"));
        System.out.println("Current Active Cases: "+data.get("active"));
	}
}
