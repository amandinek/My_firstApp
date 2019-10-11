package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class YourList extends AppCompatActivity {

    private EditText mTitle;
    private EditText mTask;
    private Button mAcivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_list);
        mTitle =(EditText) findViewById(R.id.title);
        mTask =(EditText) findViewById(R.id.task);
        mAcivity=(Button) findViewById(R.id.viewact);

        mAcivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = mTitle.getText().toString();
                String task = mTask.getText().toString();

                Intent intent = new Intent(YourList.this, Display.class);
                intent.putExtra("title", title);
                intent.putExtra("task", task);

                startActivity(intent);

            }
        });



    }
}
