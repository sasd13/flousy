package com.sasd13.flousy.view.fragment.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.form.FormException;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.R;
import com.sasd13.flousy.activities.IdentityActivity;
import com.sasd13.flousy.bean.Customer;
import com.sasd13.flousy.view.ISignController;
import com.sasd13.flousy.view.gui.form.SignForm;

public class SignUpFragment extends Fragment {

    private ISignController controller;
    private SignForm signForm;

    public static SignUpFragment newInstance() {
        return new SignUpFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        controller = (ISignController) ((IdentityActivity) getActivity()).lookup(ISignController.class);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_signup, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(this);
        buildFormSign(view);
    }

    private void buildFormSign(View view) {
        signForm = new SignForm(getContext());

        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.sign_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, signForm.getHolder());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_signup, menu);
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
        try {
            if (!signForm.isTermsAccepted()) {
                controller.displayMessage(getString(R.string.form_sign_message_error_terms));
            } else {
                controller.sign(getCustomerFromForm());
            }
        } catch (FormException e) {
            controller.displayMessage(e.getMessage());
        }
    }

    private Customer getCustomerFromForm() throws FormException {
        Customer customer = new Customer();

        customer.setFirstName(signForm.getFirstName());
        customer.setLastName(signForm.getLastName());
        customer.setEmail(signForm.getEmail());

        return customer;
    }

    @Override
    public void onStart() {
        super.onStart();

        ((IdentityActivity) getActivity()).getSupportActionBar().setTitle(getResources().getString(R.string.app_name));
        ((IdentityActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.title_fill_form);
    }
}