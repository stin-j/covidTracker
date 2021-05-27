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
        Iterator<JSONObject> iterator = data.iterator();
        while(iterator.hasNext()) {
            System.out.println("Cases: " + iterator.next().get("cases"));
        }
    }
}
