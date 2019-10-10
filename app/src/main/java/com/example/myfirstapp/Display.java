package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Display extends AppCompatActivity {
    private TextView mMyTitle;
    private TextView mMyTime;
    private TextView mMyTasks;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        mMyTitle =(TextView)findViewById(R.id.mytitle);
        mMyTime=(TextView)findViewById(R.id.myTime);
        mMyTasks=(TextView)findViewById(R.id.myTask);
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String timeDate = intent.getStringExtra("timeDate");
        String task = intent.getStringExtra("task");



        mMyTitle.setText("My tasks" +"\n"+ title);
        mMyTime.setText( timeDate);
        mMyTasks.setText( task);


    }
}
