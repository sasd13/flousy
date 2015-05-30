package com.diderot.android.flousy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import flousy.gui.content.Menu;

/**
 * Created by simo on 28/05/2015.
 */
public class LineChartActivity extends MotherActivity{

    String[] mMonth={"Jan","Fev","Mar","Avril","Mai","Juin","Juil",
                        "Aout","Sep","Oct","Nov","Dec"};
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.line_chart);
        openChart();
    }
protected void openChart() {


    int[] x = {1, 2, 3, 4, 5, 6, 7, 8};
    int[] Bus = {2500, 3500, 2900, 2700, 3800, 3500, 3900, 3200};
    int[] Taxi = {2200, 2900, 2800, 3100, 2500, 3300, 3400, 3100};

    XYSeries series = new XYSeries("Anneé precedente");
    XYSeries series1 = new XYSeries("Anneé suivante");

    for (int i = 0; i < x.length; i++) {
        series.add(x[i], Bus[i]);
        series1.add(x[i], Taxi[i]);
    }

    XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
    dataset.addSeries(series);
    dataset.addSeries(series1);

    XYSeriesRenderer renderer1 = new XYSeriesRenderer();

    renderer1.setColor(Color.parseColor("#DD4B39"));
    renderer1.setPointStyle(PointStyle.CIRCLE);
    renderer1.setFillPoints(true);
    renderer1.setLineWidth(2);
    renderer1.setDisplayChartValues(true);
    renderer1.setChartValuesTextSize(50);

    XYSeriesRenderer renderer2 = new XYSeriesRenderer();

    renderer2.setColor(Color.parseColor("#335D30"));
    renderer2.setPointStyle(PointStyle.CIRCLE);
    renderer2.setFillPoints(true);
    renderer2.setLineWidth(2);
    renderer2.setDisplayChartValues(true);
    renderer2.setChartValuesTextSize(50);

    XYMultipleSeriesRenderer multipleSeriesRenderer = new XYMultipleSeriesRenderer();
    multipleSeriesRenderer.setXLabels(0);
    multipleSeriesRenderer.setChartTitle("Comparatif de dépenses");
    multipleSeriesRenderer.setYTitle("Année");
    multipleSeriesRenderer.setYTitle("montant");
    multipleSeriesRenderer.setZoomButtonsVisible(true);

    for (int i = 0; i < x.length; i++) {
        multipleSeriesRenderer.addXTextLabel(i+1, mMonth[i]);
    }
    multipleSeriesRenderer.setApplyBackgroundColor(true);
    multipleSeriesRenderer.setBackgroundColor(Color.BLACK);
    multipleSeriesRenderer.addSeriesRenderer(renderer1);
    multipleSeriesRenderer.addSeriesRenderer(renderer2);
    multipleSeriesRenderer.setLegendTextSize(5);
    multipleSeriesRenderer.setPointSize(10);
    multipleSeriesRenderer.setLabelsTextSize(50);
    multipleSeriesRenderer.setChartTitleTextSize(90);
    multipleSeriesRenderer.setAxisTitleTextSize(50);

    Intent intent = ChartFactory.getLineChartIntent(getBaseContext(), dataset, multipleSeriesRenderer);
    startActivity(intent);
    finish();
}

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
       getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }
}
