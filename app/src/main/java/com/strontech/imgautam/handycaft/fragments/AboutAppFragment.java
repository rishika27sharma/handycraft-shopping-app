package com.strontech.imgautam.handycaft.fragments;


import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strontech.imgautam.handycaft.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutAppFragment extends Fragment implements View.OnClickListener {

    private Toolbar toolbarAboutFragment;
    private CircleImageView imageViewGithub;
    private CircleImageView imageViewTwitter;
    private CircleImageView imageViewGPlus;

    View view;

    public AboutAppFragment() {
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
        view = inflater.inflate(R.layout.fragment_about_app, container, false);

        initViews();
        initListeners();
        initObjects();
        return view;
    }


    /**
     * This method for initialization Views
     */
    private void initViews() {
        toolbarAboutFragment = view.findViewById(R.id.toolbarAboutFragment);
        imageViewGithub = view.findViewById(R.id.imageViewGithub);
        imageViewTwitter = view.findViewById(R.id.imageViewTwitter);
        imageViewGPlus = view.findViewById(R.id.imageViewGPlus);

    }


    /**
     * This method for initialization Listeners
     */
    private void initListeners() {

        imageViewGithub.setOnClickListener(this);
        imageViewTwitter.setOnClickListener(this);
        imageViewGPlus.setOnClickListener(this);
    }


    /**
     * This method for initialization Objects
     */
    private void initObjects() {
        setupToolbar();
    }


    /**
     * This method shows toolbar
     */
    private void setupToolbar() {
        toolbarAboutFragment.setTitle("About");
        toolbarAboutFragment.setTitleTextColor(Color.WHITE);
        toolbarAboutFragment.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbarAboutFragment.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, new HomeFragment());
                getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                ft.commit();
            }
        });
    }


    /**
     * This is override method to show toolbar of activity
     */
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity) getActivity()).getSupportActionBar().show();
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == imageViewGithub.getId()) {

            Intent i = getOpenGithubIntent(this, "im-gautam");
            startActivity(i);

        } else if (v.getId() == imageViewTwitter.getId()) {
            Intent i = getOpenTwitterIntent(this, "imstar_gautam");
            startActivity(i);

        } else if (v.getId() == imageViewGPlus.getId()) {

            PackageManager packageManager = getActivity().getPackageManager();
            Intent i = getOpenGPlusIntent(packageManager, "https://plus.google.com/+staronTech");
            startActivity(i);
        }
    }

    private Intent getOpenGPlusIntent(PackageManager pm, String imstar_gautam) {


        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(imstar_gautam));
        try {
            if (pm.getPackageInfo("com.google.android.apps.plus", 0) != null) {
                intent.setPackage("com.google.android.apps.plus");
            }
        } catch (PackageManager.NameNotFoundException e) {
        }
        return intent;
    }

    private Intent getOpenGithubIntent(AboutAppFragment aboutAppFragment, String imstar_gautam) {
        try {
            aboutAppFragment.getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/" + imstar_gautam));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/" + imstar_gautam));
        }
    }

    private Intent getOpenTwitterIntent(AboutAppFragment aboutAppFragment, String userName) {
        try {
            aboutAppFragment.getActivity().getPackageManager().getPackageInfo("com.twitter.android", 0);
            return new Intent(Intent.ACTION_VIEW, Uri.parse("twitter://user?screen_name=" + userName));
        } catch (Exception e) {
            return new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/" + userName));
        }
    }
}
