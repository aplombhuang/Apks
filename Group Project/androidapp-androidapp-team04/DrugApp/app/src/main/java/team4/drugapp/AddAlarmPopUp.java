package team4.drugapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;

import java.util.ArrayList;

@TargetApi(Build.VERSION_CODES.N)
public class AddAlarmPopUp extends AppCompatActivity {

    Button Accept;
    TimePicker AlarmPicker;
    Calendar calendar;
    Calendar beforeAlarm;
    CheckBox vibrateCb;
    Spinner minSpinner;
    EditText almDesc;
    int almID=-500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm_pop_up);

        //change the size of the window to look like a pop up
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        AlarmPicker = (TimePicker) findViewById(R.id.AlarmTimePicker);
        calendar = Calendar.getInstance();
        vibrateCb = (CheckBox) findViewById(R.id.vibrateCb);
        minSpinner = (Spinner) findViewById(R.id.minSpinner);
        almDesc = (EditText) findViewById(R.id.AlarmDescriptionTxt);

        //these are the values that get plugged into our spinner for the before alarm
        ArrayList<Integer> befores = new ArrayList<Integer>();
        befores.add(0);
        befores.add(10);
        befores.add(20);
        befores.add(30);

        //assigns the befores araylist to our spinner.
        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, befores);
        minSpinner.setAdapter(spinnerAdapter);

        if (getIntent().hasExtra("ALMEDT")) {
            AlarmsActivity.AlarmObj almEDT = (AlarmsActivity.AlarmObj) getIntent().getSerializableExtra("ALMEDT");
            Calendar cal;
            cal = Calendar.getInstance();
            cal.setTimeInMillis(almEDT.TimeToGoOff);
            almDesc.setText(almEDT.description);

            AlarmPicker.setHour(cal.get(Calendar.HOUR));
            AlarmPicker.setMinute(cal.get(Calendar.MINUTE));
            almID=almEDT.alarmID;
        }


    }

    public void AcceptClick(View view) {

        calendar.set(calendar.HOUR_OF_DAY, AlarmPicker.getHour());
        calendar.set(calendar.MINUTE, AlarmPicker.getMinute());
        //beforeAlarm.add(Calendar.MINUTE, (-1*beforeAmount));
        EditText et = (EditText) findViewById(R.id.AlarmDescriptionTxt);
        Intent sendcal = new Intent();
        Bundle b = new Bundle();
        b.putString("Desc", et.getText().toString());
        sendcal.putExtra("NewAlarm", calendar); //we are passing calendar object so in the future we can use timezones, for now only is good for the timezone the alarm is set for
        sendcal.putExtra("Description", b);
        sendcal.putExtra("Vibrate", vibrateCb.isChecked());

        if ((int) minSpinner.getSelectedItem() != 0) {
            beforeAlarm = Calendar.getInstance();
            beforeAlarm.set(calendar.HOUR_OF_DAY, AlarmPicker.getHour());
            beforeAlarm.set(calendar.MINUTE, AlarmPicker.getMinute());
            beforeAlarm.add(Calendar.MINUTE, -1 * ((int) minSpinner.getSelectedItem())); //sets the before alarm to whatever you have selected in the spinner
        } else {
            beforeAlarm = null;
        }
        sendcal.putExtra("BeforeAlarm", beforeAlarm);
        if(almID!=-500){
            sendcal.putExtra("AlmID",almID);
        }

        setResult(Activity.RESULT_OK, sendcal);
        finish();
    }

}