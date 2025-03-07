import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;



public class Main{
  public static void main(String[] args) {
    try {
      Scanner scanner = new Scanner(System.in);

      System.out.print("Digite o nome do arquivo contendo o grafo: ");
      String arquivo = scanner.nextLine();
      // NOME:  src/main/java/graph-test-50000-1.txt

      System.out.print("Digite o número do vértice: ");
      int vertice = scanner.nextInt();

      Grafo grafo = new Grafo(arquivo);

      System.out.println("Grau de Saída: " + grafo.grauSaida(vertice));
      System.out.println("Grau de Entrada: " + grafo.grauEntrada(vertice));
      System.out.println("Sucessores: " + grafo.sucessores(vertice));
      System.out.println("Predecessores: " + grafo.predecessores(vertice));

      scanner.close();
    } catch (FileNotFoundException e) {
      System.err.println("Arquivo não encontrado: " + e.getMessage());
    }
  }
}

