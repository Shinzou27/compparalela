package com.example.compparalela;

import javax.swing.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int threads = 5;
        Sort sort = new Sort(threads);

//        int[] sizes = {10000, 20000, 30000, 40000, 50000};
        int[] sizes = {10000, 20000, 30000, 40000, 50000, 60000, 70000, 80000, 90000, 100000, 110000, 120000, 130000, 140000, 150000, 160000, 170000, 180000, 190000, 200000, 210000, 220000, 230000, 240000, 250000, 260000, 270000, 280000, 290000, 300000};

        double[] bubbleTimesSerial = new double[sizes.length];
        double[] quickTimesSerial = new double[sizes.length];
        double[] mergeTimesSerial = new double[sizes.length];
        double[] selectionTimesSerial = new double[sizes.length];

        double[] bubbleTimesParallel = new double[sizes.length];
        double[] quickTimesParallel = new double[sizes.length];
        double[] mergeTimesParallel = new double[sizes.length];
        double[] selectionTimesParallel = new double[sizes.length];

        for (int i = 0; i < sizes.length; i++) {
            int[] array = new int[sizes[i]];
            Random random = new Random();
            for (int j = 0; j < array.length; j++) {
                array[j] = random.nextInt(10000); 
            }
            long startTime = System.nanoTime();
            long endTime = System.nanoTime();
           sort.bubbleSort(array.clone(), false);
           bubbleTimesSerial[i] = (endTime - startTime) / 1_000_000_000.0;
           System.out.println("Terminei o bubble S para o array " + (i+1));
           startTime = System.nanoTime();
           sort.quickSort(array.clone(), false);
           endTime = System.nanoTime();
           quickTimesSerial[i] = (endTime - startTime) / 1_000_000_000.0;
           System.out.println("Terminei o quick S para o array " + (i+1));
           startTime = System.nanoTime();
           sort.mergeSort(array.clone(),  false);
           endTime = System.nanoTime();
           mergeTimesSerial[i] = (endTime - startTime) / 1_000_000_000.0;
           System.out.println("Terminei o merge S para o array " + (i+1));
           startTime = System.nanoTime();
           sort.selectionSort(array.clone(), false);
           endTime = System.nanoTime();
           selectionTimesSerial[i] = (endTime - startTime) / 1_000_000_000.0;
           System.out.println("Terminei o selection S para o array " + (i+1));
            startTime = System.nanoTime();
            sort.bubbleSort(array.clone(), true);
            endTime = System.nanoTime();
            bubbleTimesParallel[i] = (endTime - startTime) / 1_000_000_000.0;
            System.out.println("Terminei o bubble P para o array " + (i+1));
            startTime = System.nanoTime();
            sort.quickSort(array.clone(),  true);
            endTime = System.nanoTime();
            quickTimesParallel[i] = (endTime - startTime) / 1_000_000_000.0;
            System.out.println("Terminei o quick P para o array " + (i+1));
            startTime = System.nanoTime();
            sort.mergeSort(array.clone(),  true);
            endTime = System.nanoTime();
            mergeTimesParallel[i] = (endTime - startTime) / 1_000_000_000.0;
            System.out.println("Terminei o merge P para o array " + (i+1));
            startTime = System.nanoTime();
            sort.selectionSort(array.clone(), true);
            endTime = System.nanoTime();
            selectionTimesParallel[i] = (endTime - startTime) / 1_000_000_000.0;
            System.out.println("Terminei o selection P para o array " + (i+1));
        }
        LineChart chart = new LineChart(bubbleTimesSerial, quickTimesSerial, mergeTimesSerial, selectionTimesSerial, bubbleTimesParallel, quickTimesParallel, mergeTimesParallel, selectionTimesParallel);
        chart.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        chart.pack();
        chart.setLocationRelativeTo(null);
        chart.setVisible(true);
        String csvFilePath = "tempos_execucao.csv";
        CSVWriter.writeCSV(csvFilePath, bubbleTimesSerial, quickTimesSerial, mergeTimesSerial, selectionTimesSerial, bubbleTimesParallel, quickTimesParallel, mergeTimesParallel, selectionTimesParallel);
    }
}
