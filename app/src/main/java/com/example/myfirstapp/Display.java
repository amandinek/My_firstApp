package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
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
import com.example.myfirstapp.models.Actor;
import com.example.myfirstapp.models.GitSearchAppResponse;
import com.example.myfirstapp.models.Repo;
import com.example.myfirstapp.network.ApiService;
import com.example.myfirstapp.network.GitClient;

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

//
//     Retrofit retrofit = new Retrofit.Builder()
//            .baseUrl("https://developer.github.com/v3")
//             .addConverterFactory((GsonConverterFactory.create()))
//            .build();

    private ListView mlistTask;
    @BindView(R.id.myTime) TextView mMyTime;
    @BindView(R.id.myTask) TextView mMyTasks;

    @BindView(R.id.recyclerView)RecyclerView mRecyclerView;
    private EventsListAdapter mAdapter;
    @BindView(R.id.errorTextView) TextView mErrorTextView;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showPublicEvents() {
        mlistTask.setVisibility(View.VISIBLE);
//        mLocationTextView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }



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

        ApiService client = GitClient.getClient();

        Call<GitSearchAppResponse> call = client.getPublicEvents(task, "list-public-events");
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        call.enqueue(new Callback<GitSearchAppResponse>() {
            @Override
            public void onResponse(Call<GitSearchAppResponse> call, Response<GitSearchAppResponse> response) {
                if (response.isSuccessful()) {
                    List<Repo> actorsList = (List<Repo>) response.body().getActor();
                    String[] actor = new String[actorsList.size()];
//                    String[] repo = new String[actorsList.size()];

                    for (int i = 0; i < actor.length; i++){
                        actor[i] = actorsList.get(i).getName();
                    }

//                    for (int i = 0; i < repo.length; i++) {
//                        Repo category = actorsList.get(i).getLogin().get(0);
//                        repo[i] = category.getName();
//                    }

                    ArrayAdapter adapter
                            = new ToDoAdapter(Display.this, android.R.layout.simple_list_item_1, actor);
                    mlistTask.setAdapter(adapter);

                    showPublicEvents();

                }
                else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<GitSearchAppResponse> call, Throwable t) {

//                Log.e(TAG, "onFailure: ",t );
                hideProgressBar();
                showFailureMessage();

            }

        });


    }
}
