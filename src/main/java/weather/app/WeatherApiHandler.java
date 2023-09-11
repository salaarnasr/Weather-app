package weather.app;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiHandler {

    private static final String API_KEY = "5479e1f7c4dd6211fc24360ccdcffc68" //
    ;
    private static final String API_BASE_URL = "https://api.openweathermap.org/data/3.0/onecall";

    public static JSONObject getWeatherData(double latitude, double longitude) {
        try {
            // Create the API request URL
            String apiUrl = API_BASE_URL + "?lat=" + latitude + "&lon=" + longitude +
                    "&exclude=hourly,daily&appid=" + API_KEY;

            // Send an HTTP GET request to the API
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            // Read the API response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response
            return new JSONObject(response.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
