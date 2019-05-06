package e.ar_g.weatherapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BootBroadcastReceiver extends BroadcastReceiver {
  @Override public void onReceive(Context context, Intent intent) {
    Toast.makeText(context.getApplicationContext(), "We received boot broadcast!", Toast.LENGTH_SHORT).show();

    Intent startServiceIntent = new Intent(context.getApplicationContext(), WeatherUpdatesService.class);
    context.getApplicationContext().startService(startServiceIntent);
  }
}
