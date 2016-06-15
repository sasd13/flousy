package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewStub;
import android.widget.TextView;

import com.sasd13.androidex.gui.color.ColorHelper;
import com.sasd13.androidex.gui.widget.recycler.RecyclerItem;
import com.sasd13.androidex.gui.widget.recycler.tab.Tab;
import com.sasd13.androidex.gui.widget.recycler.tab.TickTab;
import com.sasd13.androidex.gui.widget.recycler.tab.TickTabBar;
import com.sasd13.androidex.gui.widget.recycler.tab.TickTabItem;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Operation;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.gui.widget.recycler.tab.TabItemOperation;
import com.sasd13.flousy.util.Parameter;
import com.sasd13.flousy.util.SessionHelper;
import com.sasd13.javaex.db.LayeredPersistor;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AccountActivity extends MotherActivity {

    private TextView textViewSold;
    private Tab tab;
    private TickTab tickTab;

    private LayeredPersistor persistor = new LayeredPersistor(SQLiteDAO.getInstance());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account);
        ColorHelper.drawTitles(this);

        createTextViewSold();
        //createTabOperations();
        createTickTab();
    }

    private void createTextViewSold() {
        textViewSold = (TextView) findViewById(R.id.account_textview_sold);
    }

    private void createTabOperations() {
        tab = new Tab((RecyclerView) findViewById(R.id.account_recyclerview));
    }

    private void createTickTab() {
        tickTab = new TickTab((RecyclerView) findViewById(R.id.account_recyclerview), getSupportActionBar());

        TickTabBar tickTabBar = new TickTabBar(tickTab);
        tickTabBar.setLabel("Opérations");
        tickTabBar.inflate((ViewStub) findViewById(R.id.account_ticktabbar_viewstub));
        tickTab.setTickTabBar(tickTabBar);

        TickTabItem tickTabItem;

        for (int i=0; i<3; i++) {
            tickTabItem = new TickTabItem(tickTab);
            tickTabItem.setLabel("Item " + (i + 1));

            tickTab.addItem(tickTabItem);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.CUSTOMER.getName(), new String[] { String.valueOf(SessionHelper.getExtraIdFromSession(Extra.CUSTOMER_ID)) });

        Account account = persistor.deepRead(parameters, Account.class).get(0);

        fillTextViewSold(account);
        //fillTabOperations(account.getOperations());
    }

    private void fillTextViewSold(Account account) {
        DecimalFormat df = new DecimalFormat("#.##");

        textViewSold.setText(String.valueOf(df.format(account.getSold())));
    }

    private void fillTabOperations(List<Operation> operations) {
        tab.clearItems();

        addOperationsToTab(operations);
    }

    private void addOperationsToTab(List<Operation> operations) {
        TabItemOperation tabItemOperation;

        for (Operation operation : operations) {
            tabItemOperation = new TabItemOperation();

            tabItemOperation.setTag(operation);
            tabItemOperation.setDate(String.valueOf(operation.getDateRealization()));
            tabItemOperation.setLabel(operation.getTitle());
            tabItemOperation.setAmount(String.valueOf(operation.getAmount()));
            tabItemOperation.setOnClickListener(new RecyclerItem.OnClickListener() {
                @Override
                public void onClickOnRecyclerItem(RecyclerItem recyclerItem) {
                    Intent intent = new Intent(AccountActivity.this, OperationActivity.class);
                    intent.putExtra(Extra.MODE, Extra.MODE_EDIT);
                    intent.putExtra(Extra.OPERATION_ID, ((Operation) recyclerItem.getTag()).getId());

                    startActivity(intent);
                }
            });

            tab.addItem(tabItemOperation);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_account, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_account_action_new:
                newOperation();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void newOperation() {
        Intent intent = new Intent(this, OperationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Extra.MODE, Extra.MODE_NEW);

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (tickTab.isInTickState()) {
            tickTab.setInTickState(false);
        } else {
            super.onBackPressed();
        }
    }
}