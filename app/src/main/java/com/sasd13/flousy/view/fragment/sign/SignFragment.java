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
import com.sasd13.androidex.gui.widget.recycler.EnumRecyclerType;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.R;
import com.sasd13.flousy.activity.IdentityActivity;
import com.sasd13.flousy.util.wrapper.UserCreate;
import com.sasd13.flousy.scope.SignScope;
import com.sasd13.flousy.view.ISignController;
import com.sasd13.flousy.view.gui.form.SignForm;

public class SignFragment extends Fragment {

    private ISignController controller;
    private SignScope scope;
    private SignForm signForm;
    private Menu menu;

    public static SignFragment newInstance() {
        return new SignFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = (ISignController) ((IdentityActivity) getActivity()).lookup(ISignController.class);
        scope = (SignScope) controller.getScope();

        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.layout_sign, container, false);

        buildView(view);

        return view;
    }

    private void buildView(View view) {
        GUIHelper.colorTitles(this);
        buildFormSign(view);
    }

    private void buildFormSign(View view) {
        signForm = new SignForm(getContext());
        Recycler recycler = RecyclerFactory.makeBuilder(EnumRecyclerType.FORM).build((RecyclerView) view.findViewById(R.id.sign_recyclerview));

        recycler.addDividerItemDecoration();
        RecyclerHelper.addAll(recycler, signForm.getHolder());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        this.menu = menu;

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
            if (signForm.isTermsAccepted()) {
                controller.actionSign(getUserFromForm(), getCustomerFromForm());
            } else {
                controller.display(getString(R.string.form_sign_message_error_terms));
            }
        } catch (FormException e) {
            controller.display(e.getMessage());
        }
    }

    private UserCreate getUserFromForm() throws FormException {
        UserCreate userCreate = scope.getUserCreate();

        userCreate.getUser().setIntermediary(signForm.getIntermediary());
        userCreate.getCredential().setUsername(signForm.getUsername());
        userCreate.getCredential().setPassword(signForm.getPassword());

        return userCreate;
    }

    private Customer getCustomerFromForm() throws FormException {
        Customer customer = scope.getCustomer();

        customer.setFirstName(signForm.getFirstName());
        customer.setLastName(signForm.getLastName());
        customer.setEmail(signForm.getEmail());

        return customer;
    }

    @Override
    public void onStart() {
        super.onStart();

        ((IdentityActivity) getActivity()).getSupportActionBar().setTitle(R.string.app_name);
        ((IdentityActivity) getActivity()).getSupportActionBar().setSubtitle(R.string.title_fill_form);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        if (menu != null) {
            menu.setGroupVisible(R.id.menu_sign_group, false);
        }
    }
}