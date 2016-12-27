package com.sasd13.flousy.fragment.operation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.R;
import com.sasd13.flousy.activities.ConsultActivity;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.gui.form.OperationForm;
import com.sasd13.flousy.handler.OperationHandler;

public class OperationFragment extends Fragment {

    private Account account;
    private Operation operation;
    private boolean inModeEdit;
    private ConsultActivity parentActivity;
    private OperationHandler operationHandler;
    private OperationForm operationForm;

    public static OperationFragment newInstance(Account account) {
        OperationFragment operationFragment = new OperationFragment();
        operationFragment.account = account;

        return operationFragment;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;

        inModeEdit = true;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        parentActivity = (ConsultActivity) getActivity();
        operationHandler = new OperationHandler(this);
        operationForm = new OperationForm(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operation, container, false);

        GUIHelper.colorTitles(view);
        buildView(view);

        return view;
    }

    private void buildView(View view) {
        Recycler form = RecyclerFactory
                .makeBuilder(EnumFormType.FORM)
                .build((RecyclerView) view.findViewById(R.id.operation_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, operationForm.getHolder());
        bindFormWithOperation();
    }

    private void bindFormWithOperation() {
        if (!inModeEdit) {
            operation = operationHandler.getDefaultValueOfOperation();
        }

        operationForm.bindOperation(operation);
    }

    @Override
    public void onStart() {
        super.onStart();

        refreshView();
    }

    private void refreshView() {
        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.activity_operation));
        parentActivity.getSupportActionBar().setSubtitle(getResources().getString(R.string.title_fill_form));
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_operation, menu);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (!inModeEdit) {
            menu.findItem(R.id.menu_operation_action_delete).setVisible(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_operation_action_save:
                saveOperation();
                break;
            case R.id.menu_operation_action_delete:
                deleteOperation();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    public void saveOperation() {
        if (inModeEdit) {
            operationHandler.updateOperation(operation, operationForm);
        } else {
            operationHandler.createOperation(operationForm, account);
        }
    }

    public void deleteOperation() {
        OptionDialog.showYesNoDialog(
                getContext(),
                getResources().getString(R.string.message_delete),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        operationHandler.deleteOperation(operation);
                    }
                }
        );
    }

    public void onCreateSucceeded(Operation operation) {
        Snackbar.make(getView(), R.string.message_saved, Snackbar.LENGTH_SHORT).show();
        parentActivity.listOperations();
    }

    public void onUpdateSucceeded() {
        Snackbar.make(getView(), R.string.message_saved, Snackbar.LENGTH_SHORT).show();
    }

    public void onDeleteSucceeded() {
        Snackbar.make(getView(), R.string.message_deleted, Snackbar.LENGTH_SHORT).show();
        parentActivity.listOperations();
    }

    public void onError(@StringRes int error) {
        Snackbar.make(getView(), error, Snackbar.LENGTH_SHORT).show();
    }
}