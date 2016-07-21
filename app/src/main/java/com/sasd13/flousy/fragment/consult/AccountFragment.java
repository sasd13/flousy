package com.sasd13.flousy.fragment.consult;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.EnumActionEvent;
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
import com.sasd13.flousy.gui.widget.recycler.tab.OperationItemModel;
import com.sasd13.flousy.util.CollectionsHelper;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class AccountFragment extends Fragment {

    private static final String PATTERN_DECIMAL = "#.##";

    private Account account;
    private ConsultActivity parentActivity;
    private DecimalFormat df;
    private TextView textViewSold;
    private Recycler tab;

    public static AccountFragment newInstance() {
        return new AccountFragment();
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        parentActivity = (ConsultActivity) context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        df = new DecimalFormat(PATTERN_DECIMAL);
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

        FloatingActionButton floatingActionButton = (FloatingActionButton) view.findViewById(R.id.consult_floatingactionbutton);
        assert floatingActionButton != null;
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

        for (final Operation operation : operations) {
            operationItemModel = new OperationItemModel(operation);

            pair = new RecyclerHolderPair(operationItemModel);
            pair.addController(EnumActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    parentActivity.editOperation(operation);
                }
            });

            recyclerHolder.add(pair);
        }

        RecyclerHelper.addAll(tab, recyclerHolder);
    }
}