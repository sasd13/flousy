package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sasd13.androidex.gui.IAction;
import com.sasd13.androidex.gui.widget.ActionEvent;
import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactoryProducer;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolderPair;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.handler.ConsultHandler;
import com.sasd13.flousy.gui.recycler.MyRecyclerFactoryType;
import com.sasd13.flousy.gui.recycler.tab.MyTabFactory;
import com.sasd13.flousy.gui.recycler.tab.OperationItemModel;
import com.sasd13.flousy.util.CollectionsHelper;
import com.sasd13.flousy.util.SessionHelper;

import java.text.DecimalFormat;
import java.util.List;

public class ConsultActivity extends MotherActivity {

    private static final String PATTERN_DECIMAL = "#.##";

    private TextView textViewSold;
    private Recycler tab;

    private DecimalFormat df = new DecimalFormat(PATTERN_DECIMAL);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_consult);
        GUIHelper.colorTitles(this);
        buildConsultView();
    }

    private void buildConsultView() {
        textViewSold = (TextView) findViewById(R.id.consult_textview_sold);

        registerFactory();

        tab = RecyclerHelper.produce(MyRecyclerFactoryType.TAB_OPERATION, (RecyclerView) findViewById(R.id.consult_recyclerview));
    }

    private void registerFactory() {
        try {
            RecyclerFactoryProducer.registerFactory(MyRecyclerFactoryType.TAB_OPERATION, MyTabFactory.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Account account = ConsultHandler.readAccount(this, SessionHelper.getExtraIdFromSession(this, Extra.CUSTOMER_ID));

        textViewSold.setText(String.valueOf(df.format(account.getSold())));

        fillTabOperations(account.getOperations());
    }

    private void fillTabOperations(List<Operation> operations) {
        tab.clear();

        CollectionsHelper.sortOperationByDateDesc(operations);

        RecyclerHolder recyclerHolder = new RecyclerHolder();
        RecyclerHolderPair pair;
        OperationItemModel operationItemModel;

        for (final Operation operation : operations) {
            operationItemModel = new OperationItemModel(operation);

            pair = new RecyclerHolderPair(operationItemModel);
            pair.addController(ActionEvent.CLICK, new IAction() {
                @Override
                public void execute() {
                    Intent intent = new Intent(ConsultActivity.this, OperationActivity.class);
                    intent.putExtra(Extra.MODE, Extra.MODE_EDIT);
                    intent.putExtra(Extra.OPERATION_ID, operation.getId());

                    startActivity(intent);
                }
            });

            recyclerHolder.add(pair);
        }

        RecyclerHelper.addAll(tab, recyclerHolder, this);
        tab.addDividerItemDecoration();
    }
}