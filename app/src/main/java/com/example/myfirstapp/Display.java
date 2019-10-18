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

//import com.example.myfirstapp.models.CheckList;

import java.text.DateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;

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
//
//        TrelloApi client = TrelloClient.getClient();
//
//        Call<CheckList> call = client.getChecklists(task, "list");
        /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

//        call.enqueue(new Callback<CheckList>() {
//            @Override
//            public void onResponse(Call<CheckList> call, Response<CheckList> response) {
//                if (response.isSuccessful()) {
//                    List<Business> restaurantsList = response.body().getBusinesses();
//                    String[] restaurants = new String[restaurantsList.size()];
//                    String[] categories = new String[restaurantsList.size()];
//
//                    for (int i = 0; i < restaurants.length; i++){
//                        restaurants[i] = restaurantsList.get(i).getName();
//                    }
//
//                    for (int i = 0; i < categories.length; i++) {
//                        Category category = restaurantsList.get(i).getCategories().get(0);
//                        categories[i] = category.getTitle();
//                    }
//
//                    ArrayAdapter adapter
//                            = new MyRestaurantsArrayAdapter(RestaurantsActivity.this, android.R.layout.simple_list_item_1, restaurants, categories);
//                    mListView.setAdapter(adapter);
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<CheckList> call, Throwable t) {
//
//            }
//
//        });



    }
}
