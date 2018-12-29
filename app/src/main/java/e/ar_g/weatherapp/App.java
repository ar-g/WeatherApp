package e.ar_g.weatherapp;

import android.app.Application;
import android.content.Context;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class App extends Application {

  private OpenWeatherApi openWeatherApi;

  public OpenWeatherApi getOpenWeatherApi(){
    if (openWeatherApi == null) {
      Retrofit retrofit = new Retrofit.Builder()
        .baseUrl("http://api.openweathermap.org/")
        .addConverterFactory(GsonConverterFactory.create())
        .build();

      openWeatherApi = retrofit.create(OpenWeatherApi.class);
    }
    return openWeatherApi;
  }

  public static App getApp(Context context){
    return ((App) context.getApplicationContext());
  }
}
