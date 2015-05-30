package com.diderot.android.flousy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * Created by simo on 21/05/2015.
 */
public class ChartActivity extends MotherActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chart_pie);
        piechart();
    }
    public void piechart() {


        String[] subjects = new String[]{"Nourriture", "Soins", "Transport","Loisirs","Mode"};
        double[] distribution = {43.5, 35.9, 12.3,28,120};
        int[] colors = {Color.parseColor("#FF6600"), Color.parseColor("#2C4762"), Color.parseColor("#0CBADF"),Color.GREEN,Color.RED};
        CategorySeries category = new CategorySeries("Studens subje");
        for (int i = 0; i < distribution.length; i++) {
            category.add(subjects[i], distribution[i]);
        }
        DefaultRenderer drenderer = new DefaultRenderer();
        for (int i = 0; i < distribution.length; i++) {
            SimpleSeriesRenderer renderer = new SimpleSeriesRenderer();

            renderer.setColor(colors[i]);
            renderer.setDisplayChartValues(true);



            drenderer.addSeriesRenderer(renderer);
        }
        drenderer.setApplyBackgroundColor(true);
        drenderer.setLabelsTextSize(50);
        drenderer.setLabelsColor(Color.BLACK);
        drenderer.setLegendTextSize(50);
        drenderer.setBackgroundColor(Color.parseColor("#FFCC33"));
        drenderer.setChartTitle("Répartition d'achat");
        drenderer.setChartTitleTextSize(125);
        drenderer.setZoomButtonsVisible(true);
        Intent in = ChartFactory.getPieChartIntent(this, category, drenderer, "piechartdemo");

        startActivity(in);
        finish();
    }



}
