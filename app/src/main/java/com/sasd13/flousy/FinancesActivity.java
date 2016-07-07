package com.sasd13.flousy;

import android.os.Bundle;

import com.sasd13.androidex.util.GUIHelper;

public class FinancesActivity extends MotherActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_settings);
        GUIHelper.colorTitles(this);
    }
}