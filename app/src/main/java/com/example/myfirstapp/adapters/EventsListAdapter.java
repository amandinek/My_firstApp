package com.example.myfirstapp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.TodoListDetails;
import com.example.myfirstapp.models.Actor;


import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.RestaurantViewHolder> {

        private List<Actor> mActors;
        private Context mContext;

        public EventsListAdapter(Context context, List<Actor> actors) {
            mContext = context;
            mActors = actors;
        }

    @Override
    public EventsListAdapter.RestaurantViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_display, parent, false);
        RestaurantViewHolder viewHolder = new RestaurantViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventsListAdapter.RestaurantViewHolder holder, int position) {
        holder.bindRestaurant(mActors.get(position));
    }

    @Override
    public int getItemCount() {
        return mActors.size();
    }

    public class RestaurantViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.eventImageView)ImageView meventImageView;
        @BindView(R.id.eventName)TextView mNameTextView;


        private Context mContext;

        public RestaurantViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener((View.OnClickListener) this);
        }

//        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, TodoListDetails.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("restaurants", Parcels.wrap(mActors));
            mContext.startActivity(intent);
        }

        public void bindRestaurant(Actor actors) {
            mNameTextView.setText(actors.getLogin());
//            Picasso.get().load(actors.getAvatarUrl()).into(meventImageView);

        }
    }

}
