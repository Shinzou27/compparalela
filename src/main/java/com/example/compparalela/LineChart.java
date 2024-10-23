package com.example.compparalela;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.swing.*;
import java.awt.*;

public class LineChart extends JFrame {

    public LineChart(double[] bubbleTimesSerial, double[] quickTimesSerial,
                     double[] mergeTimesSerial, double[] selectionTimesSerial,
                     double[] bubbleTimesParallel, double[] quickTimesParallel,
                     double[] mergeTimesParallel, double[] selectionTimesParallel) {

        setLayout(new GridLayout(2, 2));
        add(createChartPanel("Bubble Sort", bubbleTimesSerial, bubbleTimesParallel));
        add(createChartPanel("Quick Sort", quickTimesSerial, quickTimesParallel));
        add(createChartPanel("Merge Sort", mergeTimesSerial, mergeTimesParallel));
        add(createChartPanel("Selection Sort", selectionTimesSerial, selectionTimesParallel));
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private ChartPanel createChartPanel(String title, double[] serialTimes, double[] parallelTimes) {
        XYDataset dataset = createDataset(title, serialTimes, parallelTimes);
        JFreeChart chart = createChart(dataset, title);
        return new ChartPanel(chart);
    }

    private XYDataset createDataset(String title, double[] serialTimes, double[] parallelTimes) {
        XYSeriesCollection dataset = new XYSeriesCollection();
        XYSeries seriesSerial = new XYSeries("Serial");
        XYSeries seriesParallel = new XYSeries("Paralelo");
        for (int i = 0; i < serialTimes.length; i++) {
            seriesSerial.add(i + 1, serialTimes[i]);
            seriesParallel.add(i + 1, parallelTimes[i]);
        }
        dataset.addSeries(seriesSerial);
        dataset.addSeries(seriesParallel);

        return dataset;
    }

    private JFreeChart createChart(XYDataset dataset, String title) {
        return ChartFactory.createXYLineChart(
                title + " - Tempo de Execução",
                "Núm. da execução",
                "Tempo (s)",
                dataset
        );
    }
}
