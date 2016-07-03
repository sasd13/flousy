package com.sasd13.flousy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.form.Form;
import com.sasd13.androidex.gui.widget.recycler.form.FormFactory;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.content.handler.SignHandler;
import com.sasd13.flousy.util.SessionHelper;

public class SignActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign);
        GUIHelper.colorTitles(this);
        SignHandler.init(this);
        buildSignView();
    }

    private void buildSignView() {
        FormFactory formFactory = new FormFactory(this);
        Form form = (Form) formFactory.makeBuilder().build((RecyclerView) findViewById(R.id.sign_recyclerview));

        RecyclerHelper.fill(form, SignHandler.getSignForm().fabricate(), formFactory);
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
        String[] errors = SignHandler.validFormInputs();

        if (errors.length == 0) {
            Customer customer = SignHandler.createCustomer();

            if (customer != null) {
                SessionHelper.logIn(this, customer);
            } else {
                OptionDialog.showOkDialog(
                        this,
                        getResources().getString(R.string.title_error),
                        getResources().getString(R.string.message_email_exists));
            }
        } else {
            OptionDialog.showOkDialog(this, getResources().getString(R.string.title_error), errors[0]);
        }
    }
}