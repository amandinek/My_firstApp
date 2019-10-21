//package com.example.myfirstapp;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.fragment.app.FragmentPagerAdapter;
//import androidx.viewpager.widget.ViewPager;
//
//import android.os.Bundle;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import com.example.myfirstapp.adapters.ToDoPageAdapter;
//import com.example.myfirstapp.models.Actor;
//
//import org.parceler.Parcels;
//
//import java.util.List;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//
//public class TodoListDetails extends AppCompatActivity {
//
//    @BindView(R.id.viewPager)
//    ViewPager mViewPager;
//    private ToDoPageAdapter adapterViewPager;
//    List<Actor> mActors;
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_todo_list_details);
//
//        ButterKnife.bind(this);
//
//        mActors = Parcels.unwrap(getIntent().getParcelableExtra("restaurants"));
//        int startingPosition = getIntent().getIntExtra("position", 0);
//
//        adapterViewPager = new ToDoPageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.POSITION_NONE, mActors);
//        mViewPager.setAdapter(adapterViewPager);
//        mViewPager.setCurrentItem(startingPosition);
//    }
//}
