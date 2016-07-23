package com.sasd13.flousy.fragment.consult;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.dialog.OptionDialog;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.ConsultActivity;
import com.sasd13.flousy.R;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.gui.tab.OperationItemActionClick;
import com.sasd13.flousy.gui.tab.OperationItemActionLongClick;
import com.sasd13.flousy.gui.tab.OperationItemModel;
import com.sasd13.flousy.handler.consult.AccountHandler;
import com.sasd13.flousy.util.CollectionsHelper;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountFragment extends Fragment {

    private static final String PATTERN_DECIMAL = "#.##";

    private Account account;
    private ConsultActivity parentActivity;
    private AccountHandler accountHandler;
    private DecimalFormat df;
    private TextView textViewSold;
    private Recycler tab;
    private ActionMode actionMode;
    private AccountActionModeCallback callback;
    private List<OperationItemModel> selectedModels;
    private FloatingActionButton floatingActionButton;

    public static AccountFragment newInstance(Account account) {
        AccountFragment accountFragment = new AccountFragment();
        accountFragment.account = account;

        return accountFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        parentActivity = (ConsultActivity) getActivity();
        accountHandler = new AccountHandler(this);
        df = new DecimalFormat(PATTERN_DECIMAL);
        callback = new AccountActionModeCallback(this);
        selectedModels = new ArrayList<>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        GUIHelper.colorTitles(view);
        buildView(view);

        return view;
    }

    private void buildView(View view) {
        textViewSold = (TextView) view.findViewById(R.id.consult_textview_sold);

        tab = RecyclerFactory.makeBuilder(EnumTabType.TAB).build((RecyclerView) view.findViewById(R.id.consult_recyclerview));
        tab.addDividerItemDecoration();

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.consult_floatingactionbutton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                parentActivity.newOperation();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        refreshView();
    }

    private void refreshView() {
        parentActivity.getSupportActionBar().setTitle(getResources().getString(R.string.activity_consult));
        parentActivity.getSupportActionBar().setSubtitle(null);

        textViewSold.setText(String.valueOf(df.format(account.getSold())));

        fillTabOperations();
    }

    private void fillTabOperations() {
        tab.clear();

        List<Operation> operations = Arrays.asList(account.getOperations());
        CollectionsHelper.sortOperationsByDateDesc(operations);

        RecyclerHolder recyclerHolder = new RecyclerHolder();
        RecyclerHolderPair pair;
        OperationItemModel operationItemModel;

        for (Operation operation : operations) {
            operationItemModel = new OperationItemModel(operation);
            pair = new RecyclerHolderPair(operationItemModel);

            pair.addController(EnumActionEvent.CLICK, new OperationItemActionClick(this, operationItemModel));
            pair.addController(EnumActionEvent.LONG_CLICK, new OperationItemActionLongClick(this, operationItemModel));

            recyclerHolder.add(pair);
        }

        RecyclerHelper.addAll(tab, recyclerHolder);
    }

    public boolean inActionMode() {
        return actionMode != null;
    }

    public void startActionMode() {
        actionMode = getActivity().startActionMode(callback);
    }

    public void onStartActionMode() {
        floatingActionButton.hide();
    }

    public void onFinishActionMode() {
        actionMode = null;

        resetSelectedModels();
        floatingActionButton.show();
    }

    private void resetSelectedModels() {
        for (OperationItemModel selectedModel : selectedModels) {
            selectedModel.setSelected(false);
        }

        selectedModels.clear();
    }

    public void onSelectModel(OperationItemModel model) {
        if (!model.isSelected()) {
            model.setSelected(true);
            selectedModels.add(model);
        } else {
            model.setSelected(false);
            selectedModels.remove(model);
        }

        refreshActionModeTitle();
    }

    private void refreshActionModeTitle() {
        if (selectedModels.size() <= 1) {
            actionMode.setTitle(selectedModels.size() + " selectionné");
        } else {
            actionMode.setTitle(selectedModels.size() + " selectionnés");
        }
    }

    public void deleteSelectedOperations() {
        OptionDialog.showOkCancelDialog(
                getContext(),
                getResources().getString(R.string.message_delete),
                getResources().getString(R.string.message_confirm),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        deleteOperations();
                        actionMode.finish();
                    }
                }
        );
    }

    private void deleteOperations() {
        if (!selectedModels.isEmpty()) {
            List<Operation> operations = new ArrayList<>();

            for (OperationItemModel model : selectedModels) {
                operations.add(model.getOperation());
            }

            accountHandler.deleteOperations(operations);
        }
    }

    public void onDeleteSucceeded() {
        Toast.makeText(getContext(), getResources().getString(R.string.message_deleted), Toast.LENGTH_SHORT).show();
        selectedModels.clear();
        refreshView();
    }

    public void onError(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
    }
}