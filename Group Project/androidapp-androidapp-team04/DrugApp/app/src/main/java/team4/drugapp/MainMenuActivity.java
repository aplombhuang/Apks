package team4.drugapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void startSettingsActivity (View view){
        Intent startSettingsIntent= new Intent(this, SettingsActivity.class);
        startActivity(startSettingsIntent);
    }

    public void startManageDrugsActivity (View view){
        Intent startManageDrugsIntent= new Intent(this, ManageDrugsActivity.class);
        startActivity(startManageDrugsIntent);
    }

    public void startAlarmsActivity (View view){
        Intent startAlarmsIntent= new Intent(this, AlarmsActivity.class);
        startActivity(startAlarmsIntent);
    }

    public void startDrugDictionaryActivity (View view){
        Intent startDrugDictionaryIntent= new Intent(this, DrugDictionaryActivity.class);
        startActivity(startDrugDictionaryIntent);
    }
    public void startDrugInteractionActivity (View view){
        Intent startDrugInteractionActivityIntent= new Intent(this, DrugInteractionActivity.class);
        startActivity(startDrugInteractionActivityIntent);
    }



}
