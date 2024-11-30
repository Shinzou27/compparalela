package com.example.compparalela;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;

public class BarChart extends JFrame {

    public BarChart(double[] executionTimes) {
        setLayout(new BorderLayout());
        add(createChartPanel(executionTimes), BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private ChartPanel createChartPanel(double[] executionTimes) {
        CategoryDataset dataset = createDataset(executionTimes);
        JFreeChart chart = createChart(dataset);
        return new ChartPanel(chart);
    }

    private CategoryDataset createDataset(double[] executionTimes) {
        if (executionTimes.length != 3) {
            throw new IllegalArgumentException("O array deve ter exatamente 3 valores.");
        }

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        String[] methods = {"Serial", "Paralelo", "GPU"};

        for (int i = 0; i < methods.length; i++) {
            dataset.addValue(executionTimes[i], methods[i], "Tempo de Execução (ms)");
        }

        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        return ChartFactory.createBarChart(
                "Comparação de Métodos", // Título
                "Métodos",              // Label do eixo X
                "Tempo de Execução (ms)", // Label do eixo Y
                dataset
        );
    }
}
