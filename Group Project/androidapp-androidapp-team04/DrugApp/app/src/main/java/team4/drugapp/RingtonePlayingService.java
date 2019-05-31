package team4.drugapp;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.os.Vibrator;
import android.widget.Toast;


/*
This class was built off of one of the replies to this stack overflow thread
http://stackoverflow.com/questions/14089380/how-do-i-stop-the-currently-playing-ringtone
 */
public class RingtonePlayingService extends Service
{
    private Ringtone ringtone;
    private Vibrator vibrator;
    @Override
    public IBinder onBind(Intent intent)
    {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        vibrator= (Vibrator) this.getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
        Toast.makeText(this.getApplicationContext(), "Alarm Is Going Off", Toast.LENGTH_LONG).show();
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        boolean vibrateOn=intent.getBooleanExtra("Vibrate",false);

        if(vibrateOn){
            vibrator.vibrate(5000);
        }
        else{
            this.ringtone = RingtoneManager.getRingtone(this, notification);
            ringtone.play();
        }

        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy()
    {
        if(ringtone!=null){
            ringtone.stop();
        }
        vibrator.cancel();
    }
}