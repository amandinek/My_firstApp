package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class YourList extends AppCompatActivity implements View.OnClickListener {

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
        mActivity.setOnClickListener(this);
    }


            @Override
            public void onClick(View v) {
        if(v == mActivity) {
            String task = mTask.getText().toString();

            Intent intent = new Intent(YourList.this, Display.class);
            intent.putExtra("task", task);

            startActivity(intent);
        }
    }
}
