package com.sasd13.flousy.controller.sign;

import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.View;

import com.sasd13.flousy.activities.IdentityActivity;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.view.ISignController;
import com.sasd13.flousy.view.fragment.sign.SignUpFragment;

/**
 * Created by ssaidali2 on 27/12/2016.
 */

public class SignController implements ISignController {

    private IdentityActivity identityActivity;
    private View contentView;
    private LogInHandler logInHandler;

    public SignController(IdentityActivity identityActivity) {
        this.identityActivity = identityActivity;
        contentView = identityActivity.findViewById(android.R.id.content);
    }

    @Override
    public void entry() {

    }

    @Override
    public void displayMessage(String message) {
        Snackbar.make(contentView, message, Snackbar.LENGTH_SHORT).show();
    }

    private void startFragment(Fragment fragment) {
        identityActivity.startFragment(fragment);
    }

    @Override
    public void signIn(String username, String password) {

    }

    @Override
    public void showSignUp() {
        startFragment(SignUpFragment.newInstance());
    }

    @Override
    public void sign(Customer customer) {

    }
}
