import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

class Graph {
    private int V; // Número de vértices
    private Map<Integer, List<Integer>> adj; // Lista de adjacência

    public Graph(int V) {
        this.V = V;
        this.adj = new HashMap<>();
        for (int i = 1; i <= V; i++) {
            adj.put(i, new ArrayList<>());
        }
    }

    // Adiciona uma aresta ao grafo
    public void addEdge(int u, int v) {
        adj.get(u).add(v);
    }

    // Ordena as listas de adjacência em ordem lexicográfica
    public void sortAdjLists() {
        for (List<Integer> list : adj.values()) {
            Collections.sort(list);
        }
    }

    // Função de DFS para encontrar arestas de árvore
    private void DFS(int v, boolean[] visited, List<String> treeEdges) {
        visited[v] = true;

        for (int u : adj.get(v)) {
            if (!visited[u]) {
                treeEdges.add(v + " -> " + u); // Adiciona a aresta de árvore
                DFS(u, visited, treeEdges);
            }
        }
    }

    // Função para classificar as arestas divergentes de um vértice
    public void classifyEdges(int startVertex) {
        boolean[] visited = new boolean[V + 1];
        List<String> treeEdges = new ArrayList<>();

        DFS(startVertex, visited, treeEdges);

        // Classificar arestas divergentes do vértice escolhido
        System.out.println("Arestas divergentes do vértice " + startVertex + ":");
        for (int u : adj.get(startVertex)) {
            if (visited[u]) {
                System.out.println("Aresta de árvore: " + startVertex + " -> " + u);
            } else {
                System.out.println("Aresta de retorno: " + startVertex + " -> " + u);
            }
        }

        // Exibe as arestas de árvore encontradas
        System.out.println("\nArestas de árvore encontradas:");
        for (String edge : treeEdges) {
            System.out.println(edge);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicita o nome do arquivo e o vértice inicial
        System.out.print("Digite o nome do ARQUIVO: ");
        String filename = scanner.nextLine();
        System.out.print("Digite o vértice inicial: ");
        int startVertex = scanner.nextInt();

        try {
            File file = new File(filename);
            Scanner fileScanner = new Scanner(file);

            int n = fileScanner.nextInt(); // Número de vértices
            int m = fileScanner.nextInt(); // Número de arestas

            Graph g = new Graph(n);

            // Lê as arestas do arquivo
            for (int i = 0; i < m; i++) {
                int u = fileScanner.nextInt();
                int v = fileScanner.nextInt();
                g.addEdge(u, v);
            }

            g.sortAdjLists(); // Ordena as listas de adjacência
            g.classifyEdges(startVertex); // Classifica as arestas

            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.err.println("Erro ao abrir o arquivo.");
        } finally {
            scanner.close();
        }
    }
}