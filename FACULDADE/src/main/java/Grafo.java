import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Grafo {
  private int numVertices;
  private int numArestas;
  private List<List<Integer>> listaAdjacencia;
  private List<List<Integer>> listaPredecessores;

  public Grafo(String arquivo) throws FileNotFoundException {
    lerGrafo(arquivo);
  }

  private void lerGrafo(String arquivo) throws FileNotFoundException {
    Scanner scanner = new Scanner(new File(arquivo));

    // Lê o número de vértices e arestas
    numVertices = scanner.nextInt();
    numArestas = scanner.nextInt();

    // Inicializa as listas de adjacência e predecessores
    listaAdjacencia = new ArrayList<>(numVertices + 1);
    listaPredecessores = new ArrayList<>(numVertices + 1);

    for (int i = 0; i <= numVertices; i++) {
      listaAdjacencia.add(new ArrayList<>());
      listaPredecessores.add(new ArrayList<>());
    }

    // Lê as arestas e preenche as listas
    for (int i = 0; i < numArestas; i++) {
      int origem = scanner.nextInt();
      int destino = scanner.nextInt();

      listaAdjacencia.get(origem).add(destino);
      listaPredecessores.get(destino).add(origem);
    }

    scanner.close();
  }

  public int grauSaida(int vertice) {
    return listaAdjacencia.get(vertice).size();
  }

  public int grauEntrada(int vertice) {
    return listaPredecessores.get(vertice).size();
  }

  public List<Integer> sucessores(int vertice) {
    return listaAdjacencia.get(vertice);
  }

  public List<Integer> predecessores(int vertice) {
    return listaPredecessores.get(vertice);
  }


}