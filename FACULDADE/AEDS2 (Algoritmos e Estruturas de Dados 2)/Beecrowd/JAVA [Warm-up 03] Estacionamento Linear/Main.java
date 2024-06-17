import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    int N = -1, K = -1;
    int Ci, Si;
    // N = numero motoristas
    // K= numero de carros que estacionamento suporta

    Scanner input = new Scanner(System.in);
    while (N != 0 && K != 0) {
      N = input.nextInt();
      K = input.nextInt();
      if (N == 0 && K == 0)
        break;
      Garagem garagem = new Garagem(N);
      for (int i = 0; i < N; i++) {
        Ci = input.nextInt();// horario chegada
        Si = input.nextInt();// horario saida
        garagem.carros[i] = new Garagem.elementos(Ci, Si);
      }
      if (garagem.procuraConflitos()) {
        System.out.println("Nao");
      } else
        System.out.println("Sim");

    }
    input.close();
  }

  public static class Garagem {
    public elementos[] carros;
    public int tamanho;

    public static class elementos {
      int entrada;
      int saida;

      public elementos(int entrada, int saida) {
        this.entrada = entrada;
        this.saida = saida;
      }
    }

    Garagem(int tamanho) {
      this.tamanho = tamanho;
      this.carros = new elementos[tamanho];
    }

    public boolean procuraConflitos() {

      for (int i = 0; i < this.tamanho - 1; i++) {
        for (int j = i + 1; j < this.tamanho; j++) {
          if (carros[i].saida > carros[j].entrada && carros[i].saida < carros[j].saida)
            return true;
        }
      }
      return false;
    }
  }

}
