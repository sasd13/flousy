package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.dao.db.SQLitePasswordDAO;
import com.sasd13.flousy.gui.widget.recycler.form.Form;
import com.sasd13.flousy.gui.widget.recycler.form.FormItem;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.DAOException;
import com.sasd13.javaex.db.LayeredPersistor;

import java.util.HashMap;
import java.util.Map;

public class SignActivity extends AppCompatActivity {

    private class FormCustomerViewHolder {
        public Form formIdentity;
        public CheckBox checkBoxValidTerms;
    }

    private static final int TIMEOUT = 2000;

    private FormCustomerViewHolder formCustomer;

    private SQLiteDAO dao = SQLiteDAO.getInstance();
    private LayeredPersistor persistor = new LayeredPersistor(dao);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign);
        createFormCustomer();
    }

    private void createFormCustomer() {
        formCustomer = new FormCustomerViewHolder();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.sign_form_recyclerview_identity);
        formCustomer.formIdentity = new Form(recyclerView, R.layout.formitem);

        formCustomer.checkBoxValidTerms = (CheckBox) findViewById(R.id.sign_form_user_checkbox_terms);
    }

    private void addFormItems() {
        FormItem formItem;

        for (int i = 0; i<4; i++) {
            formItem = new FormItem();

            switch (i) {
                case 0:
                    formItem.getInput().setName("Prénom");
                    formItem.getInput().setHint("Prénom");
                    break;
                case 1:
                    formItem.getInput().setName("Nom");
                    formItem.getInput().setHint("Nom");
                    break;
                case 2:
                    formItem.getInput().setName("Email");
                    formItem.getInput().setHint("Email");
                    break;
                case 3:
                    break;
            }

            formCustomer.formIdentity.addItem(formItem);
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
            case R.id.menu_sign_action_accept:
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
            CustomDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
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
            CustomDialog.showOkDialog(this, "Error sign", "Email (" + customer.getEmail() + ") already exists");
        }
    }

    private Customer getCustomerFromForm() {
        Customer customer = new Customer();

        customer.setFirstName(formCustomer.editTextFirstName.getText().toString().trim());
        customer.setLastName(formCustomer.editTextLastName.getText().toString().trim());
        customer.setEmail(formCustomer.editTextEmail.getText().toString().trim());

        return customer;
    }

    private void performSignUp(Customer customer) {
        try {
            dao.open();
            dao.beginTransaction();

            long id = dao.getEntityDAO(Customer.class).insert(customer);
            customer.setId(id);

            String password = formCustomer.editTextPassword.getText().toString().trim();
            SQLitePasswordDAO passwordDAO = new SQLitePasswordDAO(dao.getDB());
            passwordDAO.insert(password, customer.getId());

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