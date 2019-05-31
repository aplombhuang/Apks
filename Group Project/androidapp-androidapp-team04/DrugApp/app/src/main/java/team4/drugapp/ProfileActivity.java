package team4.drugapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase authentication object
    private FirebaseAuth firebaseAuth;

    //view objects
    private TextView textViewUserEmail;
    private Button buttonLogout;


    // reference to store data to db
    private DatabaseReference databaseReference;

    private EditText editTextName, editTextAddress, editTextGender, editTextWeight, editTextAge;
    private Button buttonSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

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

        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextGender = (EditText) findViewById(R.id.editTextGender);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        buttonSave = (Button) findViewById(R.id.buttonSave);


        FirebaseUser user = firebaseAuth.getCurrentUser();


        //initializing views
        textViewUserEmail = (TextView) findViewById(R.id.textViewUserEmail);
        buttonLogout = (Button) findViewById(R.id.UserSurvy_btn);

        //displaying logged in user name
        textViewUserEmail.setText("Please take a moment to complete our user survey "+ user.getEmail());


        //  adding listener to buttons
        buttonLogout.setOnClickListener(this);
        buttonSave.setOnClickListener(this);


    }

    public void startMainMenuActivity (View view){
        Intent startMainMenuIntent= new Intent(this, MainMenuActivity.class);
        startActivity(startMainMenuIntent);
    }

    private void saveUserInformation(){
        String name = editTextName.getText().toString().trim();
        String add = editTextAddress.getText().toString().trim();
        String gender = editTextGender.getText().toString().trim();
        String weight = editTextWeight.getText().toString().trim();
        String age = editTextAge.getText().toString().trim();

        UserInformation userInformation = new UserInformation(name, add, gender, weight, age);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        databaseReference.child(user.getUid()).setValue(userInformation);

        Toast.makeText(this, "Information Saved...", Toast.LENGTH_LONG).show();
    }



    @Override
    public void onClick(View view){

        //if logout is clicked
        if(view == buttonLogout){
            //logging out user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, LoginActivity.class));
        }

        if(view == buttonSave){
            saveUserInformation();
        }
    }
}
