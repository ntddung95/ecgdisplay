package com.example.android.bluetoothchat;

import android.graphics.Color;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

public class GraphECG {


    public static LineChart initChart(LineChart chart, String nameChart) {

        chart.getDescription().setEnabled(true);
        chart.getDescription().setText(nameChart);

        chart.setTouchEnabled(false);
        chart.setDragEnabled(false);
        chart.setScaleEnabled(false);
        chart.setDrawGridBackground(false);
        chart.setPinchZoom(false);
        chart.setAutoScaleMinMaxEnabled(true);
        chart.setBackgroundColor(Color.WHITE);

        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);
        chart.setData(data);

        // get the legend (only possible after setting data)
        Legend l = chart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
//        l.setTypeface(tfLight);
        l.setTextColor(Color.WHITE);

        XAxis xl = chart.getXAxis();
//        xl.setTypeface(tfLight);
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
//        xl.setAxisMaximum(50f);
        xl.setEnabled(true);

        YAxis leftAxis = chart.getAxisLeft();
//        leftAxis.setTypeface(tfLight);
        leftAxis.setTextColor(Color.WHITE);
//        leftAxis.setAxisMaximum(5000f);
//        leftAxis.setAxisMinimum(4000f);
        leftAxis.setDrawGridLines(true);


        YAxis rightAxis = chart.getAxisRight();
        rightAxis.setEnabled(false);
        return chart;
    }

    public static void addEntry(LineChart chart, float y){
        LineData data = chart.getData();

        if(data != null){
            ILineDataSet set = data.getDataSetByIndex(0);
            if(set == null){
                set = createSet();
                data.addDataSet(set);
            }

            float tmp = set.getEntryCount();
            data.addEntry(new Entry(set.getEntryCount(),y), 0);
            data.notifyDataChanged();
            chart.notifyDataSetChanged();
            chart.setVisibleXRangeMaximum(700);
            chart.setMaxVisibleValueCount(700);
            chart.moveViewToX(data.getEntryCount());

        }
    }

    private static LineDataSet createSet(){
        LineDataSet set = new LineDataSet(null, "Dynamic Data");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setLineWidth(0.7f);
        set.setHighlightEnabled(false);
        set.setDrawCircles(false);
        set.setDrawValues(false);
        set.setColor(Color.MAGENTA);
        set.setMode(LineDataSet.Mode.LINEAR);
        set.setCubicIntensity(3f);
        return set;
    }
}
