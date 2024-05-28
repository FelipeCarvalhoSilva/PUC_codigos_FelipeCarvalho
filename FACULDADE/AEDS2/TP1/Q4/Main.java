import java.io.*;
import java.nio.charset.*;
import java.util.Random;

public class Main {
  public static void main(String[] args) {
    Random gerador = new Random();
    gerador.setSeed(4);

    String input = "ae";
    StringBuilder saida = new StringBuilder(); // StringBuilder para armazenar a saída
    while (!input.equals("FIM")) {
      input = MyIO.readLine();
      StringBuilder modificado = new StringBuilder();
      char um, dois;
      um = (char) ('a' + Math.abs(gerador.nextInt()) % 26);
      dois = (char) ('a' + Math.abs(gerador.nextInt()) % 26);
        if (!input.equals("FIM")) {
          for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == um) {
              modificado.append(dois);
            } else
              modificado.append(input.charAt(i));
          }
          saida.append(modificado.toString()).append("\n"); // Adicionando a saída ao StringBuilder
        }
    }
    MyIO.print(saida.toString()); // Imprimindo toda a saída de uma vez
  }
} 
