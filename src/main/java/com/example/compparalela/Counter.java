package com.example.compparalela;

import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Counter {
    private List<String> palavras;
    private String palavra;

    public Counter(List<String> palavras, String palavra) {
        this.palavras = palavras;
        this.palavra = palavra;
    }

    public int contarSerial() {
        int counter = 0;
        for (int i = 0; i < palavras.size(); i++) {
            String palavraAtual = palavras.get(i);
            if (palavraAtual.equalsIgnoreCase(palavra)) {
                counter++;
//                System.out.println(palavras.get(i-1) + " " + palavras.get(i) + " " + palavras.get(i+1));
            };
        }
        return counter;
    }

    public int contarParalelo() {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new CounterTask(palavras, palavra, 8));
    }

    public int contarComGPU() {
        CounterGPU counterGPU = new CounterGPU();
        return counterGPU.contarPalavrasGPU(palavras, palavra);
    }
}
