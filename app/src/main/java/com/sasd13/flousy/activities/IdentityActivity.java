package com.sasd13.flousy.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.sasd13.flousy.R;
import com.sasd13.flousy.controller.SignController;
import com.sasd13.flousy.view.fragment.IController;
import com.sasd13.flousy.view.fragment.ISignController;
import com.sasd13.flousy.view.fragment.sign.SignInFragment;

public class IdentityActivity extends AppCompatActivity {

    private ISignController signController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        setContentView(R.layout.layout_container);
        showSignIn();
    }

    private void init() {
        signController = new SignController(this);
    }

    private void showSignIn() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, SignInFragment.newInstance())
                .commit();
    }

    public void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public IController lookup(Class<? extends IController> mClass) {
        if (ISignController.class.isAssignableFrom(mClass)) {
            return signController;
        } else {
            return null;
        }
    }
}