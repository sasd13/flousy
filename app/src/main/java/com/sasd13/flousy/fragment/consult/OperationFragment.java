package com.sasd13.flousy.fragment.consult;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.form.EnumFormType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.ConsultActivity;
import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.form.OperationForm;
import com.sasd13.flousy.content.handler.consult.OperationHandler;

public class OperationFragment extends Fragment {

    private ConsultActivity parentActivity;
    private Operation operation;
    private boolean inModeEdit;

    private OperationHandler operationHandler;
    private OperationForm operationForm;

    public static OperationFragment newInstance(ConsultActivity parentActivity) {
        OperationFragment operationFragment = new OperationFragment();
        operationFragment.parentActivity = parentActivity;

        return operationFragment;
    }

    public static OperationFragment newInstance(ConsultActivity parentActivity, Operation operation) {
        OperationFragment operationFragment = newInstance(parentActivity);
        operationFragment.operation = operation;
        operationFragment.inModeEdit = true;

        return operationFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        operationHandler = new OperationHandler(this);
        operationForm = new OperationForm(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_operation, container, false);

        GUIHelper.colorTitles(this);
        buildView(view);

        return view;
    }

    private void buildView(View view) {
        Recycler form = RecyclerFactory.makeBuilder(EnumFormType.FORM).build((RecyclerView) view.findViewById(R.id.operation_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, operationForm.getHolder());
        fillOperationView();
    }

    private void fillOperationView() {
        if (!inModeEdit) {
            operation = operationHandler.getDefaultValueOfOperation();
        }

        operationForm.bindOperation(operation);
    }

    public void saveOperation() {
        if (inModeEdit) {
            operationHandler.updateOperation(operation, operationForm);
        } else {
            operationHandler.createOperation(operationForm);
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
        Toast.makeText(getContext(), R.string.message_saved, Toast.LENGTH_SHORT).show();
        parentActivity.notifyAddedOperation(operation);
        parentActivity.listOperations();
    }

    public void onUpdateSucceeded() {
        Toast.makeText(getContext(), R.string.message_saved, Toast.LENGTH_SHORT).show();
    }

    public void onDeleteSucceeded() {
        Toast.makeText(getContext(), R.string.message_deleted, Toast.LENGTH_SHORT).show();
        parentActivity.notifyRemovedOperation(operation);
        parentActivity.listOperations();
    }

    public void onError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}