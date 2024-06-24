import java.util.Scanner;

//Treinando estrutura de arvore em java pra prova pratica

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Arvore arvore = new Arvore();
    while (scanner.hasNext()) {
      int input = scanner.nextInt();
      if (input == 0)
        break;
      System.out.println("raiz!" + arvore.raiz.elemento + " " + input);
      arvore.inserir(input);

    } /*
       * System.out.println("raiz: " + arvore.raiz.elemento);
       * System.out.println("dir: " + arvore.raiz.dir.elemento);
       * System.out.println("esq: " + arvore.raiz.esq.elemento);
       */
    arvore.imprimir();

    scanner.close();
  }

  public static class Arvore {
    No raiz;

    Arvore() {
      this.raiz = new No();
    }

    public static class No {
      int elemento;
      No esq, dir;

      No() {
        this.elemento = 0;
        this.esq = null;
        this.dir = null;
      }

      No(int x) {
        this.elemento = x;
        this.esq = null;
        this.dir = null;
      }

    }

    public void inserir(int elemento) {
      if (this.raiz == null || this.raiz.elemento == 0)
        this.raiz = new No(elemento);
      else {
        No percorre = this.raiz;
        while (percorre != null) {
          if (elemento < percorre.elemento) {
            if (percorre.esq == null) {
              percorre.esq = new No(elemento);
              percorre = null;
            } else {
              percorre = percorre.esq;
            }
          } else {
            if (percorre.dir == null) {
              percorre.dir = new No(elemento);
              percorre = null;
            } else {
              percorre = percorre.dir;
            }
          }

        }
      }
    }

    public void imprimir() {
      imprimir(this.raiz);
    }

    public void imprimir(No raiz) {
      if (raiz != null) {
        imprimir(raiz.esq);
        System.out.println(raiz.elemento);
        imprimir(raiz.dir);
      }
    }

  }
}
