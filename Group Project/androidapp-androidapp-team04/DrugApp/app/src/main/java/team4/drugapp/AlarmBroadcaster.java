package team4.drugapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;
import android.widget.Toast;

public class AlarmBroadcaster extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Boolean vibrate = intent.getBooleanExtra("Vibrate", false);
        String action = intent.getAction();

        if (action == "Stop") {
            Toast.makeText(context, "Alarm Stopped", Toast.LENGTH_LONG).show();
        }
        else if (action == "Start") {
            //When alarm plays I need to start an Alarm playing activity here
            Intent startIntent = new Intent(context, RingtonePlayingService.class);
            startIntent.putExtra("Vibrate", vibrate);
            context.startService(startIntent);

            Intent alarmPlaying = new Intent(context, AlarmExecuting.class);
            alarmPlaying.putExtra("AlmID",intent.getIntExtra("AlmID",-1));
            context.startActivity(alarmPlaying);
        }
    }
}
