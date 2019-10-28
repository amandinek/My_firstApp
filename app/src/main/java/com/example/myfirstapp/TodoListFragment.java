package com.example.myfirstapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myfirstapp.models.Business;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TodoListFragment extends Fragment implements View.OnClickListener {
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
        mWebsiteLabel.setOnClickListener(this);
        mPhoneLabel.setOnClickListener(this);
        mAddressLabel.setOnClickListener(this);

        mSaveRestaurantButton.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mWebsiteLabel) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(mReview.getUrl()));
            startActivity(webIntent);
        }
        if (v == mPhoneLabel) {
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,
                    Uri.parse("tel:" + mReview.getPhone()));
            startActivity(phoneIntent);
        }
        if (v == mAddressLabel) {
            Intent mapIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("geo:" + mReview.getCoordinates().getLatitude()
                            + "," + mReview.getCoordinates().getLongitude()
                            + "?q=(" + mReview.getName() + ")"));
            startActivity(mapIntent);
        }

        if (v == mSaveRestaurantButton) {
            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RESTAURANTS);
            restaurantRef.push().setValue(mReview);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
    }

}
