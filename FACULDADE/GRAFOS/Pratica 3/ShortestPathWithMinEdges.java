import java.io.*;
import java.util.*;

// Arestas: com destino e peso
class Edge {
    int target;
    int weight;

    public Edge(int target, int weight) {
        this.target = target;
        this.weight = weight;
    }
}
// Vertices: com id, distância e número de arestas
class Vertex implements Comparable<Vertex> {
    int id;
    int distance;
    int edges;

    public Vertex(int id, int distance, int edges) {
        this.id = id;
        this.distance = distance;
        this.edges = edges;
    }

    @Override
    public int compareTo(Vertex other) {
        if (this.distance != other.distance) {
            return Integer.compare(this.distance, other.distance);
        }
        return Integer.compare(this.edges, other.edges);
    }
}

public class ShortestPathWithMinEdges {
    private List<List<Edge>> graph;
    private int[] distances;
    private int[] edgeCounts;
    private int[] predecessors;
    private int n;

    public ShortestPathWithMinEdges(int n) {
        this.n = n;
        graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
    }

    public void addEdge(int source, int target, int weight) {
        graph.get(source).add(new Edge(target, weight));
    }
    
    // Método para encontrar o caminho mais curto com o menor número de arestas
    // Usando Dijkstra modificado
    public void findShortestPath(int source, int target) {
        distances = new int[n];
        edgeCounts = new int[n];
        predecessors = new int[n];
        Arrays.fill(distances, Integer.MAX_VALUE);
        Arrays.fill(edgeCounts, Integer.MAX_VALUE);
        Arrays.fill(predecessors, -1);

        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        distances[source] = 0;
        edgeCounts[source] = 0;
        pq.add(new Vertex(source, 0, 0));

        while (!pq.isEmpty()) {
            Vertex current = pq.poll();

            if (current.id == target) break;
            if (current.distance > distances[current.id]) continue;

            for (Edge edge : graph.get(current.id)) {
                int newDistance = distances[current.id] + edge.weight;
                int newEdges = edgeCounts[current.id] + 1;

                if (newDistance < distances[edge.target] || 
                    (newDistance == distances[edge.target] && newEdges < edgeCounts[edge.target])) {
                    distances[edge.target] = newDistance;
                    edgeCounts[edge.target] = newEdges;
                    predecessors[edge.target] = current.id;
                    pq.add(new Vertex(edge.target, newDistance, newEdges));
                }
            }
        }

        if (distances[target] == Integer.MAX_VALUE) {
            System.out.println("Não existe caminho de " + source + " para " + target);
        } else {
            System.out.println("Comprimento do caminho mínimo: " + distances[target]);
            System.out.println("Número de arestas no caminho: " + edgeCounts[target]);
            System.out.print("Caminho: ");
            printPath(source, target);
            System.out.println();
        }
    }

    private void printPath(int source, int target) {
        if (target == source) {
            System.out.print(source);
        } else if (predecessors[target] == -1) {
            System.out.print("Não existe caminho");
        } else {
            printPath(source, predecessors[target]);
            System.out.print(" -> " + target);
        }
    }

    public static void main(String[] args) {
        // Chamada de ShortestPathWithMinEdges.main(args) para executar executar pela main
        if (args.length != 1) {
            System.out.println("Uso: java ShortestPathWithMinEdges <arquivo_de_entrada>");
            return;
        }

        String inputFile = args[0];
        try (Scanner scanner = new Scanner(new File(inputFile))) {
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            int source = scanner.nextInt();
            int target = scanner.nextInt();

            ShortestPathWithMinEdges sp = new ShortestPathWithMinEdges(n);

            for (int i = 0; i < m; i++) {
                int u = scanner.nextInt();
                int v = scanner.nextInt();
                int w = scanner.nextInt();
                sp.addEdge(u, v, w);
            }

            sp.findShortestPath(source, target);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + inputFile);
        }
    }
}