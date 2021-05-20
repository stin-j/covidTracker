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

public class readData {

	public static void main(String[] args) throws IOException {
		try {
            URL all = new URL("https://disease.sh/v3/covid-19/all");
            URL states = new URL("https://disease.sh/v3/covid-19/states");
            URL continent = new URL("https://disease.sh/v3/covid-19/continents");
            URL country = new URL ("https://disease.sh/v3/covid-19/countries");
            
            HttpURLConnection con = (HttpURLConnection) all.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responsecode = con.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } //I'm only using one site's API so the response code should be the same for all links
            
            else {
                JSONObject data_all = dataInputObject(all);
                dataAll objAll = new dataAll(data_all);
                objAll.printTotals(); 
                JSONArray data_states = dataInputArray(states);
                dataStates objStates = new dataStates(data_states);
                objStates.printTotals();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static JSONObject dataInputObject(URL url) throws IOException, ParseException { //everything inside the link to a JSON Object yay
		JSONParser parse = new JSONParser();
		String file = "";
		Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            file += scanner.next();
        }
        scanner.close();
        JSONObject data = (JSONObject) parse.parse(file);
        return data;
	}
	public static JSONArray dataInputArray(URL url) throws IOException, ParseException {
		JSONParser parser = new JSONParser();
		String file = "";
		Scanner scanner = new Scanner(url.openStream());
        while (scanner.hasNext()) {
            file += scanner.next();
        }
        scanner.close();
		JSONObject obj  =  (JSONObject) parser.parse(file);
		JSONArray array = new JSONArray();
		array.add(obj);
		return (JSONArray)array;
	}
}
