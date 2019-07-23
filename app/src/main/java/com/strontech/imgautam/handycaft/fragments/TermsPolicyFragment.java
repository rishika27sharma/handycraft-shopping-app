package com.strontech.imgautam.handycaft.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import com.strontech.imgautam.handycaft.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TermsPolicyFragment extends Fragment {


    private View view;
    private Toolbar toolbarTermsPolicy;

    public TermsPolicyFragment() {
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
        view = inflater.inflate(R.layout.fragment_terms_policy, container, false);

        initViews();
        initListeners();
        initObjects();

        return view;

    }


    /**
     * This method for initialization Views
     */
    private void initViews() {

        toolbarTermsPolicy = view.findViewById(R.id.toolbarTermsPolicy);

    }

    /**
     * This method for initialization Listeners
     */
    private void initListeners() {

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
        toolbarTermsPolicy.setTitle("Terms and Policy");
        toolbarTermsPolicy.setTitleTextColor(Color.WHITE);
        toolbarTermsPolicy.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbarTermsPolicy.setNavigationOnClickListener(new OnClickListener() {
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
}
