package covidTracker;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class readData {

	public static void main(String[] args) throws IOException {
		URL url = new URL("https://corona.lmao.ninja/");
		URLConnection connection = url.openConnection();
		InputStream input = connection.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		String line = null;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
	}
}
