package com.example.myfirstapp;

import android.content.Context;
import android.widget.ArrayAdapter;

public class ToDoAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] review;

    public ToDoAdapter(Context context, int resource,String[] review){
        super(context,resource);
        this.mContext=context;
        this.review= review;
    }

    @Override
    public Object getItem(int position) {
        String reviews = review[position];
        return String.format("%s", reviews);
    }

    @Override
    public int getCount() {
        return review.length;
    }



}
