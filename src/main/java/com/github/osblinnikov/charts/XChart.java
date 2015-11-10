package com.github.osblinnikov.charts;

import neuron.ChartsData;
import org.knowm.xchart.Chart;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;

import java.util.List;

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
        Chart chart = QuickChart.getChart(title, xTitle, yTitle, yTitle+"("+xTitle+")", xData, yData);

        // Show it
        new SwingWrapper(chart).displayChart();

    }
}
