package com.strontech.imgautam.handycaft.userfragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.strontech.imgautam.handycaft.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserOrdersFragment extends Fragment {


    private Toolbar toolbarOrderFragment;

    View view;

    public UserOrdersFragment() {
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
        view = inflater.inflate(R.layout.fragment_user_orders, container, false);
        initViews();
        initListeners();
        initObjects();

        return view;
    }

    /**
     * This method is for initialization views
     */
    private void initViews() {
        toolbarOrderFragment = view.findViewById(R.id.toolbarOrderFragment);

    }


    /**
     * This method is for initialization Listeners
     */
    private void initListeners() {

    }


    /**
     * This method is for initialization Objects
     */
    private void initObjects() {
        setUpToolbar();
    }

    /**
     * This method is to setup Toolbar
     */
    private void setUpToolbar() {
        toolbarOrderFragment.setTitle("Orders");
        toolbarOrderFragment.setTitleTextColor(Color.WHITE);
        toolbarOrderFragment.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbarOrderFragment.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
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
}
