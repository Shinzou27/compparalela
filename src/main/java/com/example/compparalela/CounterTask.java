package com.example.compparalela;

import java.util.List;
import java.util.concurrent.RecursiveTask;

public class CounterTask extends RecursiveTask<Integer> {
    private int limite;
    private List<String> palavras;
    private String palavraParaContar;

    public CounterTask(List<String> palavras, String palavraParaContar, int numeroThreads) {
        this.palavras = palavras;
        this.palavraParaContar = palavraParaContar;
        this.limite = Math.max(1, palavras.size() / numeroThreads);
    }

    @Override
    protected Integer compute() {
        if (palavras.size() <= limite) {
            int counter = 0;
            for (int i = 0; i < palavras.size(); i++) {
                String palavraAtual = palavras.get(i);
                if (palavraAtual.equalsIgnoreCase(palavraParaContar)) {
                    counter++;
//                    System.out.println(palavras.get(i-1) + " " + palavras.get(i) + " " + palavras.get(i+1));
                };
            }
            return counter;
        } else {
            int meio = palavras.size() / 2;
            CounterTask task1 = new CounterTask(palavras.subList(0, meio), palavraParaContar, limite);
            CounterTask task2 = new CounterTask(palavras.subList(meio, palavras.size()), palavraParaContar, limite);

            task1.fork();
            return task2.compute() + task1.join();
        }
    }
}
