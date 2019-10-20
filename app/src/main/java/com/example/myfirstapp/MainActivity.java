package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

//    private Button mSendButton;
    @BindView(R.id.sendButton) Button mSendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mSendButton.setOnClickListener(this);
    }

            @Override
            public void onClick(View v) {
                if (v == mSendButton) {
                    Intent intent = new Intent(MainActivity.this, YourList.class);
                    startActivity(intent);

                }
            }

}