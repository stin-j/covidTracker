package covidTracker;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class readAll {
	private JSONObject data ;
	public readAll(JSONObject data_obj) {
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
