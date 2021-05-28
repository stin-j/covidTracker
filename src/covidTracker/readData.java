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
import java.awt.*;
import javax.swing.*;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class readData extends JFrame {
	private JLabel status;

//	public readData() {
//		status = new JLabel("");
//		add(status, BorderLayout.SOUTH);
//		add(new dataDisplay(status));
//		setResizable(false);
//		pack();
//		setTitle("COVID-19 Tracker");
//		setLocationRelativeTo(null);
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//	}

	public static void main(String[] args) throws IOException {
		try {
			URL all = new URL("https://disease.sh/v3/covid-19/all");
			URL states = new URL("https://disease.sh/v3/covid-19/states");
			URL continent = new URL("https://disease.sh/v3/covid-19/continents");
			URL country = new URL("https://disease.sh/v3/covid-19/countries");

			HttpURLConnection con = (HttpURLConnection) all.openConnection();
			con.setRequestMethod("GET");
			con.connect();
			int responsecode = con.getResponseCode();
			if (responsecode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responsecode);
			}
			// I'm only using one site's API so the response code should be the same for all
			// links
			else {
				// load in all data
				JSONObject data_all = dataInputObject(all);
				dataAll objAll = new dataAll(data_all);
				JSONArray data_states = dataInputArray(states);
				dataStates objStates = new dataStates(data_states);
				JSONArray data_continents = dataInputArray(continent);
				dataContinents objContinent = new dataContinents(data_continents);
				JSONArray data_countries = dataInputArray(country);
				dataCountries objCountry = new dataCountries(data_countries);

				System.out.print(
						"(all) global summary\n(states) cases per state\n(continent) cases per continent\n(country) cases per country\nEnter type of data: ");
				Scanner sc = new Scanner(System.in);
				boolean valid = false;
				String str = sc.next();
				while(!valid) {
					if((str.equals("all")||(str.equals("states"))||(str.equals("continent"))||(str.equals("country")))){
						valid = true;
						break;
					}
					else {
						System.out.print("Invalid input\nEnter type of data: ");
						str = sc.next();
					}
				}
				//global summary
				if (str.equals("all")) {
					objAll.printTotals();
				}
				//state data
				else if (str.equals("states")) {
					System.out.print("Enter requested state formatted with a capitalized first letter and no spaces (or \"all\" for all state data): ");
					String state = sc.next();
					if(state.equals("all"))
						objStates.printTotals();
					else {
						while(objStates.printState(state)==null) {
							System.out.print("Requested state does not exist.\nFormatting example: \"north dakota\" ---> \"NorthDakota\"\nEnter proper state name: ");
							state = sc.next();
						}
						System.out.println(objStates.printState(state));
					}
				}
				//continent data
				else if (str.equals("continent")) {
					System.out.print("Enter continent name (NorthAmerica, Asia, SouthAmerica, Europe, Africa, Australia-Oceania) (or \"all\" for all continent data): ");
					String continentname = sc.next();
					if(continentname.equals("all"))
						objContinent.printTotals();
					else {
						while(objContinent.printContinent(continentname)==null) {
							System.out.print("Requested continent does not exist.\nList: (NorthAmerica, Asia, SouthAmerica, Europe, Africa, Australia-Oceania) (or \"all\" for all continent data)\nEnter proper continent name: ");
							continentname = sc.next();
						}
						System.out.println(objContinent.printContinent(continentname));
					}
				}
				//country data
				else if (str.equals("country")) {
					System.out.print("Enter country name formatted with a capitalized first letter and no spaces (or \"all\" for all country data): ");
					String countryname = sc.next();
					if(countryname.equals("all"))
						objCountry.printTotals();
					else {
						while(objCountry.printCountry(countryname)==null) {
							System.out.print("Requested country does not exist.\nFormatting example: \"sri lanka\" ---> \"SriLanka\" or \"st barth\" ---> \"St.Barth\"\nEnter proper country name: ");
							countryname = sc.next();
						}
						System.out.println(objCountry.printCountry(countryname));
					}
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		readData ct = new readData();
		//ct.setVisible(true);
	}

	public static JSONObject dataInputObject(URL url) throws IOException, ParseException {

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
		JSONArray array = (JSONArray) parser.parse(file);
		return array;
	}
}