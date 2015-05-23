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


        String[] subjects = new String[]{"math", "phisic", "science"};
        double[] distribution = {43.5, 35.9, 12.3};
        int[] colors = {Color.BLUE, Color.GREEN, Color.RED};
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
        drenderer.setLabelsTextSize(50);
        drenderer.setLabelsColor(Color.BLUE);
        drenderer.setLegendTextSize(50);
        drenderer.setChartTitle("piechart Exemple");
        drenderer.setChartTitleTextSize(125);
        drenderer.setZoomButtonsVisible(true);
        Intent in = ChartFactory.getPieChartIntent(this, category, drenderer, "piechartdemo");

        startActivity(in);
        finish();
    }
}
