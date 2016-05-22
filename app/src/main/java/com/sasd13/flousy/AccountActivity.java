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
import com.sasd13.flousy.bean.Transaction;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.dao.db.SQLiteDAO;
import com.sasd13.flousy.gui.widget.recycler.tab.TabItemTransaction;
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
        createTabTransactions();
    }

    private void createTextViewSold() {
        textViewSold = (TextView) findViewById(R.id.account_textview_sold);
    }

    private void createTabTransactions() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.account_recyclerview);

        tab = new Tab(recyclerView, R.layout.tabitem_transaction);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Map<String, String[]> parameters = new HashMap<>();
        parameters.put(Parameter.CUSTOMER.getName(), new String[] { String.valueOf(SessionHelper.getExtraIdFromSession(Extra.CUSTOMER_ID)) });

        Account account = persistor.deepRead(parameters, Account.class).get(0);

        fillTextViewSold(account);
        fillTabTransactions(account.getTransactions());
    }

    private void fillTextViewSold(Account account) {
        DecimalFormat df = new DecimalFormat("#.##");

        textViewSold.setText(String.valueOf(df.format(account.getSold())));
    }

    private void fillTabTransactions(List<Transaction> transactions) {
        tab.clearItems();

        addTransactionsToTab(transactions);
    }

    private void addTransactionsToTab(List<Transaction> transactions) {
        TabItemTransaction tabItemTransaction;
        Intent intent;

        for (Transaction transaction : transactions) {
            tabItemTransaction = new TabItemTransaction();

            tabItemTransaction.setDate(String.valueOf(transaction.getDateRealization()));
            tabItemTransaction.setTitle(transaction.getTitle());
            tabItemTransaction.setValue(String.valueOf(transaction.getAmount()));

            intent = new Intent(this, TransactionActivity.class);
            intent.putExtra(Extra.MODE, Extra.MODE_EDIT);
            intent.putExtra(Extra.TRANSACTION_ID, transaction.getId());
            tabItemTransaction.setIntent(intent);

            tab.addItem(tabItemTransaction);
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
                newTransaction();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void newTransaction() {
        Intent intent = new Intent(this, TransactionActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Extra.MODE, Extra.MODE_NEW);

        startActivity(intent);
    }
}