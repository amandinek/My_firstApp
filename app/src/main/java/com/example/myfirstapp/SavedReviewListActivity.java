package com.example.myfirstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myfirstapp.models.Restaurants;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedReviewListActivity extends AppCompatActivity {

    private DatabaseReference mRestaurantReference;
    private FirebaseRecyclerAdapter<Restaurants, FirebaseToDoViewHolder> mFirebaseAdapter;

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_review_list);

        ButterKnife.bind(this);

        mRestaurantReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_RESTAURANTS);
        setUpFirebaseAdapter();

    }

    private void setUpFirebaseAdapter() {
        FirebaseRecyclerOptions<Restaurants> options =
                new FirebaseRecyclerOptions.Builder<Restaurants>()
                        .setQuery(mRestaurantReference, Restaurants.class)
                        .build();

        mFirebaseAdapter = new FirebaseRecyclerAdapter<Restaurants, FirebaseToDoViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FirebaseToDoViewHolder FirebaseToDoViewHolder, int position, @NonNull Restaurants restaurant) {
                FirebaseToDoViewHolder.bindRestaurant(restaurant);
            }

            @NonNull
            @Override
            public FirebaseToDoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_display, parent, false);
                return new FirebaseToDoViewHolder(view);
            }
        };
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mFirebaseAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }
}