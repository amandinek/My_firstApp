package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Display extends AppCompatActivity {
    private TextView mMyTitle;
    private TextView mMyTime;
    private TextView mMyTasks;
    private ListView mlistTask;

    private String[] toDo= new String[]{"eat","work out","clean","visit","help"};
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
        mlistTask=(ListView)findViewById(R.id.listTask);

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,toDo);
        mlistTask.setAdapter(adapter);



        mMyTitle.setText("My tasks" +"\n"+ title);
        mMyTime.setText( timeDate);
        mMyTasks.setText( task);


    }
}
