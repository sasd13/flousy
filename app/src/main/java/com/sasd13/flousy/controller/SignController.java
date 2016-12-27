package com.sasd13.flousy.controller;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.sasd13.flousy.activities.SignActivity;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.fragment.ISignController;
import com.sasd13.flousy.fragment.sign.SignUpFragment;
import com.sasd13.flousy.handler.LogInHandler;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public class SignController implements ISignController {

    private SignActivity signActivity;
    private View contentView;
    private LogInHandler logInHandler;

    public SignController(SignActivity signActivity) {
        this.signActivity = signActivity;
        contentView = signActivity.findViewById(android.R.id.content);
    }

    @Override
    public void entry() {

    }

    @Override
    public void displayMessage(String message) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }

    private void startFragment(Fragment fragment) {
        signActivity.startFragment(fragment);
    }

    @Override
    public void signIn(String username, String password) {

    }

    @Override
    public void showSignUp() {
        startFragment(SignUpFragment.newInstance());
    }

    @Override
    public void signUp(Customer customer) {

    }
}
