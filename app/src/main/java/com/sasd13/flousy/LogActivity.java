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
import com.sasd13.androidex.session.Session;
import com.sasd13.androidex.util.TaskPlanner;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.db.CustomerDAO;
import com.sasd13.flousy.db.sqlite.SQLiteDAO;
import com.sasd13.javaex.db.IDAO;

public class LogActivity extends Activity {

    private class FormLogViewHolder {
        public EditText editTextNumber, editTextPassword;
        public Button buttonConnect;
    }

    private static final int TIMEOUT = 2000;

    private FormLogViewHolder formLog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log);

        createFormLog();
        customizeView();
    }

    private void createFormLog() {
        this.formLog = new FormLogViewHolder();

        this.formLog.editTextNumber = (EditText) findViewById(R.id.log_edittext_number);
        this.formLog.editTextPassword = (EditText) findViewById(R.id.log_edittext_password);

        this.formLog.buttonConnect = (Button) findViewById(R.id.log_button_connect);
        this.formLog.buttonConnect.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (formLog.editTextNumber.getText().toString().trim().length() > 0
                        && formLog.editTextPassword.getText().toString().trim().length() > 0) {
                    logIn();
                }
            }
        });
    }

    private void logIn() {
        String number = this.formLog.editTextNumber.getText().toString().trim();
        String password = this.formLog.editTextPassword.getText().toString().trim();

        IDAO dao = SQLiteDAO.getInstance();

        try {
            dao.open();

            Customer customer = ((CustomerDAO) dao.getEntityDAO(Customer.class)).selectByNumber(number);

            if (customer != null && password.equals(customer.getPassword())) {
                Session.logIn(customer.getId());
                goToHomeActivity();
            } else {
                CustomDialog.showOkDialog(
                        this,
                        getResources().getString(R.string.log_alertdialog_title_error_log),
                        getResources().getString(R.string.log_alertdialog_message_error_log));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dao.close();
        }
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