package com.strontech.imgautam.handycaft.fragments;


import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.strontech.imgautam.handycaft.R;
import com.strontech.imgautam.handycaft.helper.InputValidation;

/**
 * A simple {@link Fragment} subclass.
 */
public class HelpFeedbackFragment extends Fragment implements OnClickListener{

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPhone;
    private TextInputLayout textInputLayoutOrderId;
    private TextInputLayout textInputLayoutMessage;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPhone;
    private TextInputEditText textInputEditTextOrderId;
    private TextInputEditText textInputEditTextMessage;


    private Spinner spinnerHelpFeedback;
    private Button buttonSubmit;

    private View view;
    private Toolbar toolbarHelpFeedback;

    private InputValidation inputValidation;
    public HelpFeedbackFragment() {
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
        view = inflater.inflate(R.layout.fragment_help_feedback, container, false);

        initViews();
        initListeners();
        initObjects();
        return view;
    }


    /**
     * This method for initialization Views
     */
    private void initViews() {
        toolbarHelpFeedback = view.findViewById(R.id.toolbarHelpFeedback);
        textInputLayoutEmail=view.findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPhone=view.findViewById(R.id.textInputLayoutPhone);
        textInputLayoutOrderId=view.findViewById(R.id.textInputLayoutOrderId);
        textInputLayoutMessage=view.findViewById(R.id.textInputLayoutMessage);

        textInputEditTextEmail=view.findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPhone=view.findViewById(R.id.textInputEditTextPhone);
        textInputEditTextOrderId=view.findViewById(R.id.textInputEditTextOrderId);
        textInputEditTextMessage=view.findViewById(R.id.textInputEditTextMessage);

        spinnerHelpFeedback=view.findViewById(R.id.spinnerHelpFeedback);
        buttonSubmit=view.findViewById(R.id.buttonSubmit);
    }


    /**
     * This method for initialization Listeners
     */
    private void initListeners() {
        buttonSubmit.setOnClickListener(this);

    }


    /**
     * This method for initialization Objects
     */
    private void initObjects() {
        setupToolbar();
        inputValidation=new InputValidation(getActivity());

    }

    /**
     * This method generate Toast
     * */
    private void sendFeedbackMessage() {
        if (!inputValidation.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail,
                "Enter validEmail")) {
            return;
        }
        if (!inputValidation.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail,
                "Email valid email")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextPhone, textInputLayoutPhone,
                "Enter Phone number")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextOrderId, textInputLayoutOrderId,
                "Enter Order Id")) {
            return;
        }
        if (!inputValidation.isInputEditTextFilled(textInputEditTextMessage, textInputLayoutMessage,
                "Email Message")) {
            return;
        }

        Toast.makeText(getActivity(), "Thanks for your feedback.\n We will inform you soon.", Toast.LENGTH_SHORT).show();
    }


    /**
     * This method shows toolbar
     */
    private void setupToolbar() {
        toolbarHelpFeedback.setTitle("Help and Feedback");
        toolbarHelpFeedback.setTitleTextColor(Color.WHITE);
        toolbarHelpFeedback.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbarHelpFeedback.setNavigationOnClickListener(new OnClickListener() {
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

        if (v.getId()==buttonSubmit.getId()){
            sendFeedbackMessage();
        }
    }
}
