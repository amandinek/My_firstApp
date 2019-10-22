package com.example.myfirstapp.adapters;

import android.content.Context;
import android.widget.ArrayAdapter;

public class ReviewsArrayAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] review;

    public ReviewsArrayAdapter(Context context, int resource,String[] review){
        super(context,resource);
        this.mContext=context;
        this.review= review;
    }

    @Override
    public Object getItem(int position) {
        String reviews = review[position];
        return String.format(reviews);
    }

    @Override
    public int getCount() {
        return review.length;
    }

}
