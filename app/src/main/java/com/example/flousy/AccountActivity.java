package com.example.flousy;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import flousy.beans.core.ListOperations;
import flousy.beans.core.Operation;
import flousy.db.DataAccessor;
import flousy.db.DBManager;
import flousy.gui.widget.recycler.tab.Tab;
import flousy.gui.widget.recycler.tab.TabItemOperation;
import flousy.gui.widget.recycler.tab.TabItemOperationTitle;
import flousy.session.Session;

public class AccountActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customGreen;

    private DataAccessor dao = DBManager.getDao();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview);

        setColor(getResources().getColor(ACTIVITY_COLOR));

        createTabOperations();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                startNewOperation();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void createTabOperations() {
        Tab tab = new Tab(this);

        RecyclerView tabView = (RecyclerView) findViewById(R.id.recyclerview);
        tab.adapt(tabView);

        fillTabOperations(tab);
    }

    private void fillTabOperations(Tab tab) {
        tab.addItem(new TabItemOperationTitle());

        long accountId = Long.parseLong(Session.getAccountId());
        ListOperations listOperations = this.dao.selectOperationsByAccount(accountId);

        TabItemOperation tabItemOperation;
        for (Operation operation : listOperations) {
            tabItemOperation = new TabItemOperation();

            tabItemOperation.setDate(String.valueOf(operation.getDate()));
            tabItemOperation.setName(operation.getName());
            tabItemOperation.setValue(String.valueOf(operation.getValue()));

            tab.addItem(tabItemOperation);
        }
    }

    private void startNewOperation() {

    }
}