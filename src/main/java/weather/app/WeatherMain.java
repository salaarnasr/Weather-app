package weather.app;

import org.json.JSONObject;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WeatherMain extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Weather App");

        // Create a layout for the UI
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Create UI components
        Label latitudeLabel = new Label("Latitude:");
        GridPane.setConstraints(latitudeLabel, 0, 0);

        TextField latitudeInput = new TextField();
        GridPane.setConstraints(latitudeInput, 0, 1);

        Label longitudeLabel = new Label("Longitude:");
        GridPane.setConstraints(longitudeLabel, 0, 2);

        TextField longitudeInput = new TextField();
        GridPane.setConstraints(longitudeInput, 0, 3);

        Button searchButton = new Button("Search");
        GridPane.setConstraints(searchButton, 0, 4);

        Label resultLabel = new Label("Weather Info Will Be Displayed Here");
        GridPane.setConstraints(resultLabel, 0, 5);

        // Handle button click event
        searchButton.setOnAction(e -> {
            String latitudeStr = latitudeInput.getText();
            String longitudeStr = longitudeInput.getText();

            // Validate latitude and longitude inputs
            if (latitudeStr.isEmpty() || longitudeStr.isEmpty()) {
                resultLabel.setText("Please enter latitude and longitude.");
            } else {
                try {
                    // Parse latitude and longitude to double values
                    double latitude = Double.parseDouble(latitudeStr);
                    double longitude = Double.parseDouble(longitudeStr);

                    // Retrieve weather data using the WeatherApiHandler
                    WeatherApiHandler weatherApiHandler = new WeatherApiHandler();
                    JSONObject weatherData = WeatherApiHandler.getWeatherData(latitude, longitude);

                    // Extract relevant information from the JSONObject
                    double temperature = weatherData.getJSONObject("current").getDouble("temp");
                    String description = weatherData.getJSONObject("current").getJSONArray("weather").getJSONObject(0)
                            .getString("description");
                    int humidity = weatherData.getJSONObject("current").getInt("humidity");
                    double windSpeed = weatherData.getJSONObject("current").getDouble("wind_speed");
                    double pressure = weatherData.getJSONObject("current").getDouble("pressure");
                    double visibility = weatherData.getJSONObject("current").getDouble("visibility");
                    int cloudiness = weatherData.getJSONObject("current").getInt("clouds");
                    int uvIndex = weatherData.getJSONObject("current").getInt("uvi");
                    int sunSet = weatherData.getJSONObject("current").getInt("sunset");
                    int sunRise = weatherData.getJSONObject("current").getInt("sunrise");
                    double feelsLike = weatherData.getJSONObject("current").getDouble("feels_like");
                    double dewPoint = weatherData.getJSONObject("current").getDouble("dew_point");

                    // Format weather information as a String
                    String weatherInfo = String.format(
                            "Temperature: %.2f°C\nDescription: %s\nHumidity: %d%%\nWind Speed: %.2f m/s\nPressure: %.2f hPa\nVisibility: %.2f meters\nCloudiness: %d%%\nUV Index: %d\nSunrise: %s\nSunset: %s\nFeels Like: %.2f°C\nDew Point: %.2f°C",
                            temperature, description, humidity, windSpeed, pressure, visibility, cloudiness, uvIndex,
                            sunRise, sunSet, feelsLike, dewPoint);

                    // Update the resultLabel with the weather information
                    resultLabel.setText(weatherInfo);
                } catch (NumberFormatException ex) {
                    resultLabel.setText("Invalid latitude or longitude format.");
                    ex.printStackTrace();
                } catch (Exception ex) {
                    resultLabel.setText("Error: Unable to retrieve weather data.");
                    ex.printStackTrace();
                }
            }
        });

        // Add components to the layout
        grid.getChildren().addAll(latitudeLabel, latitudeInput, longitudeLabel, longitudeInput, searchButton,
                resultLabel);

        // Create the scene
        Scene scene = new Scene(grid, 400, 200);
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // You can add methods here to retrieve weather data from an API and update the
    // UI accordingly.
}
