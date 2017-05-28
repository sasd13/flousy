package com.sasd13.flousy.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.sasd13.flousy.Configurator;
import com.sasd13.flousy.R;
import com.sasd13.flousy.Router;
import com.sasd13.flousy.view.IController;
import com.sasd13.flousy.view.fragment.authentication.LogInFragment;

public class IdentityActivity extends AppCompatActivity {

    private Router router;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_container);

        init();
        startLogInFragment();
    }

    private void init() {
        Configurator.Config config = Configurator.init(this);
        router = config.getRouter();
    }

    private void startLogInFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, LogInFragment.newInstance())
                .commit();
    }

    public IController lookup(Class mClass) {
        return router.navigate(mClass, this);
    }

    public void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }
}