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
//    public List<Business> reviews;
    private ListView mlistTask;
    @BindView(R.id.myTime) TextView mMyTime;
    @BindView(R.id.myTask) TextView mMyTasks;
    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;
    private EventsListAdapter mAdapter;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;





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

//        ArrayAdapter adapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,toDo);
//        mlistTask.setAdapter(adapter);

        mlistTask.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long id) {
                String task =((TextView)view).getText().toString();

                Toast.makeText(Display.this,task, Toast.LENGTH_LONG).show();
            }
        });
        mMyTime.setText(dTime);
        mMyTasks.setText( task);

        ApiService client = YelpClient.getClient();

        Call<YelpEventResponse> call = client.getPublicEvents(task, "location");
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        call.enqueue(new Callback<YelpEventResponse>() {
            @Override
            public void onResponse(Call<YelpEventResponse> call, Response<YelpEventResponse> response) {
                if (response.isSuccessful()) {
                    List<Business> reviewList = response.body().getBusinesses();
                    String[] review = new String[reviewList.size()];
//                    String[] categories = new String[reviewList.size()];

                    for (int i = 0; i < review .length; i++){
                        review [i] = reviewList.get(i).getName();
                    }

//                    for (int i = 0; i < categories.length; i++) {
//                        Category category = reviewList.get(i).getCategories().get(0);
//                        categories[i] = category.getTitle();
//                    }

                    ArrayAdapter adapter
                            = new ArrayAdapter(Display.this, android.R.layout.simple_list_item_1, review);
                    mlistTask.setAdapter(adapter);
                    System.out.println(adapter);
                    showReview();


                }else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<YelpEventResponse> call, Throwable t) {

//                Log.e(TAG, "onFailure: ",t );
                hideProgressBar();
                showFailureMessage();

            }

        });

    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
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

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }
}
