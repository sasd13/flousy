package com.sasd13.flousy.view.fragment.authentication;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.sasd13.androidex.gui.widget.dialog.WaitDialog;
import com.sasd13.flousy.R;
import com.sasd13.flousy.activities.IdentityActivity;
import com.sasd13.flousy.scope.Scope;
import com.sasd13.flousy.view.ILogInController;

import java.util.Observable;
import java.util.Observer;

public class LogInFragment extends Fragment implements Observer {

    private static class LogInForm {
        EditText editTextUsername, editTextPassword;
    }

    private ILogInController controller;
    private Scope scope;
    private LogInForm logInForm;
    private WaitDialog waitDialog;

    public static LogInFragment newInstance() {
        return new LogInFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (ILogInController) ((IdentityActivity) getActivity()).lookup(ILogInController.class);
        scope = controller.getScope();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        scope.addObserver(this);

        View view = inflater.inflate(R.layout.layout_login, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        buildFormLogIn(view);
        buildWaitDialog(view);
    }

    private void buildFormLogIn(View view) {
        logInForm = new LogInForm();
        logInForm.editTextUsername = (EditText) view.findViewById(R.id.login_edittext_username);
        logInForm.editTextPassword = (EditText) view.findViewById(R.id.login_edittext_password);

        buildButtonConnect(view);
    }

    private void buildButtonConnect(View view) {
        Button button = (Button) view.findViewById(R.id.login_button_connect);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logIn();
            }
        });
    }

    private void logIn() {
        String username = logInForm.editTextUsername.getText().toString();
        String password = logInForm.editTextPassword.getText().toString();

        if (!username.isEmpty() && !password.isEmpty()) {
            controller.logIn(username, password);
        } else {
            controller.display(R.string.error_empty_credential);
        }
    }

    private void buildWaitDialog(View view) {
        waitDialog = new WaitDialog(view.getContext());
    }

    @Override
    public void update(Observable observable, Object o) {
        if (scope.isLoading() && !waitDialog.isShowing()) {
            waitDialog.show();
        } else if (!scope.isLoading() && waitDialog.isShowing()) {
            waitDialog.dismiss();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        scope.deleteObserver(this);
    }
}