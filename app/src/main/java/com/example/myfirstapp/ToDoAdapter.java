package com.example.myfirstapp;

import android.content.Context;
import android.widget.ArrayAdapter;

public class ToDoAdapter extends ArrayAdapter {
    private Context mContext;
    private String[] mToDo;

    public ToDoAdapter(Context context, int resource,String[] toDo){
        super(context,resource);
        this.mContext=context;
        this.mToDo= toDo;
    }

    @Override
    public Object getItem(int position) {
        String toDo = mToDo[position];
        return String.format("%s", toDo);
    }

    @Override
    public int getCount() {
        return mToDo.length;
    }



}
