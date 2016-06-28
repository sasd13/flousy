package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.dao.db.SQLitePasswordDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.DAOException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private class FormLoginViewHolder {
        public EditText editTextEmail, editTextPassword;
    }

    public static LoginActivity self;

    private FormLoginViewHolder formLogin;
    private SQLiteDAO dao = SQLiteDAO.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        self = this;

        setContentView(R.layout.activity_login);
        createFormLog();
        createButtonConnect();
        setTextViewSign();
    }

    private void createFormLog() {
        formLogin = new FormLoginViewHolder();

        formLogin.editTextEmail = (EditText) findViewById(R.id.login_edittext_email);
        formLogin.editTextPassword = (EditText) findViewById(R.id.login_edittext_password);
    }

    private void createButtonConnect() {
        Button button = (Button) findViewById(R.id.login_button_connect);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (formLogin.editTextEmail.getText().toString().trim().length() > 0
                        && formLogin.editTextPassword.getText().toString().trim().length() > 0) {
                    logIn();
                }
            }
        });
    }

    private void logIn() {
        String email = formLogin.editTextEmail.getText().toString().trim();
        String candidate = formLogin.editTextPassword.getText().toString().trim();

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.EMAIL.getName(), new String[]{email});

        try {
            dao.open();

            List<Customer> customers = dao.getEntityDAO(Customer.class).select(parameters);
            if (customers.size() == 1 && passwordMatches(customers.get(0), candidate)) {
                SessionHelper.logIn(this, customers.get(0).getId(), customers.get(0).getFirstName());
            } else {
                OptionDialog.showOkDialog(
                        this,
                        getResources().getString(R.string.login_alertdialog_title_error_login),
                        getResources().getString(R.string.login_alertdialog_message_error_login));
            }
        } catch (DAOException e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
    }

    private boolean passwordMatches(Customer customer, String password) {
        return new SQLitePasswordDAO(dao.getDB()).contains(password, customer.getId());
    }

    private void setTextViewSign() {
        TextView textView = (TextView) findViewById(R.id.login_textview_signup);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignActivity.class));
            }
        });

        GUIHelper.addUnderline(textView);
    }
}