package weather.app;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherApiClient {
    public static void main(String[] args) {
        try {
            // Replace with your API key
            String apiKey = "5479e1f7c4dd6211fc24360ccdcffc68" //
            ;

            // Replace with the latitude and longitude of your location
            String latitude = "33.44";
            String longitude = "-94.04";

            // Construct the API URL
            String apiUrl = "https://api.openweathermap.org/data/3.0/onecall?lat=" + latitude +
                    "&lon=" + longitude + "&exclude=hourly,daily&appid=" + apiKey;

            // Create a URL object
            URL url = new URL(apiUrl);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            // Check if the response code is 200 (OK)
            if (responseCode == 200) {
                // Read the response from the API
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();

                // Print the API response
                System.out.println(response.toString());
            } else {
                // Handle the error (e.g., by printing an error message)
                System.err.println("Error: " + responseCode);
            }

            // Close the connection
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
