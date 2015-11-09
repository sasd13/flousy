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
import flousy.gui.widget.recycler.tab.TabItemSpend;
import flousy.gui.widget.recycler.tab.TabItemSpendTitle;
import flousy.session.Session;

public class AccountActivity extends MotherActivity {

    public static final int ACTIVITY_COLOR = R.color.customGreen;

    private DataAccessor dao = DBManager.getDao();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recyclerview);

        setColor(getResources().getColor(ACTIVITY_COLOR));

        createTabSpends();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void createTabSpends() {
        Tab tab = new Tab(this);

        RecyclerView tabView = (RecyclerView) findViewById(R.id.recyclerview);
        tab.adapt(tabView);

        fillTab(tab);
    }

    private void fillTab(Tab tab) {
        tab.addItem(new TabItemSpendTitle());

        long accountId = Long.parseLong(Session.getAccountId());
        ListOperations listOperations = this.dao.selectOperationsByAccount(accountId);

        TabItemSpend tabItemSpend;
        for (Operation operation : listOperations) {
            tabItemSpend = new TabItemSpend();

            tabItemSpend.setDate(String.valueOf(operation.getDate().toString()));
            tabItemSpend.setName(operation.getName());
            tabItemSpend.setValue(String.valueOf(operation.getValue()));

            tab.addItem(tabItemSpend);
        }
    }
}