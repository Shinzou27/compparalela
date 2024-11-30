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
    public static void writeWordCountCSV(String filePath, double[] tempos, int[] resultados, String palavra) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Cabeçalho do CSV
            writer.write("Método,Tempo de Execução (ms),Ocorrências da Palavra '" + palavra + "'");
            writer.newLine();

            // Adicionando dados de cada método
            writer.write("Serial," + (tempos[0] / 1e6) + "," + resultados[0]);
            writer.newLine();
            writer.write("Paralelo," + (tempos[1] / 1e6) + "," + resultados[1]);
            writer.newLine();
            writer.write("GPU," + (tempos[2] / 1e6) + "," + resultados[2]);
            writer.newLine();

            System.out.println("Arquivo CSV gerado em: " + filePath);
        } catch (IOException e) {
            System.err.println("Erro ao criar o arquivo CSV: " + e.getMessage());
        }
    }
}
