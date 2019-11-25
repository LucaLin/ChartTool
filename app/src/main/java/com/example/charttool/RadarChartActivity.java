package com.example.charttool;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;

import java.util.ArrayList;

public class RadarChartActivity extends AppCompatActivity {

    private final String[] zones = new String[]{"1", "2", "3", "4", "5"};
    RadarChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_radar_chart);

        init();

    }

    private void init() {

        chart = findViewById(R.id.chart);
        chart.setBackgroundColor(Color.rgb(60, 65, 82));

        chart.getDescription().setEnabled(false);

        chart.setWebLineWidth(1f);
        chart.setWebColor(Color.LTGRAY);
        chart.setWebLineWidthInner(1f);
        chart.setWebColorInner(Color.LTGRAY);
        chart.setWebAlpha(100);

        MarkerView markerView = new RadarMarkerView(this,R.layout.item_radarmarker);
        markerView.setChartView(chart);//綁定指定的chart
        chart.setMarker(markerView);//chart設定要有marker

        setData();

        chart.animateXY(1400, 1400, Easing.EaseInOutQuad);

        //XY軸設定
        XAxis xAxis = chart.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return zones[(int) value % zones.length];
            }
        });
        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = chart.getYAxis();
        yAxis.setLabelCount(5,false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaxValue(80f);
        yAxis.setDrawLabels(false);
    }

    private void setData() {

        float value = 80;
        float min = 20;//基本值
        int count = 5;
        ArrayList<RadarEntry> list = new ArrayList<>();
        //todo:list可以有多條，藉以做不同時期的資料比較

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * value) + min;
            list.add(new RadarEntry(val));

        }

        RadarDataSet dataSet = new RadarDataSet(list, "radarChart");
        dataSet.setColor(Color.rgb(121, 162, 175));
        dataSet.setFillColor(Color.rgb(121, 162, 175));
        dataSet.setDrawFilled(true);
        dataSet.setFillAlpha(180);
        dataSet.setLineWidth(2f);
        dataSet.setDrawHighlightCircleEnabled(true);
        dataSet.setDrawHighlightIndicators(false);

        ArrayList<IRadarDataSet> radarDataSets = new ArrayList<>();
        radarDataSets.add(dataSet);

        RadarData data = new RadarData(radarDataSets);
        data.setValueTextSize(8f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        chart.setData(data);
        chart.invalidate();

    }
}
