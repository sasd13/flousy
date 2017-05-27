package com.sasd13.flousy.view.fragment.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.flousy.R;
import com.sasd13.flousy.activities.IdentityActivity;
import com.sasd13.flousy.view.fragment.ISignController;

public class SignInFragment extends Fragment {

    private static class LogInForm {
        private EditText editTextEmail, editTextPassword;
    }

    private ISignController controller;

    public static SignInFragment newInstance() {
        return new SignInFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (ISignController) ((IdentityActivity) getActivity()).lookup(ISignController.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_signin, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        LogInForm logInForm = new LogInForm();
        logInForm.editTextEmail = (EditText) view.findViewById(R.id.login_edittext_email);
        logInForm.editTextPassword = (EditText) view.findViewById(R.id.login_edittext_password);

        buildButtonConnect(view, logInForm);
        buildTextViewSignUp(view);
    }

    private void buildButtonConnect(View view, final LogInForm logInForm) {
        Button button = (Button) view.findViewById(R.id.login_button_connect);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!logInForm.editTextEmail.getText().toString().trim().isEmpty()
                        && !logInForm.editTextPassword.getText().toString().trim().isEmpty()) {
                    String email = logInForm.editTextEmail.getText().toString().trim();
                    String password = logInForm.editTextPassword.getText().toString().trim();

                    controller.signIn(email, password);
                }
            }
        });
    }

    private void buildTextViewSignUp(View view) {
        TextView textView = (TextView) view.findViewById(R.id.login_textview_signup);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller.showSignUp();
            }
        });

        GUIHelper.addUnderline(textView);
    }
}