package com.diderot.android.flousy;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

/**
 * Created by simo on 28/05/2015.
 */
public class BarActivity extends MotherActivity {
    LinearLayout la;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bar_pie);
        la=(LinearLayout)findViewById(R.id.bar);
        barChart();

    }
    public void barChart() {
        int color[] = {2, 2, 2, 1, 1, 3, 3, 3, 3};
        int height[] = {100, 100, 100, 200, 300, 300, 300, 300};

        for (int j = 0; j < color.length; j++) {
            drawchart(1, color[j], height[j]);

        }
    }
    private void drawchart(int count,int color,int height){
        System.out.println(count+color+height);
        if(color==1){
            color=Color.BLUE;
        }
        if(color==2){
            color=Color.YELLOW;
        }
        if(color==1){
            color=Color.GREEN;
        }
        for (int k=1 ; k<=count;k++){
            View view = new View(this);
            view.setBackgroundColor(color);

            view.setLayoutParams(new LinearLayout.LayoutParams(30,height));
            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams)view.getLayoutParams();
            params.setMargins(3,0,0,0);
            view.setLayoutParams(params);
            la.addView(view);
        }


    }

}
