package com.sasd13.flousy.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.SessionStorage;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.Configuration;
import com.sasd13.flousy.R;
import com.sasd13.flousy.Router;
import com.sasd13.flousy.bean.user.User;
import com.sasd13.flousy.util.Constants;
import com.sasd13.flousy.view.IController;
import com.sasd13.flousy.view.fragment.authentication.LogInFragment;

public class IdentityActivity extends AppCompatActivity {

    private Router router;
    private SessionStorage sessionStorage;

    public SessionStorage getSessionStorage() {
        return sessionStorage;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.layout_container);

        init();
    }

    private void init() {
        router = Configuration.init(this);
        sessionStorage = new SessionStorage(this);

        startLogInFragment();
    }

    private void startLogInFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, LogInFragment.newInstance())
                .commit();
    }

    public IController lookup(Class mClass) {
        return router.dispatch(mClass, this);
    }

    public void startFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layout_container_fragment, fragment)
                .addToBackStack(null)
                .commit();
    }

    public void goToMainActivity(final User user) {
        final WaitDialog waitDialog = new WaitDialog(this);
        final Intent intent = new Intent(this, MainActivity.class);

        new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                getSessionStorage().put(Constants.USERID, user.getUserID());
                getSessionStorage().put(Constants.INTERMEDIARY, user.getIntermediary());
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                intent.putExtra(Constants.USER, user);
                startActivity(intent);
                waitDialog.dismiss();
                finish();
            }
        }).start(0);

        waitDialog.show();
    }
}