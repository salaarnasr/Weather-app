package weather.app;

import org.json.JSONObject;

public class WeatherApiTest {

    public static void main(String[] args) {
        // Replace with actual latitude and longitude values
        double latitude = 33.44;
        double longitude = -94.04;

        // Call the WeatherApiHandler to get weather data
        JSONObject weatherData = WeatherApiHandler.getWeatherData(latitude, longitude);

        if (weatherData != null) {
            // Print the JSON response to the console
            System.out.println(weatherData.toString(4)); // Use toString(4) for pretty-printing
        } else {
            System.out.println("Failed to fetch weather data.");
        }
    }
}
