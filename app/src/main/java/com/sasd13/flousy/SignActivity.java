package com.sasd13.flousy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.sasd13.androidex.gui.widget.recycler.Recycler;
import com.sasd13.androidex.gui.widget.recycler.RecyclerFactoryType;
import com.sasd13.androidex.util.GUIHelper;
import com.sasd13.androidex.util.RecyclerHelper;
import com.sasd13.flousy.content.form.SignForm;
import com.sasd13.flousy.content.handler.SignHandler;

public class SignActivity extends AppCompatActivity {

    private SignForm signForm;
    private SignHandler signHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_sign);
        GUIHelper.colorTitles(this);

        signForm = new SignForm(this);
        signHandler = new SignHandler(this);

        buildSignView();
    }

    private void buildSignView() {
        Recycler form = RecyclerHelper.produce(RecyclerFactoryType.FORM, (RecyclerView) findViewById(R.id.sign_recyclerview));
        form.addDividerItemDecoration();

        RecyclerHelper.addAll(form, signForm.getHolder());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_sign, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_sign_action_done:
                sign();
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }

    private void sign() {
        signHandler.sign(signForm);
    }
}