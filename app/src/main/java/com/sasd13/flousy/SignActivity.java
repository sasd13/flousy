package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHeader;
import com.sasd13.androidex.gui.widget.recycler.form.Form;
import com.sasd13.androidex.gui.widget.recycler.form.FormItem;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.androidex.util.form.FormHelper;
import com.sasd13.androidex.util.form.FormHolder;
import com.sasd13.androidex.util.form.FormItemType;
import com.sasd13.androidex.util.form.field.FormField;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.dao.db.SQLitePasswordDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.LayeredPersistor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SignActivity extends AppCompatActivity {

    private static final int TIMEOUT = 2000;

    private class FormCustomer {
        private static final int ID_FIRSTNAME = 0;
        private static final int ID_LASTNAME = 1;
        private static final int ID_EMAIL = 2;
        private static final int ID_PASSWORD = 3;
        private static final int ID_TERMS = 4;

        private FormHolder holder;

        private FormCustomer() {
            holder = new FormHolder();

            FormField[] fieldsIdentity = {
                    new FormField<String>(ID_FIRSTNAME, FormItemType.TEXT, "First name"),
                    new FormField<String>(ID_LASTNAME, FormItemType.TEXT, "Last name")
            };

            holder.add("Identité", fieldsIdentity);

            FormField[] fieldsAccount = {
                    new FormField<String>(ID_EMAIL, FormItemType.TEXT, "Email"),
                    new FormField<String>(ID_PASSWORD, FormItemType.PASSWORD, "Password"),
                    new FormField<Boolean>(ID_TERMS, FormItemType.CHECKBOX, "Terms of use"),
            };

            holder.add("Compte", fieldsAccount);
        }
    }

    private Form form;
    private FormCustomer formCustomer;

    private SQLiteDAO dao = SQLiteDAO.getInstance();
    private LayeredPersistor persistor = new LayeredPersistor(dao);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign);
        GUIHelper.colorTitles(this);

        createFormCustomer();
        addFormItems();
    }

    private void createFormCustomer() {
        formCustomer = new FormCustomer();
        form = new Form((RecyclerView) findViewById(R.id.sign_recyclerview));
    }

    private void addFormItems() {
        List<FormItem> formItems = new ArrayList<>();

        for (Map.Entry<String, FormField[]> entry : formCustomer.holder.values()) {
            for (FormField formField : entry.getValue()) {
                formItems.add(FormHelper.buildField(formField, this));
            }

            form.addAll(new RecyclerHeader(entry.getKey()), formItems);

            formItems.clear();
        }
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
                signUp();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void signUp() {
        String[] tabFormErrors = validFormCustomer();

        if (true) {
            tryToPerformSignUp();
        } else {
            OptionDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
        }
    }

    private String[] validFormCustomer() {
        //TODO

        return null;
    }

    private void tryToPerformSignUp() {
        Customer customer = getCustomerFromForm();

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.EMAIL.getName(), new String[]{ customer.getEmail() });

        if (persistor.read(parameters, Customer.class).isEmpty()) {
            performSignUp(customer);

            SessionHelper.setExtraIdInSession(Extra.CUSTOMER_ID, customer.getId());
            goToHomeActivityWithWelcome(customer.getFirstName());
        } else {
            OptionDialog.showOkDialog(this, "Error sign", "Email (" + customer.getEmail() + ") already exists");
        }
    }

    private Customer getCustomerFromForm() {
        Customer customer = new Customer();

        //TODO

        return customer;
    }

    private void performSignUp(Customer customer) {
        try {
            dao.open();
            dao.beginTransaction();

            long id = dao.getEntityDAO(Customer.class).insert(customer);
            customer.setId(id);

            //String password = formCustomer.editTextPassword.getText().toString().trim();
            SQLitePasswordDAO passwordDAO = new SQLitePasswordDAO(dao.getDB());
            //passwordDAO.insert(password, customer.getId());

            dao.getEntityDAO(Account.class).insert(customer.getAccount());

            dao.commit();
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            dao.endTransaction();
            dao.close();
        }
    }

    private void goToHomeActivityWithWelcome(final String firstName) {
        final WaitDialog waitDialog = new WaitDialog(this);

        TaskPlanner taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SignActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra(Extra.WELCOME, true);
                intent.putExtra(Extra.FIRSTNAME, firstName);

                startActivity(intent);
                waitDialog.dismiss();
            }
        }, TIMEOUT);

        taskPlanner.start();
        waitDialog.show();
    }
}