package com.example.charttool;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    //到走勢圖頁
    public void toLineChartPage(View view){
        startActivity(new Intent(this,LineChartActivity.class));
    }
    //到長條圖頁
    public void toBarChartPage(View view){
        startActivity(new Intent(this,BarChartActivity.class));
    }
    //到雷達圖頁
    public void toRadarChartPage(View view){
        startActivity(new Intent(this,RadarChartActivity.class));
    }
}
