package com.example.compparalela;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVWriter {

    public static void writeCSV(String filePath,
                                double[] bubbleTimesSerial,
                                double[] quickTimesSerial,
                                double[] mergeTimesSerial,
                                double[] selectionTimesSerial,
                                double[] bubbleTimesParallel,
                                double[] quickTimesParallel,
                                double[] mergeTimesParallel,
                                double[] selectionTimesParallel) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write("Execução,Bubble Sort Serial,Quick Sort Serial,Merge Sort Serial,Selection Sort Serial," +
                    "Bubble Sort Paralelo,Quick Sort Paralelo,Merge Sort Paralelo,Selection Sort Paralelo");
            writer.newLine();
            for (int i = 0; i < bubbleTimesSerial.length; i++) {
                writer.write((i + 1) + "," +
                        bubbleTimesSerial[i] + "," +
                        quickTimesSerial[i] + "," +
                        mergeTimesSerial[i] + "," +
                        selectionTimesSerial[i] + "," +
                        bubbleTimesParallel[i] + "," +
                        quickTimesParallel[i] + "," +
                        mergeTimesParallel[i] + "," +
                        selectionTimesParallel[i]);
                writer.newLine();
            }
            System.out.println("deu bom");
        } catch (IOException e) {
            System.err.println("Erro: " + e.getMessage());
        }
    }
}
