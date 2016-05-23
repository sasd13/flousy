package com.sasd13.flousy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sasd13.androidex.gui.widget.dialog.CustomDialog;
import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.dao.db.SQLitePasswordDAO;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.LayeredPersistor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LogActivity extends Activity {

    private class FormLogViewHolder {
        public EditText editTextEmail, editTextPassword;
        public Button buttonConnect;
    }

    private static final int TIMEOUT = 2000;

    private FormLogViewHolder formLog;

    private SQLiteDAO dao = SQLiteDAO.getInstance();
    private LayeredPersistor persistor = new LayeredPersistor(dao);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log);
        createFormLog();
        customizeView();
    }

    private void createFormLog() {
        formLog = new FormLogViewHolder();

        formLog.editTextEmail = (EditText) findViewById(R.id.log_edittext_email);
        formLog.editTextPassword = (EditText) findViewById(R.id.log_edittext_password);
        formLog.buttonConnect = (Button) findViewById(R.id.log_button_connect);

        formLog.buttonConnect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (formLog.editTextEmail.getText().toString().trim().length() > 0
                        && formLog.editTextPassword.getText().toString().trim().length() > 0) {
                    logIn();
                }
            }
        });
    }

    private void logIn() {
        String email = formLog.editTextEmail.getText().toString().trim();

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.EMAIL.getName(), new String[]{ email });

        List<Customer> customers = persistor.read(parameters, Customer.class);

        if (customers.size() == 1) {
            Customer customer = customers.get(0);

            String candidate = formLog.editTextPassword.getText().toString().trim();
            String password = selectPasswordFromDAO(customer);

            if (password.equals(candidate)) {
                SessionHelper.setExtraIdInSession(Extra.CUSTOMER_ID, customer.getId());
                goToHomeActivity();
            } else {
                CustomDialog.showOkDialog(
                        this,
                        getResources().getString(R.string.log_alertdialog_title_error_log),
                        getResources().getString(R.string.log_alertdialog_message_error_log));
            }
        } else {
            CustomDialog.showOkDialog(
                    this,
                    getResources().getString(R.string.log_alertdialog_title_error_log),
                    getResources().getString(R.string.log_alertdialog_message_error_log));
        }
    }

    private String selectPasswordFromDAO(Customer customer) {
        return new SQLitePasswordDAO(dao.getDB()).select(customer.getId());
    }

    private void goToHomeActivity() {
        final WaitDialog waitDialog = new WaitDialog(this);

        TaskPlanner taskPlanner = new TaskPlanner(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(LogActivity.this, HomeActivity.class);
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
                (TextView) findViewById(R.id.log_textview_signup)
        };

        SpannableString text;
        for (TextView textView : textViews) {
            text = new SpannableString(textView.getText().toString());
            text.setSpan(new UnderlineSpan(), 0, text.length(), 0);
            textView.setText(text);

            switch (textView.getId()) {
                case R.id.log_textview_signup :
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(LogActivity.this, SignActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            startActivity(intent);
                        }
                    });
            }
        }
    }
}