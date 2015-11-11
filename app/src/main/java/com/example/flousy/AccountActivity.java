package com.example.flousy;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import flousy.beans.core.Account;
import flousy.beans.core.Operation;
import flousy.constant.Extra;
import flousy.db.DataAccessorFactory;
import flousy.gui.widget.recycler.tab.Tab;
import flousy.gui.widget.recycler.tab.TabItemOperation;
import flousy.gui.widget.recycler.tab.TabItemOperationTitle;
import flousy.session.Session;

public class AccountActivity extends MotherActivity {

    private TextView textViewSold;
    private Tab tab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_account);

        createTextViewSold();
        createTabOperations();
    }

    @Override
    protected void onStart() {
        super.onStart();

        Account account = DataAccessorFactory.get().selectAccountByUserWithOperations(Session.getUserId());

        fillTextViewSold(account);
        fillTabOperations(account);
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

    private void createTextViewSold() {
        this.textViewSold = (TextView) findViewById(R.id.account_textview_sold);
    }

    private void createTabOperations() {
        this.tab = new Tab(this);

        RecyclerView tabView = (RecyclerView) findViewById(R.id.account_recyclerview);
        this.tab.adapt(tabView);
    }

    private void fillTextViewSold(Account account) {
        this.textViewSold.setText(String.valueOf(account.getSold()));
    }

    private void fillTabOperations(Account account) {
        this.tab.clearItems();

        this.tab.addItem(new TabItemOperationTitle());

        TabItemOperation tabItemOperation;
        Intent intent;
        for (Operation operation : account.getListOperations()) {
            tabItemOperation = new TabItemOperation();

            tabItemOperation.setDate(String.valueOf(operation.getDate()));
            tabItemOperation.setName(operation.getName());
            tabItemOperation.setValue(String.valueOf(operation.getValue()));

            intent = new Intent(this, OperationActivity.class);
            intent.putExtra(Extra.MODE, Extra.MODE_EDIT);
            intent.putExtra(Extra.OPERATION_ID, operation.getId());
            tabItemOperation.setIntent(intent);

            this.tab.addItem(tabItemOperation);
        }
    }

    private void newOperation() {
        Intent intent = new Intent(this, OperationActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Extra.MODE, Extra.MODE_NEW);

        startActivity(intent);
    }
}