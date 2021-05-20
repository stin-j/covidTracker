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
            URL url = new URL("https://disease.sh/v3/covid-19/all");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            int responsecode = con.getResponseCode();
            if (responsecode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responsecode);
            } else {
                String file = "";
                Scanner scanner = new Scanner(url.openStream());
                while (scanner.hasNext()) {
                    file += scanner.next();
                }
                scanner.close();
                JSONParser parse = new JSONParser();
                JSONObject data_obj = (JSONObject) parse.parse(file);
                //JSONObject obj =  (JSONObject) data_obj.get("cases");
                System.out.println("Total Cases: "+data_obj.get("cases"));
                System.out.println("New Cases Today: "+data_obj.get("todayCases"));
                System.out.println("Total Deaths: "+data_obj.get("deaths"));
                System.out.println("Deaths Today: "+data_obj.get("todayDeaths"));
                System.out.println("Total Recovered: "+data_obj.get("cases"));
                System.out.println("New Recoveries Today: "+data_obj.get("todayRecovered"));
                System.out.println("Current Active Cases: "+data_obj.get("active"));
                
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
	}
}
//4b015525bf704af98038f30aab1088f6
//current data for all states: https://api.covidactnow.org/v2/states.json?apiKey=4b015525bf704af98038f30aab1088f6
//historic data for all states: https://api.covidactnow.org/v2/states.timeseries.json?apiKey=4b015525bf704af98038f30aab1088f6
//current data for California: https://api.covidactnow.org/v2/state/CA.json?apiKey=4b015525bf704af98038f30aab1088f6
//historic data for California: https://api.covidactnow.org/v2/state/CA.timeseries.json?apiKey=4b015525bf704af98038f30aab1088f6
//data for all counties in California: https://api.covidactnow.org/v2/county/CA.json?apiKey=4b015525bf704af98038f30aab1088f6
//aggregated US data: https://api.covidactnow.org/v2/country/US.json?apiKey=4b015525bf704af98038f30aab1088f6
//global summary: https://disease.sh/v3/covid-19/all
