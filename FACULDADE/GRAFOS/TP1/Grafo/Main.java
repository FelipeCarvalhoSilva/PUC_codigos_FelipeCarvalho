package Grafo;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        int[] tamanhos = { 100 }; // Numero de vertices para teste

        for (int vertices : tamanhos) {
            int arestas = vertices*2; // Número de arestas proporcional aos vértices; diminuir quando tiver muitos vértices

            System.out.println("\n=== Testando grafo com " + vertices + " vértices e " + arestas + " arestas ===");

            // Gerar grafo e imprimir conectividade
            Graph g = GeradorGrafo.gerarGrafoAleatorio(vertices, arestas); //gerar grafo euleriano
            // Graph g = GeradorGrafo.gerarGrafoEuleriano(10, 20); //gerar grafo euleriano
            Graph salva = g.clone();

            System.out.println("\nGrafo gerado:");
            g.printGraph(); //Printa o grafo gerado
            System.out.println("Conectividade: " + (g.isConnected() ? "Conexo" : "Desconexo"));

            FleuryAlgorithm fleuryNaive = new FleuryAlgorithm(g);
            System.out.println("Tipo do grafo (Naive): " + fleuryNaive.tipoEuleriano());
            long startNaive = System.nanoTime();
            var caminhoNaive = fleuryNaive.encontrarCaminhoEuleriano(false);
            long endNaive = System.nanoTime();
            /*
             * System.out.println("\nPontes encontradas com Naïve:");
             * for (int[] bridge : bridgesNaive) {
             * System.out.println(bridge[0] + " - " + bridge[1]);
             * }
             */
            System.out.printf("Naive - Tempo: %.2f ms | Caminho Euleriano: %s%n",
                    (endNaive - startNaive) / 1e6,
                    (caminhoNaive != null ? "Existe" : "Não existe"));

            // Regenerar o grafo para garantir igualdade de condições
            g = salva;
            FleuryAlgorithm fleuryTarjan = new FleuryAlgorithm(g);
            System.out.println("Tipo do grafo (Tarjan): " + fleuryTarjan.tipoEuleriano());
            long startTarjan = System.nanoTime();
            List<int[]> bridgesTarjan = g.findBridgesTarjan();
            var caminhoTarjan = fleuryTarjan.encontrarCaminhoEuleriano(true);
            long endTarjan = System.nanoTime();
            System.out.printf("Tarjan - Tempo: %.2f ms | Caminho Euleriano: %s%n",
            (endTarjan - startTarjan) / 1e6,
            (caminhoTarjan != null ? "Existe" : "Não existe"));
    
    if (caminhoTarjan != null) {
        System.out.print("Caminho: ");
        for (int i = caminhoTarjan.size() - 1; i >= 0; i--) {
            System.out.print(caminhoTarjan.get(i));
            if (i != 0) System.out.print(" -> ");
        }
        System.out.println();
    }
    System.out.print("Pontes: \n");
            for (int[] bridge : bridgesTarjan) {
                System.out.println(bridge[0] + " - " + bridge[1]);
            }

        }
    }
}
