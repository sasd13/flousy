package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.constant.Extra;
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
        public Button buttonConnect;
    }

    private static final int TIMEOUT = 2000;

    private FormLoginViewHolder formLogin;

    private SQLiteDAO dao = SQLiteDAO.getInstance();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        createFormLog();
        customizeView();
    }

    private void createFormLog() {
        formLogin = new FormLoginViewHolder();

        formLogin.editTextEmail = (EditText) findViewById(R.id.login_edittext_email);
        formLogin.editTextPassword = (EditText) findViewById(R.id.login_edittext_password);
        formLogin.buttonConnect = (Button) findViewById(R.id.login_button_connect);

        formLogin.buttonConnect.setOnClickListener(new View.OnClickListener() {

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
                SessionHelper.setExtraIdInSession(Extra.CUSTOMER_ID, customers.get(0).getId());
                goToHomeActivity();
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

    private void goToHomeActivity() {
        final WaitDialog waitDialog = new WaitDialog(this);

        TaskPlanner taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                startActivity(intent);
                waitDialog.dismiss();
            }
        }, TIMEOUT);

        taskPlanner.start();
        waitDialog.show();
    }

    private void customizeView() {
        //Add underline and link for textViews
        TextView[] textViews = {
                (TextView) findViewById(R.id.login_textview_signup)
        };

        for (TextView textView : textViews) {
            GUIHelper.addUnderline(textView);

            switch (textView.getId()) {
                case R.id.login_textview_signup:
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(LoginActivity.this, SignActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            startActivity(intent);
                        }
                    });
            }
        }
    }
}