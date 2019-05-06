package e.ar_g.weatherapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherApi {

  String API_KEY = "db5de5623c840cdd78db529d260bd495";

  @GET("/data/2.5/weather")
  Call<WeatherResponse> getCurrentWeather(
    @Query("q") String cityAndCountryCode,
    @Query("APPID") String apiKey,
    @Query("units") String units
  );
}
