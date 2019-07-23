package com.strontech.imgautam.handycaft.helper;

import android.app.Activity;
import android.content.Context;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;

public class InputValidation {

    Context context;


    /**
     * Constructor
     */
    public InputValidation(Context context) {
        this.context = context;
    }


    /**
     * method to check InputText filled
     *
     * @param textInputEditText for editText
     * @param textInputLayout   to print message on it
     * @param message           to notify user
     */
    public boolean isInputEditTextFilled(TextInputEditText textInputEditText,
                                         TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }


    /**
     * method to check InputEditTExt has valid email
     *
     * @param textInputEditText fot input text
     * @param textInputLayout   for give message on it
     * @param message           to give message
     */
    public boolean isInputEditTextEmail(TextInputEditText textInputEditText,
                                        TextInputLayout textInputLayout, String message) {
        String value = textInputEditText.getText().toString().trim();
        if (value.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(value).matches()) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * This method matches both editText values are same
     *
     * @param textInputEditText1 first editText
     * @param textInputEditText2 second editText
     * @param textInputLayout    layout to print message
     * @param message            give message to notify
     */
    public boolean isInputEditTextMatches(TextInputEditText textInputEditText1,
                                          TextInputEditText textInputEditText2, TextInputLayout textInputLayout, String message) {
        String value1 = textInputEditText1.getText().toString().trim();
        String value2 = textInputEditText2.getText().toString().trim();
        if (!value1.contentEquals(value2)) {
            textInputLayout.setError(message);
            hideKeyboardFrom(textInputEditText2);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }


    /**
     * method to hide keyboard
     *
     * @param view
     */
    public void hideKeyboardFrom(View view) {
        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Activity.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(),
                    LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }
}
