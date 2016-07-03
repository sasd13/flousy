package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.sasd13.androidex.gui.Action;
import com.sasd13.androidex.gui.widget.recycler.RecyclerHolder;
import com.sasd13.androidex.gui.widget.recycler.tab.Tab;
import com.sasd13.androidex.util.DateTimeHelper;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.content.Extra;
import com.sasd13.flousy.content.handler.ConsultHandler;
import com.sasd13.flousy.gui.recycler.tab.OperationModel;
import com.sasd13.flousy.gui.recycler.tab.TabFactory;
import com.sasd13.flousy.util.SessionHelper;

import java.text.DecimalFormat;
import java.util.List;

public class ConsultActivity extends MotherActivity {

    private static final String PATTERN_DECIMAL = "#.##";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ConsultHandler.init(this);
        setContentView(R.layout.activity_consult);
        GUIHelper.colorTitles(this);
        buildConsultView();
    }

    private void buildConsultView() {
        Account account = ConsultHandler.readAccount(SessionHelper.getExtraIdFromSession(Extra.CUSTOMER_ID));

        createTextViewSold(account);
        createTabOperations(account);
    }

    private void createTextViewSold(Account account) {
        TextView textViewSold = (TextView) findViewById(R.id.consult_textview_sold);

        DecimalFormat df = new DecimalFormat(PATTERN_DECIMAL);
        textViewSold.setText(String.valueOf(df.format(account.getSold())));
    }

    private void createTabOperations(Account account) {
        TabFactory tabFactory = new TabFactory(this);
        Tab tab = (Tab) tabFactory.makeBuilder().build((RecyclerView) findViewById(R.id.consult_recyclerview));

        fillTabOperations(tab, account.getOperations(), tabFactory);
    }

    private void fillTabOperations(Tab tab, List<Operation> operations, TabFactory tabFactory) {
        tab.clear();

        RecyclerHolder recyclerHolder = new RecyclerHolder();
        OperationModel[] operationModels = new OperationModel[operations.size()];

        int i = -1;

        for (final Operation operation : operations) {
            i++;

            operationModels[i] = new OperationModel();
            operationModels[i].setDate(DateTimeHelper.format(
                    operation.getDateRealization(),
                    DateTimeHelper.getLocaleDateFormatPattern(this, DateTimeHelper.Format.SHORT)));
            operationModels[i].setLabel(operation.getTitle());
            operationModels[i].setAmount(String.valueOf(operation.getAmount()));
            operationModels[i].setActionClick(new Action() {
                @Override
                public void execute() {
                    Intent intent = new Intent(ConsultActivity.this, OperationActivity.class);
                    intent.putExtra(Extra.MODE, Extra.MODE_EDIT);
                    intent.putExtra(Extra.OPERATION_ID, operation.getId());

                    startActivity(intent);
                }
            });
        }

        recyclerHolder.add(operationModels);
        RecyclerHelper.fill(tab, recyclerHolder, tabFactory);
    }
}