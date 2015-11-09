package com.github.osblinnikov.charts;

import com.googlecode.charts4j.*;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static com.googlecode.charts4j.Color.*;

/**
 * Created by oleg on 08.11.15.
 */
public class Chart4j {

    public static double max(List<Double> x){
        double m = Double.MIN_VALUE;
        for(double v : x){
            if(v > m){
                m = v;
            }
        }
        return m;
    }

    public static double min(List<Double> x){
        double m = Double.MAX_VALUE;
        for(double v : x){
            if(v < m){
                m = v;
            }
        }
        return m;
    }

    public static String createChartUrl(List<Double> x, List<Double> y, String title){
//        System.out.println(Arrays.toString(x.toArray()));
//        System.out.println(Arrays.toString(y.toArray()));
        System.out.println(min(x)+" "+max(x)+" "+min(y)+" "+max(y));
        XYLine line1 = Plots.newXYLine(DataUtil.scaleWithinRange(min(x),max(x),x), DataUtil.scaleWithinRange(min(y),max(y),y));
        line1.setLineStyle(LineStyle.newLineStyle(3, 1, 0));
        line1.setFillAreaColor(LIGHTGREEN);


        // Defining chart.
        XYLineChart chart = GCharts.newXYLineChart(line1);
        chart.setSize(600, 450);
        chart.setTitle(title, WHITE, 14);
//
        // Defining axis info and styles
        AxisStyle axisStyle = AxisStyle.newAxisStyle(WHITE, 12, AxisTextAlignment.CENTER);
        AxisLabels yAxis = AxisLabelsFactory.newNumericRangeAxisLabels(min(y), max(y));
        yAxis.setAxisStyle(axisStyle);
        AxisLabels xAxis2 = AxisLabelsFactory.newNumericRangeAxisLabels(min(x), max(x));
        xAxis2.setAxisStyle(axisStyle);

        // Adding axis info to chart.
        chart.addYAxisLabels(yAxis);
        chart.addXAxisLabels(xAxis2);
        chart.setGrid(100, 6.78, 5, 0);

        // Defining background and chart fills.
        chart.setBackgroundFill(Fills.newSolidFill(BLACK));
        chart.setAreaFill(Fills.newSolidFill(Color.newColor("708090")));
        String url = chart.toURLString();
        // EXAMPLE CODE END. Use this url string in your web or
        // Internet application.
        return url;
    }
}
