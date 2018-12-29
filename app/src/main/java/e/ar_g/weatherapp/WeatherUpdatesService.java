package e.ar_g.weatherapp;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherUpdatesService extends Service {

  @Override public void onCreate() {
    super.onCreate();

    Toast.makeText(this, "Service is here", Toast.LENGTH_SHORT).show();

    NotificationCompat.Builder builder = new NotificationCompat
      .Builder(getApplicationContext(), "42");

    startForeground(101, builder.build());

    OpenWeatherApi openWeatherApi = App.getApp(this).getOpenWeatherApi();

    Call<WeatherResponse> call = openWeatherApi.getCurrentWeather("Kiev,ua", OpenWeatherApi.API_KEY);
    call.enqueue(new Callback<WeatherResponse>() {
      @Override public void onResponse(Call<WeatherResponse> call, Response<WeatherResponse> response) {

        if (response.isSuccessful()) {
          NotificationCompat.Builder builder = new NotificationCompat
            .Builder(getApplicationContext(), "42");

          double temperature = response.body().getMain().getTemp();

          builder.setContentText(temperature + " 0F");
          builder.setSmallIcon(R.drawable.ic_launcher_background);

          Notification notification = builder.build();

          NotificationManagerCompat.from(getApplicationContext())
            .notify(101, notification);
        }
      }

      @Override public void onFailure(Call<WeatherResponse> call, Throwable t) {

      }
    });
  }

  @Override public int onStartCommand(Intent intent, int flags, int startId) {
    return Service.START_STICKY;
  }

  @Nullable @Override public IBinder onBind(Intent intent) {
    return null;
  }
}
