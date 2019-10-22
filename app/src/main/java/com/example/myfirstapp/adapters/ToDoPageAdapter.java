package com.example.myfirstapp.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myfirstapp.TodoListFragment;
import com.example.myfirstapp.models.Business;

import java.util.List;

public class ToDoPageAdapter extends FragmentPagerAdapter {
    private List<Business> mreview;

    public ToDoPageAdapter(FragmentManager fm, int behavior, List<Business> review) {
        super(fm);
        mreview = review;
    }

    @Override
    public Fragment getItem(int position) {
        return TodoListFragment.newInstance(mreview.get(position));
    }

    @Override
    public int getCount() {
        return mreview.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mreview.get(position).getName();
    }
}
