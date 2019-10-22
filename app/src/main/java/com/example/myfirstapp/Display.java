package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.myfirstapp.models.CheckList;

import com.example.myfirstapp.adapters.EventsListAdapter;
import com.example.myfirstapp.adapters.ReviewsArrayAdapter;
import com.example.myfirstapp.models.Business;
import com.example.myfirstapp.models.Category;
import com.example.myfirstapp.models.YelpEventResponse;
import com.example.myfirstapp.network.ApiService;
import com.example.myfirstapp.network.YelpClient;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
//import retrofit2.Call;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Display extends AppCompatActivity {

    private ListView mlistTask;
    @BindView(R.id.myTime) TextView mMyTime;
//    @BindView(R.id.myTask) TextView mMyTasks;
    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;
    private EventsListAdapter mAdapter;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    public List<Business> review;



    private String[] toDo= new String[]{"eat","work out","clean","visit","help"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Calendar calendar = Calendar.getInstance();
        String dTime = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());

        ButterKnife.bind(this);
        Intent intent = getIntent();
        String task = intent.getStringExtra("task");
        Toast.makeText(this, "" + task, Toast.LENGTH_SHORT).show();
        mlistTask = (ListView) findViewById(R.id.listTask);


        mMyTime.setText(dTime);
//        mMyTasks.setText(task);

        ApiService client = YelpClient.getClient();

        Call<YelpEventResponse> call = client.getPublicEvents(task, "location");
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        call.enqueue(new Callback<YelpEventResponse>() {
            @Override
            public void onResponse(Call<YelpEventResponse> call, Response<YelpEventResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
//                    List<Business> reviewList = response.body().getBusinesses();
//                    String[] review = new String[reviewList.size()];
//                    String[] categories = new String[reviewList.size()];
//                    for (int i = 0; i < review .length; i++){
//                        review [i] = reviewList.get(i).getName();
//                    }

                     review= response.body().getBusinesses();
//                    ArrayAdapter adapter
                    mAdapter = new EventsListAdapter(Display.this,review);
                    mRecyclerView.setAdapter(mAdapter);

                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(Display.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);


                    showReview();


                }else {
                    showUnsuccessfulMessage();
                }

            }

            @Override
            public void onFailure(Call<YelpEventResponse> call, Throwable t) {
                mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
                mErrorTextView.setVisibility(View.VISIBLE);

            }
        });
    }




    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showReview() {
        mlistTask.setVisibility(View.VISIBLE);
//        mLocationTextView.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
    }
}
