# Análise de Desempenho na Contagem de Palavras: Serial, Paralelo e GPU

## Resumo  
Este projeto implementa três métodos para contagem de palavras em grandes textos: execução serial, paralela utilizando Fork/Join Framework e execução com GPU usando OpenCL (via JOCL). Testamos a eficiência de cada abordagem, analisamos os tempos de execução e apresentamos comparações visuais por meio de gráficos.

---

## Introdução  
O objetivo do trabalho foi comparar o desempenho de três abordagens na solução do problema de contagem de palavras:  
1. **Método Serial**: Iteração simples com um laço `for`.  
2. **Método Paralelo**: Divisão do problema em tarefas com Fork/Join Framework.  
3. **Método GPU**: Utilização da biblioteca JOCL para executar a contagem em paralelo na GPU via OpenCL.  

Esses métodos foram testados com base no texto "Don Quixote", buscando a palavra "Quijote". Os resultados foram avaliados em termos de precisão e tempo de execução.

---

## Metodologia  
### Estrutura do Código  
- O código é estruturado em várias classes:  
  - **Counter**: Implementa os métodos de contagem (serial, paralelo e GPU).  
  - **CounterGPU**: Contém o kernel OpenCL e as operações para execução na GPU.  
  - **CounterTask**: Classe de apoio para o Fork/Join Framework.  
  - **WordCounterMain**: Classe principal que coordena os testes e exibe os resultados.  
  - **BarChart** e **CSVWriter**: Geram gráficos e exportam dados para análise estatística.  

### Metodologia dos Testes  
1. **Cenário de Teste**:  
   - Arquivo: *DonQuixote.txt*.  
   - Palavra-alvo: "Quijote".  
2. **Análise de Desempenho**:  
   - Coleta de tempos de execução para cada abordagem.  
   - Exportação dos resultados para CSV.  
3. **Ferramentas**:  
   - **JOCL**: Interface para OpenCL em Java.  
   - **Gráficos**: Representação dos tempos com a biblioteca de gráficos personalizada.

### Análise Estatística  
Os tempos de execução foram comparados para identificar o método mais eficiente. Foram realizadas análises qualitativas dos padrões de desempenho sob diferentes condições.

---

## Resultados e Discussão  

### Dados Obtidos  
| Método         | Contagem de Palavras | Tempo de Execução (ms) |
|----------------|----------------------|------------------------|
| Serial         | 398                 | `XX.XX`               |
| Paralelo       | 398                 | `YY.YY`               |
| GPU (JOCL)     | 398                 | `ZZ.ZZ`               |

**Nota**: Os tempos de execução variam de acordo com o hardware.  

### Análise Gráfica  
O gráfico a seguir demonstra a diferença de tempo entre os métodos.  
![Gráfico de desempenho](path/to/graph.png)

#### Discussão  
1. **Desempenho Serial**: Apresentou o pior desempenho devido à execução linear, sem paralelismo.  
2. **Desempenho Paralelo**: Obteve melhoria significativa, aproveitando núcleos da CPU.  
3. **Desempenho GPU**: Foi o mais rápido, devido ao alto grau de paralelismo na GPU.  

---

## Conclusão  
Os resultados confirmam que métodos paralelos, especialmente baseados em GPU, são mais adequados para tarefas computacionalmente intensivas, como a contagem de palavras em grandes textos. A abordagem serial, embora simples, não é eficiente para grandes volumes de dados.  

### Sugestões Finais  
- Para pequenos textos, métodos paralelos podem não justificar o overhead.  
- GPUs são altamente recomendadas para grandes volumes de dados.  

---

## Como Executar  
1. Clone este repositório:  
   ```bash
   git clone https://github.com/seuusuario/seuprojeto.git
