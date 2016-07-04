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
import com.sasd13.flousy.content.form.SignForm;
import com.sasd13.flousy.content.handler.SignHandler;
import com.sasd13.flousy.util.SessionHelper;

public class SignActivity extends AppCompatActivity {

    private SignForm signForm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign);
        GUIHelper.colorTitles(this);
        buildSignView();
    }

    private void buildSignView() {
        signForm = new SignForm(this);
        FormFactory formFactory = new FormFactory(this);
        Form form = (Form) formFactory.makeBuilder().build((RecyclerView) findViewById(R.id.sign_recyclerview));

        RecyclerHelper.fill(form, signForm.fabricate(), formFactory);
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
        SignHandler.sign(this, signForm);
    }

    public void editCustomerWithForm(Customer customer) {
        customer.setFirstName(signForm.getFirstName());
        customer.setLastName(signForm.getLastName());
        customer.setEmail(signForm.getEmail());
    }

    public void onSuccess(Customer customer) {
        SessionHelper.logIn(this, customer);
    }

    public void onError(String error) {
        OptionDialog.showOkDialog(this, getResources().getString(R.string.title_error), error);
    }
}