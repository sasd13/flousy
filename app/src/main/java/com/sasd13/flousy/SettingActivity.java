package com.sasd13.flousy;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.session.Session;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.db.CustomerDAO;
import com.sasd13.flousy.db.sqlite.SQLiteDAO;
import com.sasd13.javaex.db.IDAO;
import com.sasd13.javaex.db.IEntityDAO;

public class SettingActivity extends MotherActivity {

    private class FormCustomerViewHolder {
        public EditText editTextFirstName, editTextLastName, editTextEmail;
    }

    private FormCustomerViewHolder formCustomer;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_setting);

        createFormCustomer();
    }

    private void createFormCustomer() {
        this.formCustomer = new FormCustomerViewHolder();

        this.formCustomer.editTextFirstName = (EditText) findViewById(R.id.setting_form_user_edittext_firstname);
        this.formCustomer.editTextLastName = (EditText) findViewById(R.id.setting_form_user_edittext_lastname);
        this.formCustomer.editTextEmail = (EditText) findViewById(R.id.setting_form_user_edittext_email);
    }

    @Override
    protected void onStart() {
        super.onStart();

        IDAO dao = SQLiteDAO.getInstance();

        try {
            dao.open();

            Customer customer = (Customer) dao.getEntityDAO(Customer.class).select(Session.getId());

            fillFormCustomer(customer);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }

    private void fillFormCustomer(Customer customer) {
        this.formCustomer.editTextFirstName.setText(customer.getFirstName(), TextView.BufferType.EDITABLE);
        this.formCustomer.editTextLastName.setText(customer.getLastName(), TextView.BufferType.EDITABLE);
        this.formCustomer.editTextEmail.setText(customer.getEmail(), TextView.BufferType.EDITABLE);
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
                updateCustomer();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void updateCustomer() {
        String[] tabFormErrors = validFormCustomer();

        if (true) {
            tryToPerformUpdateCustomer();
        } else {
            CustomDialog.showOkDialog(this, "Error form", tabFormErrors[0]);
        }
    }

    private String[] validFormCustomer() {
        //TODO

        return null;
    }

    private void tryToPerformUpdateCustomer() {
        String email = this.formCustomer.editTextEmail.getText().toString().trim();

        IDAO dao = SQLiteDAO.getInstance();

        try {
            dao.open();

            CustomerDAO customerDAO = (CustomerDAO) dao.getEntityDAO(Customer.class);

            if (customerDAO.selectByEmail(email) == null) {
                Customer customer = customerDAO.select(Session.getId());

                performUpdateCustomer(customer, customerDAO);
            } else {
                Customer customer = customerDAO.selectByEmail(email);

                if (customer.getId() == Session.getId()) {
                    performUpdateCustomer(customer, customerDAO);
                } else {
                    CustomDialog.showOkDialog(this, "Error update", "Email (" + email + ") already exists");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }

    private void performUpdateCustomer(Customer customer, IEntityDAO entityDAO) {
        editCustomerWithForm(customer);
        entityDAO.update(customer);

        Toast.makeText(this, R.string.message_saved, Toast.LENGTH_SHORT).show();
    }

    private void editCustomerWithForm(Customer customer) {
        String firstName = this.formCustomer.editTextFirstName.getText().toString().trim();
        String lastName = this.formCustomer.editTextLastName.getText().toString().trim();
        String email = this.formCustomer.editTextEmail.getText().toString().trim();

        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
    }
}