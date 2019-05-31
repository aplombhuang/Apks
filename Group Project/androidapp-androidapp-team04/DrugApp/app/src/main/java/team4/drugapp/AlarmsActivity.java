package team4.drugapp;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.SystemClock;
import android.preference.PreferenceManager;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.content.Context.ALARM_SERVICE;

public class AlarmsActivity extends AppCompatActivity {
    public static ArrayList <AlarmObj> AlarmList=new ArrayList<AlarmObj>();
    ListView AlarmListView;
    ArrayAdapter<AlarmObj> LvAdapter;
    SharedPreferences sharedPrefs;
    Button addNewAlarm;
    Button delSelAlm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarms);
        sharedPrefs=PreferenceManager.getDefaultSharedPreferences(this.getApplicationContext());
        AlarmList=loadAlarms();
        addNewAlarm=(Button) findViewById(R.id.AddAlarmBtn);
        delSelAlm=(Button) findViewById(R.id.almDelSelBtn);
        delSelAlm.setVisibility(View.INVISIBLE);

        AlarmListView=(ListView) findViewById(R.id.AlarmListView);
        LvAdapter=new ListAdaptor(this,R.layout.alarm_list_item,AlarmList);

        //Tells the ListView what adapter to use for itself
        AlarmListView.setAdapter(LvAdapter);


        AlarmListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent startAddAlarmPopUp= new Intent(parent.getContext(), AddAlarmPopUp.class);
                AlarmObj edt=AlarmList.get(position);
                startAddAlarmPopUp.putExtra("ALMEDT", edt);
                startActivityForResult(startAddAlarmPopUp,1);
            }
        });

        //Long click Listener for the AlarmListView
        AlarmListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                CheckBox cb= (CheckBox) view.findViewById(R.id.almSelected);
                cb.setVisibility(View.VISIBLE);
                cb.setChecked(true);
                addNewAlarm.setVisibility(View.INVISIBLE);
                delSelAlm.setVisibility(View.VISIBLE);
                return false;
            }
        });
    }

    /*
    I am using the Gson Library to be able to save and load the users alarm objects
    more info: https://github.com/google/gson

     When the activity is closed we are going to save the AlarmList to local storage before closing the activity*/
    @Override
    public void onDestroy(){
        saveAlarms();
        super.onDestroy();
    }

    @TargetApi(Build.VERSION_CODES.N)
    public void startAddNewAlarmActivity(View view){
        Intent startAddAlarmPopUp= new Intent(this, AddAlarmPopUp.class);
        startActivityForResult(startAddAlarmPopUp,1);
    }

    public void onDeletePress(View view){
        //Pro programmers tip #01100100 when deleting multiple items in a for loop count down the list and delete, youll thank me later
        for(int i=AlarmListView.getCount()-1; i>=0;){
            CheckBox cb= (CheckBox) AlarmListView.getChildAt(i).findViewById(R.id.almSelected);
            if(cb.isChecked()){
                cb.setChecked(false);
                setAlarm(AlarmList.get(i).alarmID,AlarmList.get(i),null,true);
                AlarmList.remove(i);
                LvAdapter.notifyDataSetChanged();
            }
            else {
                i--;
            }
        }
        delSelAlm.setVisibility(View.INVISIBLE);
        addNewAlarm.setVisibility(View.VISIBLE);
        for(int i=0; i<AlarmListView.getCount();i++){
            CheckBox cb= (CheckBox) AlarmListView.getChildAt(i).findViewById(R.id.almSelected);
            cb.setChecked(false);
            AlarmListView.getChildAt(i).findViewById(R.id.almSelected).setVisibility(View.INVISIBLE);
        }
        saveAlarms();
    }

    @Override
    public void onBackPressed(){ //handles the press of the Back button
        //swap the visibility of buttons
        if(delSelAlm.getVisibility()==View.VISIBLE){
            delSelAlm.setVisibility(View.INVISIBLE);
            addNewAlarm.setVisibility(View.VISIBLE);

            for(int i=0; i<AlarmListView.getCount();i++){// Item in the List view is unselected and check box dissapears
                CheckBox cb= (CheckBox) AlarmListView.getChildAt(i).findViewById(R.id.almSelected);
                cb.setChecked(false);
                AlarmListView.getChildAt(i).findViewById(R.id.almSelected).setVisibility(View.INVISIBLE);
            }
        }
        else{
            finish();
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                //the next block is made to recieve new alarms that are created
                Bundle extras=data.getBundleExtra("Description");//For some reason you have to send strings between using a "Bundle"
                if(extras != null){//If a new alarm was actually created
                    Calendar cal= (Calendar) data.getSerializableExtra("NewAlarm");//we recieve the new alarm

                    //retrieving vibrate checkbox from the intent
                    boolean vibrateOpt=(boolean) data.getSerializableExtra("Vibrate");
                    int alarmID = AlarmList.size() + 1;

                    //retrieving description from the intent.
                    String desc= (String) extras.getString("Desc");

                    //now we need to make an Alarm object using the information we have (for now I will only have the time and description)
                    AlarmObj newAlarm;
                    if(data.hasExtra("AlmID")){
                        alarmID=(int) data.getSerializableExtra("AlmID");
                        newAlarm=AlarmList.get(alarmID-1);
                        newAlarm.description=desc;
                        newAlarm.TimeToGoOff=cal.getTimeInMillis();
                        newAlarm.vibrate=vibrateOpt;

                    }
                    else{
                        newAlarm=new AlarmObj(cal.getTimeInMillis(),desc,alarmID,vibrateOpt);
                        AlarmList.add(newAlarm);
                    }

                    //this makes the pending intent for the alarm and sets it with the alarm service in android.
                    setAlarm(alarmID,newAlarm,cal,false);

                    //retrieves and sets the beforealarm
                    Calendar beforecal= (Calendar) data.getSerializableExtra("BeforeAlarm");
                    if(beforecal!=null){
                        setAlarm(alarmID*-1,newAlarm,beforecal,false);
                    }

                    saveAlarms();
                    LvAdapter.notifyDataSetChanged();
                }
            }
        }
    }

    //This sets up both the pending intent and alarm Manager for an alarm, also can cancel given the cancel flag
    @TargetApi(Build.VERSION_CODES.N)
    public void setAlarm(int ID,AlarmObj alm, Calendar alrm, boolean cancel){
        int alarmID=ID;
        Intent alarmIntent = new Intent(this, AlarmBroadcaster.class);
        alarmIntent.setAction("Start");
        alarmIntent.putExtra("Vibrate",alm.vibrate);
        alarmIntent.putExtra("AlmID",alm.alarmID);

        PendingIntent pi = PendingIntent.getBroadcast(this.getApplicationContext(), alarmID, alarmIntent, 0);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);

        if(cancel){//if we are canceling the alarm we enter this loop to cancel both the pending intent and the alarm manager
            pi.cancel();
            am.cancel(pi);
            return;
        }

        Date today=new Date(); //this allows us to set an alarm that spans to the next day.
        if(alrm.before(today)){
            alrm.add(Calendar.DATE,1);
        }
        am.set(AlarmManager.RTC_WAKEUP, alrm.getTimeInMillis(), pi);
    }

    //This method Load all the alarms that are stored on local memory
    public ArrayList<AlarmObj> loadAlarms() {
        ArrayList<AlarmObj> alarms = new ArrayList<AlarmObj>();
        Gson gson = new Gson();
        String json = sharedPrefs.getString("AlarmList", "");
        if (json.isEmpty()) {
            alarms = new ArrayList<AlarmObj>();
        } else {
            Type type = new TypeToken<List<AlarmObj>>() {
            }.getType();
            alarms = gson.fromJson(json, type);
        }
        return alarms;
    }

    public void saveAlarms(){
        SharedPreferences.Editor prefsEditor = sharedPrefs.edit();
        prefsEditor.remove("AlarmList");//we want to remove the old AlarmList (In case Alarms are edited)
        prefsEditor.commit();

        Gson gson = new Gson(); //and add the new AlarmList
        String json = gson.toJson(AlarmList);
        prefsEditor.putString("AlarmList", json);
        prefsEditor.commit();

    }

    public void startSettingsActivity (View view){//starts the settings Activity
        Intent startSettingsIntent= new Intent(this, SettingsActivity.class);
        startActivity(startSettingsIntent);
    }

    //Alarm object that is stored
    class AlarmObj implements Serializable{
        public Long TimeToGoOff; //this is the time that the alarm goes off
        public String description;
        public int alarmID;
        public boolean vibrate;
        public boolean snoozewarning1;
        public boolean snoozewarning2;
        public boolean snoozewarning3;

        @TargetApi(Build.VERSION_CODES.N)
        public AlarmObj(Long time, String desc, int ID, boolean vib){
            description=desc;
            TimeToGoOff=time;
            alarmID=ID;
            vibrate=vib;
        }

        @TargetApi(Build.VERSION_CODES.N)
        public void setSnooze (long snoozeTime)
        {
            Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(snoozeTime);
                setAlarm(alarmID*-1,this,cal,false);
        }

    }

    //This class is to adapt our alarm_list_item layout to our listview
    private class ListAdaptor extends ArrayAdapter<AlarmObj> {
        private int layout;

        private ListAdaptor(@NonNull Context context, @LayoutRes int resource, @NonNull List<AlarmObj> objects) {
            super(context, resource, objects);
            layout=resource;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder mainViewHolder=null;
            if(convertView==null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView=inflater.inflate(layout,parent,false);
                ViewHolder viewholder= new ViewHolder();
                viewholder.txt= (TextView) convertView.findViewById(R.id.DrugNameTxtV);
                viewholder.img= (ImageView) convertView.findViewById(R.id.drugImg);
                viewholder.almSelected=(CheckBox) convertView.findViewById(R.id.almSelected);
                viewholder.almSelected.setVisibility(View.INVISIBLE);
                convertView.setTag(viewholder);
            }
                mainViewHolder = (ViewHolder) convertView.getTag();
                mainViewHolder.txt.setText(getItem(position).description); //sets the text field

            return convertView;
        }
    }

    public class ViewHolder{//This is the view for each item in the AlarmListView
        TextView txt;
        ImageView img;
        CheckBox almSelected;
    }
}