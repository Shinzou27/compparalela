# Análise de Desempenho na Contagem de Palavras: Serial, Paralelo e GPU

## Resumo  
Este projeto implementa três métodos para contagem de palavras em grandes textos: execução serial, paralela utilizando ForkJoin  e execução com GPU usando OpenCL (via JOCL). Testou-se a eficiência de cada abordagem, analisou-se os tempos de execução e gerou-se representações visuais por meio de gráficos.

---

## Introdução  
O objetivo do trabalho foi comparar o desempenho de três abordagens na solução do problema de contagem de palavras:  
1. **Método Serial**: Iteração simples com um laço `for`.  
2. **Método Paralelo**: Divisão do problema em tarefas com ForkJoin.  
3. **Método GPU**: Utilização da biblioteca JOCL para executar a contagem em paralelo na GPU via OpenCL.  

Esses métodos foram testados, inicialmente, com base no texto "Don Quixote", buscando a palavra "Quijote". Os resultados foram avaliados em termos de tempo de execução.

---

## Metodologia  
### Estrutura do Código  
- O código é estruturado em várias classes:  
  - **Counter**: Implementa os métodos de contagem (serial, paralelo e GPU).  
  - **CounterGPU**: Contém o kernel OpenCL e as operações para execução na GPU.  
  - **CounterTask**: Classe de apoio para a contagem paralela.  
  - **WordCounterMain**: Classe main que inicia os testes e exibe os resultados.  
  - **BarChart** e **CSVWriter**: Geram gráficos e exportam dados em .csv.  

### Metodologia  
Análise estatística dos resultados obtidos para identificar padrões de desempenho e comparar os algoritmos sob diferentes condições.

---

## Resultados e Discussão  
Os tempos de execução foram comparados para identificar o método mais eficiente. Foram realizadas análises qualitativas dos padrões de desempenho sob diferentes condições.

### Dados Obtidos  
| Método         | Contagem de Palavras | Tempo de Execução (ms) |
|----------------|----------------------|------------------------|
| Serial         | 2245                 | `10,1645`               |
| Paralelo       | 2245                 | `18,6987`               |
| GPU            | 2232                 | `193,9172`              |


#### Discussão  
1. **Desempenho Serial**: 
2. **Desempenho Paralelo**: 
3. **Desempenho GPU**: 

---

## Conclusão  
Para pequenos textos, métodos paralelos podem não justificar o overhead.  

---

Link do repositório: https://github.com/shinzou27/compparalela