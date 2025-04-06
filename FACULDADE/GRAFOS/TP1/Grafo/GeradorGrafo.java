package Grafo;

import java.util.*;

// Classe principal para gerar um grafo aleatório e encontrar pontes
class Graph {

    private int V; // Número de vértices
    private List<List<Integer>> adj; // Lista de adjacência

    public Graph(int V) {
        this.V = V;
        adj = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj.add(new ArrayList<>()); // Inicializa a lista de adjacência para cada vértice
        }
    }

    public Graph clone() {
        Graph copia = new Graph(this.V);
        for (int i = 0; i < V; i++) {
            copia.adj.set(i, new ArrayList<>(this.adj.get(i)));
        }
        return copia;
    }

    public int getV() {
        return V;
    }

    public List<List<Integer>> getAdj() {
        return adj;
    }

    public List<Integer> getAdj(int v) {
        return adj.get(v);
    }

    public void addEdge(int u, int v) {
        adj.get(u).add(v); // Adiciona uma aresta de u para v
        adj.get(v).add(u); // Grafo é não direcionado, adiciona de v para u também
    }

    public void removeEdge(int u, int v) {
        adj.get(u).remove(Integer.valueOf(v));
        adj.get(v).remove(Integer.valueOf(u));
    }

    public void printGraph() {
        for (int i = 0; i < V; i++) {
            System.out.print(i + ": ");
            for (int neighbor : adj.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public boolean isConnected() {
        boolean[] visited = new boolean[V];
        int start = -1;

        // Verifica se há algum vértice isolado (sem nenhuma aresta)
        for (int i = 0; i < V; i++) {
            if (adj.get(i).isEmpty()) {
                return false; // Vértice isolado → grafo não é conectado
            }
        }

        // Encontra um vértice com pelo menos uma aresta
        for (int i = 0; i < V; i++) {
            if (!adj.get(i).isEmpty()) {
                start = i;
                break;
            }
        }
        if (start == -1)
            return true; // Grafo vazio é considerado conectado

        dfs(start, visited); // Realiza busca em profundidade

        for (int i = 0; i < V; i++) {
            if (!adj.get(i).isEmpty() && !visited[i])
                return false; // Se algum vértice com arestas não foi visitado, o grafo não é conectado
        }
        return true;
    }

    private boolean isConnectedForNaive() {
        boolean[] visited = new boolean[V];

        // Encontra um vértice com pelo menos uma aresta
        int start = -1;
        for (int i = 0; i < V; i++) {
            if (!adj.get(i).isEmpty()) {
                start = i;
                break;
            }
        }

        // Grafo sem arestas é considerado conectado
        if (start == -1)
            return true;

        // Realiza DFS a partir do vértice encontrado
        dfs(start, visited);

        // Verifica se todos os vértices com pelo menos uma aresta foram visitados
        for (int i = 0; i < V; i++) {
            if (!adj.get(i).isEmpty() && !visited[i]) {
                return false;
            }
        }

        return true;
    }

    private void dfs(int v, boolean[] visited) {
        visited[v] = true;
        for (int neighbor : adj.get(v)) {
            if (!visited[neighbor]) {
                dfs(neighbor, visited); // Chamada recursiva para os vizinhos
            }
        }
    }

    // Naive
    public List<int[]> findBridgesNaive() {
        List<int[]> bridges = new ArrayList<>();

        for (int u = 0; u < V; u++) {
            for (int v : new ArrayList<>(adj.get(u))) {
                if (u < v) { // Evita checagem duplicada
                    Graph clone = this.clone(); // Clona o grafo atual
                    clone.removeEdge(u, v); // Remove no clone
                    if (!clone.isConnectedForNaive()) {
                        bridges.add(new int[] { u, v }); // Se a remoção desconecta, é ponte
                    }
                }
            }
        }

        return bridges;
    }

    // Tarjan
    public List<int[]> findBridgesTarjan() {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V], low = new int[V];
        List<int[]> bridges = new ArrayList<>();
        Arrays.fill(disc, -1); // Inicializa tempos de descoberta
        int time = 0;
        for (int i = 0; i < V; i++) {
            if (disc[i] == -1) {
                tarjanDFS(i, -1, visited, disc, low, time, bridges);
            }
        }
        return bridges;
    }

    private void tarjanDFS(int u, int parent, boolean[] visited, int[] disc, int[] low, int time, List<int[]> bridges) {
        visited[u] = true;
        disc[u] = low[u] = ++time; // Inicializa tempos de descoberta e menor ancestral

        for (int v : adj.get(u)) {
            if (v == parent)
                continue; // Ignora aresta para o pai

            if (!visited[v]) {
                tarjanDFS(v, u, visited, disc, low, time, bridges);
                low[u] = Math.min(low[u], low[v]); // Atualiza low[u] com o menor low[v]

                if (low[v] > disc[u]) {
                    bridges.add(new int[] { u, v }); // Ponte detectada
                }
            } else {
                low[u] = Math.min(low[u], disc[v]); // Atualiza low[u] com tempo de descoberta de v
            }
        }
    }

}

// Gerador de grafo aleatório
class GeradorGrafo {
    public static Graph gerarGrafoAleatorio(int V, int E) {
        Graph graph = new Graph(V);
        Random rand = new Random();
        Set<String> edges = new HashSet<>(); // Hashset para evitar arestas duplicadas

        while (edges.size() < E) {
            int u = rand.nextInt(V);
            int v = rand.nextInt(V);
            // Garante que não haja loops nem arestas duplicadas
            if (u != v && !edges.contains(u + "-" + v) && !edges.contains(v + "-" + u)) {

                // Adiciona aresta ao grafo
                graph.addEdge(u, v);

                // Adiciona aresta ao Hashset de controle
                edges.add(u + "-" + v);
            }
        }

        return graph;
    }
    public static Graph gerarGrafoEuleriano(int V, int E) {
        Graph g = new Graph(V);
        Random rand = new Random();
    
        if (E < V || E % 2 != 0) {
            throw new IllegalArgumentException("Número de arestas deve ser pelo menos igual ao número de vértices e par");
        }
    
        // Garante um ciclo inicial (conectado e com graus pares)
        for (int i = 0; i < V; i++) {
            int next = (i + 1) % V;
            g.addEdge(i, next);
            E--;
        }
    
        // Adiciona arestas extras mantendo os graus pares
        while (E > 0) {
            int u = rand.nextInt(V);
            int v = rand.nextInt(V);
    
            if (u != v) {
                // Verifica se a aresta já existe percorrendo a lista de adjacência
                boolean jaExiste = false;
                for (int vizinho : g.getAdj().get(u)) {
                    if (vizinho == v) {
                        jaExiste = true;
                        break;
                    }
                }
    
                if (!jaExiste) {
                    g.addEdge(u, v);
                    E--;
                }
            }
        }
    
        return g;
    }
    
}

class FleuryAlgorithm {
    private Graph graph;

    public FleuryAlgorithm(Graph graph) {
        this.graph = graph;
    }

    // Retorna tipo do grafo: 0 = não euleriano, 1 = semi-euleriano, 2 = euleriano
    private int tipoGrafo() {
        int odd = 0;
        for (int i = 0; i < graph.getV(); i++) {
            if (graph.getAdj(i).size() % 2 != 0) {
                odd++;
            }
        }
        if (odd > 2)
            return 0;
        return (odd == 2) ? 1 : 2;
    }

    // Encontra caminho usando Fleury com verificação de ponte via Tarjan ou Força
    // Bruta
    public List<Integer> encontrarCaminhoEuleriano(boolean usarTarjan) {
        int tipo = tipoGrafo();
        if (tipo == 0)
            return null; // Não existe caminho euleriano
    
        int start = 0;
        if (tipo == 1) {
            for (int i = 0; i < graph.getV(); i++) {
                if (graph.getAdj(i).size() % 2 != 0) {
                    start = i;
                    break;
                }
            }
        }
    
        List<Integer> caminho = new ArrayList<>();
        dfsFleury(start, caminho, usarTarjan);
        return caminho;
    }
    
    private void dfsFleury(int u, List<Integer> caminho, boolean usarTarjan) {
        for (int v : new ArrayList<>(graph.getAdj(u))) {
            if (!isBridge(u, v, usarTarjan)) {
                graph.removeEdge(u, v);
                dfsFleury(v, caminho, usarTarjan);
            }
        }
        caminho.add(u);
    }
    
    private boolean isBridge(int u, int v, boolean usarTarjan) {
        graph.removeEdge(u, v);
        boolean isBridge = false;
    
        if (!graph.isConnected()) {
            isBridge = true;
        } else if (usarTarjan) {
            for (int[] bridge : graph.findBridgesTarjan()) {
                if ((bridge[0] == u && bridge[1] == v) || (bridge[0] == v && bridge[1] == u)) {
                    isBridge = true;
                    break;
                }
            }
        } else {
            for (int[] bridge : graph.findBridgesNaive()) {
                if ((bridge[0] == u && bridge[1] == v) || (bridge[0] == v && bridge[1] == u)) {
                    isBridge = true;
                    break;
                }
            }
        }
    
        graph.addEdge(u, v); // Reinsere a aresta
        return isBridge;
    }
    
    public String tipoEuleriano() {
        int tipo = tipoGrafo();
        switch (tipo) {
            case 0:
                return "Não Euleriano";
            case 1:
                return "Semi-Euleriano";
            case 2:
                return "Euleriano";
            default:
                return "Desconhecido";
        }
    }
    
    // imprime o caminho Euleriano
    public void printarCaminhoEuleriano(boolean usarTarjan) {
        List<Integer> caminho = encontrarCaminhoEuleriano(usarTarjan);
    
        if (caminho == null || caminho.isEmpty()) {
            System.out.println("Não existe caminho Euleriano.");
            return;
        }
    
        System.out.println("Caminho Euleriano:");
        for (int i = caminho.size() - 1; i >= 0; i--) {
            System.out.print(caminho.get(i));
            if (i != 0) System.out.print(" -> ");
        }
        System.out.println(); // quebra de linha
    }
    

}
