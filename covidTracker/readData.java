package covidTracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class readData {

	public static void main(String[] args) throws IOException {
		JSONParser jsonParser = new JSONParser();
		URL url = new URL("https://api.covidactnow.org/v2/states.json?apiKey=4b015525bf704af98038f30aab1088f6");
		URLConnection connection = url.openConnection();
		InputStream input = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		String line = null;
		while ((line = br.readLine()) != null) {
			
			System.out.println(line);
		}
	}
}
//4b015525bf704af98038f30aab1088f6
