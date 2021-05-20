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
                JSONObject data_all = dataInput(all);
                readAll objAll = new readAll(data_all);
                objAll.printTotals(); 
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public static JSONObject dataInput(URL url) throws IOException, ParseException { //everything inside the link to a JSON Object yay
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
}
//COVIDACTNOW.ORG
//4b015525bf704af98038f30aab1088f6 <--- API KEY
//current data for all states: https://api.covidactnow.org/v2/states.json?apiKey=4b015525bf704af98038f30aab1088f6
//historic data for all states: https://api.covidactnow.org/v2/states.timeseries.json?apiKey=4b015525bf704af98038f30aab1088f6
//current data for California: https://api.covidactnow.org/v2/state/CA.json?apiKey=4b015525bf704af98038f30aab1088f6
//historic data for California: https://api.covidactnow.org/v2/state/CA.timeseries.json?apiKey=4b015525bf704af98038f30aab1088f6
//data for all counties in California: https://api.covidactnow.org/v2/county/CA.json?apiKey=4b015525bf704af98038f30aab1088f6
//aggregated US data: https://api.covidactnow.org/v2/country/US.json?apiKey=4b015525bf704af98038f30aab1088f6

//disease.sh (easier to use rn)
//global summary: https://disease.sh/v3/covid-19/all
//by state: https://disease.sh/v3/covid-19/states
//California data (don't need tbh): https://disease.sh/v3/covid-19/states/California
//by continent: https://disease.sh/v3/covid-19/continents
//by country: https://disease.sh/v3/covid-19/countries
