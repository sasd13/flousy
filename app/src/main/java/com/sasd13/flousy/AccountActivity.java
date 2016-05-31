package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.sasd13.androidex.gui.widget.recycler.tab.Tab;
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

    private LayeredPersistor persistor = new LayeredPersistor(SQLiteDAO.getInstance());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account);
        createTextViewSold();
        createTabOperations();
    }

    private void createTextViewSold() {
        textViewSold = (TextView) findViewById(R.id.account_textview_sold);
    }

    private void createTabOperations() {
        tab = new Tab((RecyclerView) findViewById(R.id.account_recyclerview));
    }

    @Override
    protected void onStart() {
        super.onStart();

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.CUSTOMER.getName(), new String[] { String.valueOf(SessionHelper.getExtraIdFromSession(Extra.CUSTOMER_ID)) });

        Account account = persistor.deepRead(parameters, Account.class).get(0);

        fillTextViewSold(account);
        fillTabOperations(account.getOperations());
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
        Intent intent;

        for (Operation operation : operations) {
            tabItemOperation = new TabItemOperation();

            tabItemOperation.setDate(String.valueOf(operation.getDateRealization()));
            tabItemOperation.setTitle(operation.getTitle());
            tabItemOperation.setAmount(String.valueOf(operation.getAmount()));

            intent = new Intent(this, OperationActivity.class);
            intent.putExtra(Extra.MODE, Extra.MODE_EDIT);
            intent.putExtra(Extra.OPERATION_ID, operation.getId());
            tabItemOperation.setIntent(intent);

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
}