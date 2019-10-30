package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.myfirstapp.models.CheckList;

import com.example.myfirstapp.adapters.EventsListAdapter;
//import com.example.myfirstapp.adapters.ReviewsArrayAdapter;
import com.example.myfirstapp.models.Business;
//import com.example.myfirstapp.models.Category;
import com.example.myfirstapp.models.YelpEventResponse;
import com.example.myfirstapp.network.ApiService;
import com.example.myfirstapp.network.YelpClient;

import java.io.IOException;
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

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;



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
//        getRestaurants(task);

        ApiService client = YelpClient.getClient();

        Call<YelpEventResponse> call = client.getPublicEvents(task, "location");
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        call.enqueue(new Callback<YelpEventResponse>() {
            @Override
            public void onResponse(Call<YelpEventResponse> call, Response<YelpEventResponse> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    List<Business> reviewList = response.body().getBusinesses();
                    String[] review = new String[reviewList.size()];
                    String[] categories = new String[reviewList.size()];
                    for (int i = 0; i < review .length; i++){
                        review [i] = reviewList.get(i).getName();
                    }

//                     review= response.body().getBusinesses();
////                    ArrayAdapter adapter
//                    mAdapter = new EventsListAdapter(Display.this,review);
//                    mRecyclerView.setAdapter(mAdapter);

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

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);
        Log.d("Shared Pref Location", mRecentAddress);
        if (mRecentAddress != null) {
//            getRestaurants(mRecentAddress);
        }

    }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private void addToSharedPreferences(String location) {
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
//                getRestaurants(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }

        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
//
//    private void getRestaurants(String location) {
//        final ApiService apiService = new ApiService();
//
//        apiService.findRestaurant(location, new Callback() {
//
//            @Override
//            public void onFailure(Call call, IOException e) {
//                e.printStackTrace();
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) {
//                mRestaurants =  ApiService.processResults(response);
//
//               Display.this.runOnUiThread(new Runnable() {
//
//                    @Override
//                    public void run() {
//                        mAdapter = new EventsListAdapter(getApplicationContext(), mAdapter);
//                        mRecyclerView.setAdapter(mAdapter);
//                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(Display.this);
//                        mRecyclerView.setLayoutManager(layoutManager);
//                        mRecyclerView.setHasFixedSize(true);
//                    }
//                });
//            }
//        });
//    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

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
