package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        mlistTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                String task =((TextView)view).getText().toString();
                Toast.makeText(Display.this,task, Toast.LENGTH_LONG).show();
            }
        });
        mMyTime.setText(dTime);
        mMyTasks.setText( task);


    }
}
