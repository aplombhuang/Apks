package team4.drugapp;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class AlarmExecuting extends AppCompatActivity {
    Intent startIntent;
    Context context;
    ImageButton condition1;
    ImageButton condition2;
    ImageButton condition3;
    ImageButton condition4;
    int alm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_executing);
        condition1=(ImageButton) findViewById(R.id.Condition1Btn);
        condition2=(ImageButton) findViewById(R.id.Condition2Btn);
        condition3=(ImageButton) findViewById(R.id.Condition3Btn);
        condition4=(ImageButton) findViewById(R.id.Condition4Btn);
    }

    public void tookMedicineClick(View view){
        Intent stopAlarm=new Intent(this.getApplicationContext(),RingtonePlayingService.class);
        this.getApplicationContext().stopService(stopAlarm);
        //Here we will deduct 1 pill from our records of how many pills of this subscription the user has
        //Here we will log if the user took their medicine with a timestamp and name of the medicine.

        finish();
    }

    public void notTakingMedicineClick(View view){
        Intent stopAlarm=new Intent(this.getApplicationContext(),RingtonePlayingService.class);
        this.getApplicationContext().stopService(stopAlarm);
        //Here we will log if the user did not take their medicine with a timestamp and name of the medicine.

        finish();
    }
//the if loop in this method is very messy :/ need to come back and optimize, a switch was harder to implement for this than expected
    public void conditionclick(View view){
        if(view.getId()==findViewById(R.id.Condition1Btn).getId()){
            Toast.makeText(this.getApplicationContext(), "Condition1 Text goes here", Toast.LENGTH_LONG).show();
        }
        else if(view.getId()==findViewById(R.id.Condition2Btn).getId()){
            Toast.makeText(this.getApplicationContext(), "Condition2 Text goes here", Toast.LENGTH_LONG).show();
        }
        else if(view.getId()==findViewById(R.id.Condition3Btn).getId()){
            Toast.makeText(this.getApplicationContext(), "Condition3 Text goes here", Toast.LENGTH_LONG).show();
        }
        else if(view.getId()==findViewById(R.id.Condition4Btn).getId()){
            Toast.makeText(this.getApplicationContext(), "Condition4 Text goes here", Toast.LENGTH_LONG).show();
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void snoozeClick(View view){
        alm=this.getIntent().getIntExtra("AlmID",-1)-1;
        AlarmsActivity.AlarmObj almO=AlarmsActivity.AlarmList.get(alm);
        Calendar cal=Calendar.getInstance();
        cal.add(Calendar.MINUTE,2);
        cal.setTimeInMillis(cal.getTimeInMillis());
        almO.setSnooze(cal.getTimeInMillis());
        Toast.makeText(this.getApplicationContext(), "Snooze Set", Toast.LENGTH_LONG).show();
        finish();
    }
}