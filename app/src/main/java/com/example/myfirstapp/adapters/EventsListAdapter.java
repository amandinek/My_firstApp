package com.example.myfirstapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.myfirstapp.R;
import com.example.myfirstapp.models.Business;
import com.squareup.picasso.Picasso;
//import com.example.myfirstapp.TodoListDetails;


import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventsListAdapter extends RecyclerView.Adapter<EventsListAdapter.DisplayViewHolder> {

        private List<Business> mReviews;
        private Context mContext;

        public EventsListAdapter(Context context, List<Business> reviews) {
            mContext = context;
            mReviews = reviews;
        }

    @Override
    public EventsListAdapter.DisplayViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_display, parent, false);
        DisplayViewHolder viewHolder = new DisplayViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(EventsListAdapter.DisplayViewHolder holder, int position) {
        holder.bindReview(mReviews.get(position));
    }

    @Override
    public int getItemCount() {
        return mReviews.size();
    }

    public class DisplayViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.reviewImageView) ImageView mReviewImageView;
        @BindView(R.id.reviewTextView) TextView mReviewTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;



        private Context mContext;

        public DisplayViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            int itemPosition = getLayoutPosition();
//            Intent intent = new Intent(mContext, TodoListDetails.class);
//            intent.putExtra("position", itemPosition);
//            intent.putExtra("restaurants", Parcels.wrap(mActors));
//            mContext.startActivity(intent);
//        }

        public void bindReview(Business review) {
            mReviewTextView.setText(review.getName());
            mCategoryTextView.setText(review.getCategories().get(0).getTitle());
            mRatingTextView.setText("Rating: " + review.getRating() + "/5");
            Picasso.get().load(review.getImageUrl()).into(mReviewImageView);
        }

        @Override
        public void onClick(View v) {

        }
    }

}
