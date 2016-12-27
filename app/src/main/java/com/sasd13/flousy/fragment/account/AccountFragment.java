package com.sasd13.flousy.fragment.account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sasd13.androidex.activity.actionbar.IActionModeConsumer;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactory;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.gui.widget.recycler.tab.EnumTabType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.R;
import com.sasd13.flousy.activities.ConsultActivity;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.gui.tab.OperationItemModel;
import com.sasd13.flousy.handler.AccountHandler;
import com.sasd13.flousy.util.Sorter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccountFragment extends Fragment implements IActionModeConsumer {

    private static final String PATTERN_DECIMAL = "#.##";

    private Account account;
    private ConsultActivity parentActivity;
    private AccountHandler accountHandler;
    private AccountActionModeProvider actionModeProvider;
    private DecimalFormat df;
    private TextView textViewSold;
    private Recycler tabOperations;
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
        actionModeProvider = new AccountActionModeProvider(this);
        df = new DecimalFormat(PATTERN_DECIMAL);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_account, container, false);

        GUIHelper.colorTitles(view);
        buildView(view);

        return view;
    }

    private void buildView(View view) {
        textViewSold = (TextView) view.findViewById(R.id.consult_textview_sold);

        buildTabOperations(view);
        buildFloatingActionButton(view);
    }

    private void buildTabOperations(View view) {
        tabOperations = RecyclerFactory
                .makeBuilder(EnumTabType.TAB)
                .build((RecyclerView) view.findViewById(R.id.consult_recyclerview));
        tabOperations.addDividerItemDecoration();
    }

    private void buildFloatingActionButton(View view) {
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
        tabOperations.clear();

        List<Operation> operations = Arrays.asList(account.getOperations());
        Sorter.sortOperationsByDateDesc(operations);

        RecyclerHolder recyclerHolder = new RecyclerHolder();
        RecyclerHolderPair pair;
        OperationItemModel operationItemModel;

        for (Operation operation : operations) {
            operationItemModel = new OperationItemModel(operation);
            pair = new RecyclerHolderPair(operationItemModel);

            pair.addController(EnumActionEvent.CLICK, new OperationItemActionClick(operationItemModel, actionModeProvider, parentActivity));
            pair.addController(EnumActionEvent.LONG_CLICK, new OperationItemActionLongClick(operationItemModel, actionModeProvider));

            recyclerHolder.add(pair);
        }

        RecyclerHelper.addAll(tabOperations, recyclerHolder);
    }

    @Override
    public void onPause() {
        super.onPause();

        if (actionModeProvider.inActionMode()) {
            actionModeProvider.finishActionMode();
        }
    }

    @Override
    public void onCreateActionMode() {
        floatingActionButton.hide();
    }

    @Override
    public void onDestroyActionMode() {
        floatingActionButton.show();
    }

    public void deleteOperations(List<OperationItemModel> operationItemModels) {
        List<Operation> operations = new ArrayList<>();

        for (OperationItemModel operationItemModel : operationItemModels) {
            operations.add(operationItemModel.getOperation());
        }

        accountHandler.deleteOperations(operations);
    }

    public void onDeleteSucceeded() {
        Snackbar.make(getView(), R.string.message_deleted, Snackbar.LENGTH_SHORT).show();

        refreshView();
    }

    public void onError(@StringRes int error) {
        Snackbar.make(getView(), error, Snackbar.LENGTH_SHORT).show();
    }
}