package com.example.charttool;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class BarChartActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, OnChartValueSelectedListener {

    BarChart chart;
    SeekBar sb_amount, sb_range;
    TextView txv_amount, txv_range;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_bar_chart);
        setTitle("長條圖");

        init();
        setData();


    }

    private void setData() {


        ArrayList<BarEntry> list = new ArrayList<>();

        for (int i = 0; i < sb_amount.getProgress(); i++) {
            float multi = sb_range.getProgress() + 1;
            float valueY = (float) (Math.random() * multi) + multi / 3;
            list.add(new BarEntry(i, valueY));

        }
        //長條圖的設定
        BarDataSet set;

        set = new BarDataSet(list, "barData");
        set.setColors(ColorTemplate.VORDIPLOM_COLORS);
        set.setDrawValues(false);

        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(set);

        //將bardata參數設定與資料放到圖表物件
        BarData barData = new BarData(dataSets);
        chart.setData(barData);
        chart.setFitBars(true);

    }


    private void init() {
        chart = findViewById(R.id.chart);
        chart.setPinchZoom(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);
        chart.animateY(2500);//設定長條圖出現的速度
        chart.getLegend().setEnabled(false);
        chart.getDescription().setEnabled(false);

        sb_amount = findViewById(R.id.sb_amount);
        sb_range = findViewById(R.id.sb_range);
        txv_amount = findViewById(R.id.txvDataAmount);
        txv_range = findViewById(R.id.txvDataRange);
        sb_amount.setOnSeekBarChangeListener(this);
        sb_range.setOnSeekBarChangeListener(this);
        chart.setOnChartValueSelectedListener(this);


        sb_amount.setProgress(10);
        sb_range.setProgress(15);

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        txv_amount.setText(String.valueOf(sb_amount.getProgress()));
        txv_range.setText(String.valueOf(sb_range.getProgress()));

        setData();
        chart.invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
    }

    @Override
    public void onValueSelected(Entry e, Highlight h) {
        //放大顯示時移動時的效果
        chart.centerViewToAnimated(e.getX(), e.getY(), chart.getData().getDataSetByIndex(h.getDataSetIndex())
                .getAxisDependency(), 500);
    }

    @Override
    public void onNothingSelected() {

    }
}
