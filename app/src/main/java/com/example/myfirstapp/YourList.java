package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YourList extends AppCompatActivity implements View.OnClickListener {

//    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
private DatabaseReference mSearchedLocationReference;
private ValueEventListener mSearchedLocationReferenceListener;

//    private EditText mTask;
//    private Button mAcivity;
    @BindView(R.id.task) EditText mTask;
    @BindView(R.id.viewact) Button mActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_list);
//        mTask =(EditText) findViewById(R.id.task);
//        mAcivity=(Button) findViewById(R.id.viewact);
        ButterKnife.bind(this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();

        mSearchedLocationReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_LOCATION);

        mSearchedLocationReference.addValueEventListener(new ValueEventListener() { //attach listener

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot locationSnapshot : dataSnapshot.getChildren()) {
                    String location = locationSnapshot.getValue().toString();
                    Log.d("Locations updated", "location: " + location); //log
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) { //update UI here if error occurred.

            }
        });



        mActivity.setOnClickListener(this);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedLocationReference.removeEventListener(mSearchedLocationReferenceListener);
    }



    @Override
            public void onClick(View v) {
        if(v == mActivity) {
            String task = mTask.getText().toString();
//            if(!(task).equals("")) {
//                addToSharedPreferences(task);
//            }
            saveLocationToFirebase(task);

            Intent intent = new Intent(YourList.this, Display.class);
            intent.putExtra("task", task);

            startActivity(intent);
        }
    }

//    private void addToSharedPreferences(String location) {
//        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
//    }

//    public void saveLocationToFirebase(String location) {
//        mSearchedLocationReference.setValue(location);
//    }

    public void saveLocationToFirebase(String location) {
        mSearchedLocationReference.push().setValue(location);
    }
}
