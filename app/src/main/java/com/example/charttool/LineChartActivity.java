package com.example.charttool;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class LineChartActivity extends AppCompatActivity implements SeekBar.OnSeekBarChangeListener, OnChartValueSelectedListener {

    TextView txv_amount, txv_range;
    SeekBar sb_amount, sb_range;
    LineChart chart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_linechart);
        setTitle("走勢圖");
        initView();

    }

    private void initChart() {
        sb_amount.setProgress(10);
        sb_range.setProgress(10);

        chart.setTouchEnabled(true);//允許點觸事件
        chart.setDragDecelerationFrictionCoef(0.9f);//設定滑動速度

        // 允許拖動或縮放
        chart.setDragEnabled(true);
        chart.setScaleEnabled(true);
        chart.setDrawGridBackground(false);
        chart.setHighlightPerDragEnabled(true);

        // 設定背景顏色
        chart.setBackgroundColor(Color.LTGRAY);

        chart.animateX(1500);//x軸資料出現的速度

        //設定圖示說明區
        Legend legend = chart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setTextSize(10f);
        legend.setTextColor(Color.WHITE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);

//        //X軸的設定
        XAxis xAxis = chart.getXAxis();
//        xAxis.setTypeface(tfLight);
        xAxis.setTextSize(14f);
        xAxis.setTextColor(Color.BLACK);
        xAxis.setDrawGridLines(true);//是否要格線
        xAxis.setDrawAxisLine(true);
//
//        //y軸左邊的設定
        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setAxisMaximum(80f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);
        leftAxis.setGranularityEnabled(true);
//
//        //y軸右邊的設定
//        YAxis rightAxis = chart.getAxisRight();
//        rightAxis.setTypeface(tfLight);
//        rightAxis.setTextColor(Color.RED);
//        rightAxis.setAxisMaximum(900);
//        rightAxis.setAxisMinimum(-200);
//        rightAxis.setDrawGridLines(false);
//        rightAxis.setDrawZeroLine(false);
//        rightAxis.setGranularityEnabled(false);

    }

    public void setData(int count, int range) {

        ArrayList<Entry> dataList = new ArrayList<>();


        for (int i = 0; i < count; i++) {
            float valueY = (float) (Math.random() * range) + 50;
            dataList.add(new Entry(i, valueY));
        }
        //todo:要增加資料的話就繼續加list與set

        LineDataSet dataSet;

//        if(chart.getData() == null ){
        dataSet = new LineDataSet(dataList, "lineChart");

        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSet.setColor(Color.GREEN);//線條顏色
        dataSet.setCircleColor(Color.RED);//節點顏色
        dataSet.setLineWidth(2f);//線條粗細
        dataSet.setCircleRadius(3f);//節點大小
        dataSet.setFillAlpha(65);//透明度

//            dataSet.setHighLightColor(Color.rgb(244, 117, 117));
        dataSet.setDrawCircleHole(false);

        LineData lineData = new LineData(dataSet);
        lineData.setValueTextColor(Color.RED);
        lineData.setValueTextSize(10f);
        chart.setData(lineData);

//        }else {


//        }

    }

    private void initView() {
        txv_amount = findViewById(R.id.txvDataAmount);
        txv_range = findViewById(R.id.txvDataRange);
        sb_amount = findViewById(R.id.sb_amount);
        sb_range = findViewById(R.id.sb_range);
        sb_amount.setOnSeekBarChangeListener(this);
        sb_range.setOnSeekBarChangeListener(this);
        chart = findViewById(R.id.lineChart);
        chart.setOnChartValueSelectedListener(this);
        initChart();
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        txv_amount.setText(String.valueOf(sb_amount.getProgress()));
        txv_range.setText(String.valueOf(sb_range.getProgress()));

        setData(sb_amount.getProgress(), sb_range.getProgress());
        //有改變資料的話呼叫chart重畫
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
