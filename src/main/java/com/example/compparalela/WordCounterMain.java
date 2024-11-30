package com.example.compparalela;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class WordCounterMain {
    public static void main(String[] args) {
        String nomeArquivo = "DonQuixote.txt";
        String palavra = "Quijote";
        String csvFilePath = "resultados.csv"; // Caminho do arquivo CSV

        List<String> palavras = carregarPalavras(nomeArquivo);
//        imprimirLista(palavras);
        Counter counter = new Counter(palavras, palavra);

        long inicio = System.nanoTime();
        int contagemSerial = counter.contarSerial();
        long tempoSerial = System.nanoTime() - inicio;

        inicio = System.nanoTime();
        int contagemParalela = counter.contarParalelo();
        long tempoParalelo = System.nanoTime() - inicio;

        inicio = System.nanoTime();
        int contagemGPU = counter.contarComGPU();
        long tempoGPU = System.nanoTime() - inicio;

        System.out.println("Contagem Serial: " + contagemSerial + " | Tempo: " + tempoSerial/1e6 + " ms");
        System.out.println("Contagem Paralela: " + contagemParalela + " | Tempo: " + tempoParalelo/1e6 + " ms");
        System.out.println("Contagem GPU: " + contagemGPU + " | Tempo: " + tempoGPU/1e6 + " ms");
        double[] tempos = {tempoSerial, tempoParalelo, tempoGPU};
        int[] resultados = {contagemSerial, contagemParalela, contagemGPU};
        CSVWriter.writeWordCountCSV(csvFilePath, tempos, resultados, palavra);
        BarChart chart = new BarChart(tempos);
        chart.pack();
        chart.setLocationRelativeTo(null);
        chart.setVisible(true);
    }

    public static List<String> carregarPalavras(String nomeArquivo) {
        try {
            String conteudo = new String(Files.readAllBytes(Paths.get(nomeArquivo)));
            return Arrays.stream(conteudo.split("\\s+"))
                    .map(palavra -> palavra.replaceAll("[^a-zA-ZáéíóúÁÉÍÓÚñÑüÜ]", "")) // Remove símbolos
                    .filter(palavra -> !palavra.isEmpty()) // Remove strings vazias
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public static void imprimirLista(List<String> lista) {
        for (String item : lista) {
            System.out.println(item);
        }
    }

}
