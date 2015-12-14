package com.sasd13.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.DecimalFormat;

import com.sasd13.androidx.gui.widget.recycler.tab.Tab;
import com.sasd13.flousy.bean.Account;
import com.sasd13.flousy.bean.Transaction;
import com.sasd13.flousy.constant.Extra;
import com.sasd13.flousy.db.DAOFactory;
import com.sasd13.flousy.gui.widget.recycler.tab.TabItemTransaction;
import com.sasd13.flousy.session.Session;

public class AccountActivity extends MotherActivity {

    private TextView textViewSold;
    private Tab tab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account);

        createTextViewSold();
        createTabTransactions();
    }

    private void createTextViewSold() {
        this.textViewSold = (TextView) findViewById(R.id.account_textview_sold);
    }

    private void createTabTransactions() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.account_recyclerview);

        this.tab = new Tab(this, recyclerView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        Account account = DAOFactory.make().selectAccountByCustomerWithTransactions(Session.getCustomerId());

        fillTextViewSold(account);
        fillTabTransactions(account);
    }

    private void fillTextViewSold(Account account) {
        DecimalFormat df = new DecimalFormat("#.##");

        this.textViewSold.setText(String.valueOf(df.format(account.getSold())));
    }

    private void fillTabTransactions(Account account) {
        this.tab.clearItems();

        addTransactionsToTab(account);
    }

    private void addTransactionsToTab(Account account) {
        TabItemTransaction tabItemTransaction;
        Intent intent;
        for (Transaction transaction : account.getTransactions()) {
            tabItemTransaction = new TabItemTransaction();

            tabItemTransaction.setDate(String.valueOf(transaction.getDateRealization()));
            tabItemTransaction.setTitle(transaction.getTitle());
            tabItemTransaction.setValue(String.valueOf(transaction.getValue()));

            intent = new Intent(this, TransactionActivity.class);
            intent.putExtra(Extra.MODE, Extra.MODE_EDIT);
            intent.putExtra(Extra.TRANSACTION_ID, transaction.getId());
            tabItemTransaction.setIntent(intent);

            this.tab.addItem(tabItemTransaction);
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