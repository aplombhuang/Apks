package team4.drugapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static team4.drugapp.R.id.ListView1;
//import static team4.drugapp.R.id.vaalues;

public class ManageDrugsActivity extends AppCompatActivity {
    private Button settings;
    ListView lv;
    EditText nameTxt;
    Button addBtn,updateBtn,deleteBtn;
    ArrayList<String> names=new ArrayList<String>();
    ArrayAdapter<String> adapter;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_manage_drugs);
            lv=(ListView)findViewById(R.id.ListView1);
            nameTxt=(EditText) findViewById(R.id.nameTxt);
            addBtn=(Button) findViewById(R.id.addbtn);
            updateBtn=(Button) findViewById(R.id.updatebtn);
            deleteBtn=(Button) findViewById(R.id.deletebtn);

            //Adapter
            adapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, names);
            lv.setAdapter(adapter);

            //set selected item
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> arg0, View v, int pos,
                                        long id) {
                    //TODO Auto-generated method stub

                    nameTxt.setText(names.get(pos));
                }
            });

            //handle events
            addBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {

                    add();
                }
            });

            updateBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {

                    update();
                }
            });

            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View arg0) {

                    delete();
                }
            });

        }





    //add
    private void add()
    {
        String name=nameTxt.getText().toString();

        if(!name.isEmpty() && name.length() > 0)
        {
            adapter.add(name);

            //refresh
            adapter.notifyDataSetChanged();

            nameTxt.setText("");

            Toast.makeText(getApplicationContext(), "Added " + name, Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getApplicationContext(), " ! Please enter a drug to add", Toast.LENGTH_SHORT).show();
        }

    }

    //update
    private void update()
    {
        String name =nameTxt.getText().toString();

        //get pos of selected item
        int pos= lv.getCheckedItemPosition();

        if(!name.isEmpty() && name.length() > 0)
        {
            //remove current item
            adapter.remove(names.get(pos));

            //insert item
            adapter.insert(name, pos);

            //refresh
            adapter.notifyDataSetChanged();

            Toast.makeText(getApplicationContext(), "Updated " + name, Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(getApplicationContext(), " ! Nothing to update", Toast.LENGTH_SHORT).show();
        }
    }


    //delete
    private void delete()
    {
        int pos=lv.getCheckedItemPosition();

        if(pos > -1)
        {
            //remove
            adapter.remove(names.get(pos));

            //refresh
            adapter.notifyDataSetChanged();

            nameTxt.setText("");
            Toast.makeText(getApplicationContext(), "Deleted " , Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), " ! Nothing to delete", Toast.LENGTH_SHORT).show();
        }


    }






    public void startSettingsActivity (View view){
        Intent startSettingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(startSettingsIntent);
    }
}