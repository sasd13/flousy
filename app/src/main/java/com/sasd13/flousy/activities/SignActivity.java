package com.sasd13.flousy.activities;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.form.SignForm;
import com.sasd13.flousy.handler.SignHandler;
import com.sasd13.flousy.util.SessionHelper;

public class SignActivity extends AppCompatActivity {

    private View contentView;
    private SignHandler signHandler;
    private SignForm formSign;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign);
        GUIHelper.colorTitles(this);

        contentView = findViewById(android.R.id.content);
        signHandler = new SignHandler(this);
        formSign = new SignForm(this);

        buildView();
    }

    private void buildView() {
        getSupportActionBar().setSubtitle(R.string.title_fill_form);

        buildFormSign();
    }

    private void buildFormSign() {
        Recycler form = RecyclerFactory
                .makeBuilder(EnumFormType.FORM)
                .build((RecyclerView) findViewById(R.id.sign_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, formSign.getHolder());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sign, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sign_action_done:
                sign();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void sign() {
        signHandler.sign(formSign);
    }

    public void onSignSucceeded(Customer customer) {
        SessionHelper.logIn(this, customer);
    }

    public void onError(@StringRes int error) {
        Snackbar.make(contentView, error, Snackbar.LENGTH_SHORT).show();
    }
}