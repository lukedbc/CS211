package CP5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class EclipseTest {

    private static final String API = "https://aa.usno.navy.mil/api/eclipses/solar/year?year=";
    private static final Scanner CONSOLE = new Scanner(System.in);

    public static void main(String[] args) throws IOException, ParseException {
        System.out.print("Input year: ");
        String yearInput = CONSOLE.nextLine();

        int year = convertToInt(yearInput);
        if (!isValidYear(year)) {
            System.out.println("Invalid input. The input must be between 1800 and 2050");
            return;
        }

        JSONObject jsonObject = callAPI(year);

        Elipse elipse = new Elipse(jsonObject);
        elipse.print();

        CONSOLE.close();
    }

    private static boolean isValidYear(int year) {
        return year >= 1800 && year <= 2050;
    }

    private static int convertToInt(String s) {
        if (null == s || s.isEmpty()) {
            return -1;
        }

        try {
            return Integer.parseInt(s);
        } catch (Exception e) {
            return -1;
        }
    }

    private static JSONObject callAPI(int year) throws IOException, ParseException {
        String endpoint = API + year;

        URL url = new URL(endpoint);
        InputStream jsonFromEndpoint = url.openStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(jsonFromEndpoint));

        return (JSONObject) (new JSONParser()).parse(br);
    }

    // The class extracts json data
    private static class Elipse {
        private final List<Event> events;
        private final int year;

        public Elipse(JSONObject json) {
            JSONArray arr = (JSONArray)json.get("eclipses_in_year");
            year = getIntValue(json, "year");
            if (null == arr) {
                throw new RuntimeException("Null json array");
            }
            events = new ArrayList<>(arr.size());

            for (Object obj: arr) {
                JSONObject jsonObject = (JSONObject) obj;

                int day = getIntValue(jsonObject, "day");
                int month = getIntValue(jsonObject, "month");
                int year = getIntValue(jsonObject, "year");
                String text = getStringValue(jsonObject, "event");

                Event event = new Event(day, month, year, text);
                events.add(event);
            }
        }

        public void print() {
            System.out.println("============= xxx =============");
            System.out.println("Year: " + year);
            System.out.println("Number of eclipses: " + events.size());

            for (int i = 0, size = events.size(); i < size; i++) {
                Event event = events.get(i);
                System.out.println((i + 1) + ". Event: " + event.getText());
            }
        }
        private int getIntValue(JSONObject object, String key){
            Object val  = object.get(key);
            if (val != null) {
                return ((Long)val).intValue();
            }
            throw new RuntimeException("No integer value for key: " + key);
        }

        private String getStringValue(JSONObject object, String key) {
            Object val  = object.get(key);
            if (val != null) {
                return (String)val;
            }
            throw new RuntimeException("No string value for key: " + key);
        }
    }

    // Placeholder for json data
    private static class Event {
        private final int day;
        private final int month;
        private final int year;
        private final String text;

        public Event(int day, int month, int year, String text) {
            this.day = day;
            this.month = month;
            this.year = year;
            this.text = text;
        }

        public int getDay() {
            return day;
        }

        public int getMonth() {
            return month;
        }

        public int getYear() {
            return year;
        }

        public String getText() {
            return text;
        }
    }
}
