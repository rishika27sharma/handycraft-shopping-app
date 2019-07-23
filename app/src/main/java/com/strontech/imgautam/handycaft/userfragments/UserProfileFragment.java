package com.strontech.imgautam.handycaft.userfragments;


import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.strontech.imgautam.handycaft.R;
import com.strontech.imgautam.handycaft.activities.MainActivity;

import java.io.InputStream;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserProfileFragment extends Fragment {

    private Toolbar toolbarProfileFragment;
    private ImageView circleImageViewUserProfilePic;
    private TextView textViewUsername;
    private TextView textViewUserEmail;
    private TextView textViewUserMobNumber;
    private TextView textViewUserAddress;

    DatabaseReference databaseReference;

    View view;

    SharedPreferences sharedPreferences;
    String name;
    String email;
    //For Google
    private String username_google;
    private String email_google;
    private String profile_pic_google;


    //Facebook
    private String first_name;
    private String last_name;
    private String fb_email;
    private String imageUrl;


    public UserProfileFragment() {
        // Required empty public constructor
    }

    /**
     * This is override method to hide activity toolbar on onResume method
     */
    @Override
    public void onResume() {
        super.onResume();
        if (((AppCompatActivity) getActivity()).getSupportActionBar() != null) {
            ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_user_profile, container, false);

        initViews();
        initListeners();
        initObjects();

        return view;
    }


    /**
     * This method is to initialization Views
     */
    private void initViews() {
        toolbarProfileFragment = view.findViewById(R.id.toolbarProfileFragment);

        circleImageViewUserProfilePic = view.findViewById(R.id.circleImageViewUserProfilePic);
        textViewUsername = view.findViewById(R.id.textViewUsername);
        textViewUserEmail = view.findViewById(R.id.textViewUserEmail);
        textViewUserMobNumber = view.findViewById(R.id.textViewUserMobNumber);
        textViewUserAddress = view.findViewById(R.id.textViewUserAddress);
    }


    /**
     * This method is to initialization Listeners
     */
    private void initListeners() {

    }


    /**
     * This method is to initialization Objects
     */
    private void initObjects() {
        setUpToolbar();

        //SharedPreferences
        sharedPreferences = getActivity().getSharedPreferences("myEmailPass", Context.MODE_PRIVATE);

        getDataFromSharedPfSet();
    }


    /**
     * This method is to setup Toolbar
     */
    private void setUpToolbar() {
        toolbarProfileFragment.setTitle("Your Profile");
        toolbarProfileFragment.setTitleTextColor(Color.WHITE);
        toolbarProfileFragment.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbarProfileFragment.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });
    }

    /**
     * This method to get Data from SharedPreferences and set
     */
    private void getDataFromSharedPfSet() {
//get data from sharedPreferences and set it
        email = sharedPreferences.getString("email", null);

        //Google
        username_google = sharedPreferences.getString("username_google", null);
        email_google = sharedPreferences.getString("email_google", null);
        profile_pic_google = sharedPreferences.getString("profile_pic_google", null);

        //Facebook
        first_name = sharedPreferences.getString("facebook_first_name", null);
        last_name = sharedPreferences.getString("facebook_last_name", null);
        fb_email = sharedPreferences.getString("facebook_email", null);
        imageUrl = sharedPreferences.getString("facebook_image_url", null);


        if (email != null) {
            textViewUsername.setText(username_google);
            textViewUserEmail.setText(email);
            circleImageViewUserProfilePic.setImageResource(R.drawable.icon_user);
        } else if (first_name != null || last_name != null || fb_email != null || imageUrl != null) {
            textViewUserEmail.setText(fb_email);
            textViewUsername.setText(first_name + " " + last_name);
            new UserProfileFragment.DownloadImage(circleImageViewUserProfilePic).execute(imageUrl);

        } else if (username_google != null || email_google != null || profile_pic_google != null) {

            //Google
            textViewUsername.setText(username_google);
            textViewUserEmail.setText(email_google);
            Glide.with(getActivity()).load(profile_pic_google).into(circleImageViewUserProfilePic);
        } else {

        }

    }


    /**
     * This is class to download facebook Image
     */
    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {


        public DownloadImage(ImageView bmImage) {
            circleImageViewUserProfilePic = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            circleImageViewUserProfilePic.setImageBitmap(result);
        }
    }


    /**
     * This is override method to show toolbar of activity
     */
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();

    }

}
