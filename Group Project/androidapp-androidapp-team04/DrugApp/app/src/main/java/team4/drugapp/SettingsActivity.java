package team4.drugapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase authentication object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;


    // reference to store data to db
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance();

        //if user is not logged in current user will be null
        if(firebaseAuth.getCurrentUser() == null){
            //closing this activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();




        FirebaseUser user = firebaseAuth.getCurrentUser();




        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.UserSurvy_btn2);

        //displaying logged in user name
        textViewUserEmail.setText("Current User is "+ user.getEmail());

        //  adding listener to buttons
        buttonLogout.setOnClickListener(this);
    }

    public void startProfileActivity (View view){
        Intent startProfileActivityIntent= new Intent(this, ProfileActivity.class);
        startActivity(startProfileActivityIntent);
    }

    @Override
    public void onClick(View view) {

        //if logout is clicked
        if (view == buttonLogout) {
            //logging out user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }



     }
    }
