package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Display extends AppCompatActivity {
//    private TextView mMyTime;
//    private TextView mMyTasks;
    private ListView mlistTask;
    @BindView(R.id.myTime) TextView mMyTime;
    @BindView(R.id.myTask) TextView mMyTasks;

    private String[] toDo= new String[]{"eat","work out","clean","visit","help"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Calendar calendar=Calendar.getInstance();
        String dTime= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
//        mMyTime=(TextView)findViewById(R.id.myTime);
//        mMyTasks=(TextView)findViewById(R.id.myTask);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String task = intent.getStringExtra("task");
        mlistTask=(ListView)findViewById(R.id.listTask);

        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,toDo);
        mlistTask.setAdapter(adapter);


        mMyTime.setText(dTime);
        mMyTasks.setText( task);


    }
}
