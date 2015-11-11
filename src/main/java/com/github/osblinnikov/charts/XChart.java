package com.github.osblinnikov.charts;

import neuron.ChartsData;
import org.knowm.xchart.*;

import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Created by oleg on 09.11.15.
 */
public class XChart {
    public static void main(String[] args){
        ChartsData cd = new ChartsData();
        cd.run();
    }
    public static void createChart(List<Double> x, List<Double> y, String xTitle, String yTitle, String title){
        double[] xData = new double[x.size()];
        double[] yData = new double[y.size()];
        int i=0;
        for(double n : x){
            xData[i++] = n;
        }
        i=0;
        for(double n : y){
            yData[i++] = n;
        }

        // Create Chart
        Chart chart = QuickChart.getChart(title, xTitle, yTitle, yTitle + "(" + xTitle + ")", xData, yData);
        for(Map.Entry<String,Series> s : chart.getSeriesMap().entrySet()){
            Series series = s.getValue();
            series.setLineColor(SeriesColor.BLUE);
            series.setMarkerColor(Color.ORANGE);
            series.setMarker(SeriesMarker.CIRCLE);
            series.setLineStyle(SeriesLineStyle.SOLID);
        }


        // Show it
        new SwingWrapper(chart).displayChart();

    }
}
