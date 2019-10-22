package com.example.myfirstapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myfirstapp.models.Business;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoListFragment extends Fragment {
    @BindView(R.id.reviewImageView)ImageView mImageLabel;
    @BindView(R.id.reviewNameTextView)TextView mNameLabel;
    @BindView(R.id.ratingTextView) TextView mRatingLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;
    @BindView(R.id.saveRestaurantButton) TextView mSaveRestaurantButton;

    private Business mReview;


    public TodoListFragment() {
        // Required empty public constructor
    }
    public static TodoListFragment newInstance(Business review) {
        TodoListFragment todoListFragment = new TodoListFragment();
        Bundle args = new Bundle();
        args.putParcelable("location", Parcels.wrap(review));
        todoListFragment.setArguments(args);
        return todoListFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReview = Parcels.unwrap(getArguments().getParcelable("location"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo_list, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mReview.getImageUrl()).into(mImageLabel);


        mNameLabel.setText(mReview.getName());
        mRatingLabel.setText(Double.toString(mReview.getRating()) + "/5");
        mPhoneLabel.setText(mReview.getPhone());
        mAddressLabel.setText(mReview.getLocation().toString());

        return view;
    }
}
