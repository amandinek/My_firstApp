package com.example.myfirstapp.adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.myfirstapp.TodoListFragment;
import com.example.myfirstapp.models.Actor;

import java.util.List;

public class ToDoPageAdapter extends FragmentPagerAdapter {
    private List<Actor> mActors;

    public ToDoPageAdapter(FragmentManager fm, int behavior, List<Actor> actors) {
        super(fm, behavior);
        mActors = actors;
    }

    @Override
    public Fragment getItem(int position) {
        return TodoListFragment.newInstance(mActors.get(position));
    }

    @Override
    public int getCount() {
        return mActors.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mActors.get(position).getLogin();
    }
}
